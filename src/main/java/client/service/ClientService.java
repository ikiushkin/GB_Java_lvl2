package client.service;

import javax.swing.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientService extends JFrame {

    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    public ClientService() {

        try {
            socket = new Socket("localhost", 8189);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
            setAutorized(false);

            Thread t1 = new Thread(() -> {
                try {
                    while (true) {
                        String strMsg = dis.readUTF();
                        if (strMsg.startsWith("/authOk")) {
                            setAutorized(true);
                            break;
                        }
                        //chatArea.appendText(strMsg + "\n");
                    }
                    while (true) {
                        String strMsg = dis.readUTF();
                        if (strMsg.equals("/exit")) {
                            break;
                        }
                        //chatArea.appendText(strMsg + "\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            Thread t2 = new Thread(()  -> {

                Scanner scanner = new Scanner(System.in);

                while (true) {
                    try {
                        String str = scanner.nextLine();
                            if (str.equalsIgnoreCase("/exit")) {
                            System.out.println("Клиент отключился");
                            dos.writeUTF("/exit");
                            break;
                        }
                        dos.writeUTF("Клиент : " + str);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            t1.setDaemon(true);
            t1.start();
            t2.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage() {


    }

    private void setAutorized(boolean b) {
    }

}