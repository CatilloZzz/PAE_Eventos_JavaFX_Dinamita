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
        // 1. Vincular columnas con los atributos de la clase Artesania
        colImagen.setCellValueFactory(new PropertyValueFactory<>("imagen"));
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        // 2. Cargar las imágenes de manera segura con bloque try-catch
        try {
            Image img1 = new Image(getClass().getResourceAsStream("/Imagenes/Masaya-Arts-and-Crafts-Nicaragua-Centroamerica-06.webp"));
            Image img2 = new Image(getClass().getResourceAsStream("/Imagenes/bff951bb1266002621d66e0ce2616a96.webp"));

            ImageView view1 = new ImageView(img1);
            ImageView view2 = new ImageView(img2);

            // 3. Crear objetos Artesania
            Artesania art1 = new Artesania("ART-001", "Artesanía de Masaya", 450.50, view1);
            Artesania art2 = new Artesania("ART-002", "Adorno Nicaragüense", 320.00, view2);

            listaArtesanias.addAll(art1, art2);
        } catch (Exception e) {
            System.out.println("Error al cargar las imágenes. Revisa los nombres de archivo.");
            e.printStackTrace();
        }

        // 4. Asignar lista a la tabla
        tablaArtesanias.setItems(listaArtesanias);
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
