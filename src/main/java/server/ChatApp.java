package server;

/*import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;*/
import server.service.ServerImpl;

public class ChatApp /*extends Application*/ {

    public static void main(String[] args) {

        new ServerImpl();
        //launch(args);
    }

/*    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("ClientChatWindow.fxml"));
        primaryStage.setTitle("CHAT");
        primaryStage.setScene(new Scene(root, 400, 500));
        primaryStage.show();
    }*/
}