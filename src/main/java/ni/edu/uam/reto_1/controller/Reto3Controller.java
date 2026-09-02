package ni.edu.uam.reto_1.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ni.edu.uam.reto_1.modelos.Artesania;

public class Reto3Controller {

    @FXML private TableView<Artesania> tablaArtesanias;
    @FXML private TableColumn<Artesania, ImageView> colImagen;
    @FXML private TableColumn<Artesania, String> colCodigo;
    @FXML private TableColumn<Artesania, String> colNombre;
    @FXML private TableColumn<Artesania, Double> colPrecio;

    private ObservableList<Artesania> listaArtesanias = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Vincular columnas validando que no sean null
        if (colImagen != null) colImagen.setCellValueFactory(new PropertyValueFactory<>("imagen"));
        if (colCodigo != null) colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        if (colNombre != null) colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        if (colPrecio != null) colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        // Crear objetos de prueba sin pasar imágenes
        Artesania art1 = new Artesania("ART-001", "Artesanía de Masaya", 450.50, null);
        Artesania art2 = new Artesania("ART-002", "Adorno Nicaragüense", 320.00, null);

        listaArtesanias.clear();
        listaArtesanias.addAll(art1, art2);

        if (tablaArtesanias != null) {
            tablaArtesanias.setItems(listaArtesanias);
        }
    }

    // Eventos de la ToolBar y el Menú
    @FXML
    public void onNuevoClick(ActionEvent event) {
        System.out.println("Acción: Preparando para registrar nueva artesanía...");
    }

    @FXML
    public void onGuardarClick(ActionEvent event) {
        System.out.println("Acción: Guardando artesanía en el catálogo...");
    }

    @FXML
    public void onBuscarClick(ActionEvent event) {
        System.out.println("Acción: Abriendo el buscador de productos...");
    }

    @FXML
    public void onMenuAyudaClick(ActionEvent event) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Ayuda");
        alerta.setHeaderText("Catálogo de Artesanías");
        alerta.setContentText("Versión 1.0 - Gestión de inventario y ventas.");
        alerta.showAndWait();
    }

}
