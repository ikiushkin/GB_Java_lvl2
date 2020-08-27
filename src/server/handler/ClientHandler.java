package server.handler;

import server.service.ServerImpl;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private ServerImpl server;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private boolean isClosed;
    private int timeout = 120;

    private volatile String nick;

    public ClientHandler(ServerImpl server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
            this.nick = "";
            Thread connection = new Thread(() -> {
                try {
                    authentication();
                    readMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            });
            connection.start();
            new Thread(() -> {
                try {
                    Thread.sleep(timeout * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (getNick().equals("")) {
                    sendMsg("Вы отключены! Превышение времени ожидания!");
                    sendMsg("/disconnect");
                    connection.interrupt();
                    closeConnection();
                }
            }).start();
        } catch (IOException e) {
            throw new RuntimeException("Проблемы при создании обработчика клиента");
        }
    }

    private void authentication() throws IOException {
        while (!isClosed) {
            if (dis.available() > 0) {
                String str = dis.readUTF();
                if (str.startsWith("/auth") && str.split("\\s").length == 3) {
                    String[] dataArray = str.split("\\s");
                    String nick = server.getAuthService().getNick(dataArray[1], dataArray[2]);
                    if (nick != null) {
                        if (!server.isNickBusy(nick)) {
                            sendMsg("/authOk " + nick);
                            this.nick = nick;
                            server.subscribe(this);
                            server.broadcastMsg(this.nick + " присоединился к чату");
                            return;
                        } else {
                            sendMsg("Пользователь с данным ником уже подключён");
                        }
                    } else {
                        sendMsg("Неверный логин или пароль");
                    }
                }
            }
        }
    }

    public void sendMsg(String msg) {
        try {
            dos.writeUTF(msg);
        } catch (IOException e) {
            System.out.println(nick + " closed chat and disconnected");
        }
    }

    public void readMessage() {
        try {
            while (true) {
                String clientStr = dis.readUTF();
                System.out.println("От " + this.nick + ": " + clientStr);

                if (clientStr.startsWith("/")) {
                    if (clientStr.startsWith("/w") && clientStr.split("\\s").length > 2) {
                        String toUser = clientStr.split("\\s")[1];
                        String msg = clientStr.split("\\s", 3)[2];
                        privateMsg(ClientHandler.this, toUser, msg);
                    } else if (clientStr.equals("/exit")) {
                        return;
                    } else if (clientStr.equals("/online")) {
                        sendMsg("Пользователи в сети: ");
                        for (ClientHandler c : server.clients) {
                            sendMsg(c.getNick());
                        }
                    } else {
                        sendMsg("Вы ввели неверную команду");
                    }
                } else {
                    server.broadcastMsg(nick + ": " + clientStr);
                }
            }
        } catch (IOException e) {
            sendMsg("Вы ввели неверную команду");
        }
    }

    public void privateMsg(ClientHandler fromUser, String toUser, String msg) {
        if (!server.isNickBusy(toUser)) {
            sendMsg(toUser + " не в сети!");
        } else {
            for (ClientHandler c : server.clients) {
                if (toUser.equals(c.getNick())) {
                    c.sendMsg("От " + fromUser.getNick() + ": " + msg);
                    break;
                }
            }
            fromUser.sendMsg("Кому " + toUser + ": " + msg);
        }
    }

    public String getNick() {
        return nick;
    }

    private void closeConnection() {
        isClosed = true;
        server.unsubscribe(this);
        server.broadcastMsg(this.nick + ": Вышел из чата");

        try {
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}