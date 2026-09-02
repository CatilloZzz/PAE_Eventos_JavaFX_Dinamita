package ni.edu.uam.reto_1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Reto1Application extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Carga la interfaz diseñada en Scene Builder
        URL fxmlLocation = getClass().getResource("/ni/edu/uam/reto_1/Reto1-view.fxml");
        Parent root = FXMLLoader.load(fxmlLocation);

        Scene scene = new Scene(root);
        primaryStage.setTitle("Reto 1 - Inventario de Pulpería");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
