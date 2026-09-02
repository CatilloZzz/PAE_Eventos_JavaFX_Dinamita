module ni.edu.uam.reto_1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ni.edu.uam.reto_1 to javafx.fxml;
    exports ni.edu.uam.reto_1;
}