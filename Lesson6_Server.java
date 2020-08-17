import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Lesson6_Server {
    public static void main(String[] args) {

        Socket socket = null;

        try (ServerSocket serverSocket = new ServerSocket(8189)) {

            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());


            new Thread((Runnable) () -> {

                while (true) {
                    String str = null;
                    try {
                        str = dis.readUTF();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (str.equalsIgnoreCase("end")) {
                        System.out.println("Клиент отключился");
                        break;
                    }
                    System.out.println(str);
                }
            }).start();

            new Thread((Runnable) () -> {

                Scanner scanner = new Scanner(System.in);
                
                while (true) {
                    try {
                        String s = scanner.nextLine();
                        if (s.equalsIgnoreCase("end")) {
                            System.exit(0);
                        }
                        dos.writeUTF("Сервер : " + s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
