package ni.edu.uam.reto_1;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class MenuApplication extends Application {
    @Override

    public void start(Stage primaryStage) {
        try {
            // Carga el archivo Menu-view.fxml desde el mismo paquete
            Parent root = FXMLLoader.load(getClass().getResource("Menu-view.fxml"));
            Scene scene = new Scene(root, 400, 350);

            primaryStage.setTitle("Menú Principal - Ejercicios en Pareja");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}