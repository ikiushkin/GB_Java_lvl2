package server.handler;

import server.inter.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {

    private Server server; // Имя сервера
    private Socket socket; // Порт подключения
    private DataInputStream dis; // Поток ввода
    private DataOutputStream dos; // Поток вывода

    private String nick; // Ник

    public String getNick() {
        return nick;
    }

    // Метод взаимодействия клиента с сервером
    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
            this.nick = "";

            new Thread(() -> {
                try {
                    authentication();
                    readMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException e) {
            throw new RuntimeException("Проблемы при создании обработчика клиента");
        }
    }

    private void authentication() throws IOException {
        while (true) {
            String str = dis.readUTF(); // Читаем сообщение от клиента
            if (str.startsWith("/auth")) {
                String[] dataArray = str.split("\\s"); // Парсим сообщение клиента
                String nick = server.getAuthService().getNick(dataArray[1], dataArray[2]); // Получаем ник по логину и паролю
                if (nick != null) {
                    if (!server.isNickBusy(nick)) {
                        sendMsg("/authOk " + nick);
                        this.nick = nick;
                        server.broadcastMsg(this.nick + " присоединился к чату"); // this = nick, пишем в чат о подключении клиента
                        server.subscribe(this); // Добавляем его в список авторизованных
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

    // Метод вывода сообщения в чат
    public void sendMsg(String msg) {
        try {
            dos.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Метод чтения сообщений
    public void readMessage() throws IOException {
        while (true) {
            String clientStr = dis.readUTF();
            System.out.println("От: " + this.nick + ": " + clientStr);
            if (clientStr.equals("/exit")) {
                return;
            }
            if (clientStr.startsWith("/w")) {
                String[] privatMsgArray = clientStr.split("\\s");
                String nick = privatMsgArray[1];
                StringBuilder privatMsg = null;
                for (int i = 2; i < privatMsgArray.length; i++) {
                    privatMsg.append(i).append(" ");
                }
                server.sendPrivateMsg(this, nick, privatMsg.toString());
                continue;
            }
            server.broadcastMsg(this.nick + ": " + clientStr);
        }
    }

    private void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMsg(this.nick + " вышел из чата");

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