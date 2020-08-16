package hw;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Lesson6_Client {

    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public Lesson6_Client() {
        try {
            openConnection();
            sendMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openConnection() throws IOException {

        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        new Thread((Runnable) () -> {

            try {
                while (true) {
                    String strFromServer = in.readUTF();
                    System.out.println(strFromServer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void sendMessage() {

        new Thread((Runnable) () -> {

            Scanner scanner = new Scanner(System.in);

            while (true) {
                try {
                    String str = scanner.nextLine();
                    if (str.equalsIgnoreCase("end")) {
                        System.out.println("Клиент отключился");
                        out.writeUTF("end");
                        closeConnection();
                        break;
                    }
                    out.writeUTF("Клиент : " + str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void closeConnection() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Lesson6_Client();
    }
}
