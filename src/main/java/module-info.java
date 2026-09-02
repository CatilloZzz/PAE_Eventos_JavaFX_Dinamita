module ni.edu.uam.reto_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens ni.edu.uam.reto_1 to javafx.fxml;
    exports ni.edu.uam.reto_1;
    exports ni.edu.uam.reto_1.controller;
    opens ni.edu.uam.reto_1.controller to javafx.fxml;
    exports ni.edu.uam.reto_1.modelos;
    opens ni.edu.uam.reto_1.modelos to javafx.fxml;
}