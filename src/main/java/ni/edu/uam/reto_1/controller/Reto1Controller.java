package ni.edu.uam.reto_1.controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;
import java.util.Map;
public class Reto1Controller {
    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPrecio;
    @FXML private TextField txtCantidad;
    @FXML private TextField txtBuscar;

    @FXML private TableView<Producto> tablaInventario;
    @FXML private TableColumn<Producto, String> colCodigo;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Double> colPrecio;
    @FXML
    private TableColumn<Producto, Integer> colCantidad;

    // Simulación de base de datos y lista para la tabla
    private Map<String, Producto> inventario = new HashMap<>();
    private ObservableList<Producto> listaResultado = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Vincula las columnas de la tabla con los atributos de la clase Producto
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

        tablaInventario.setItems(listaResultado);
    }

    @FXML
    public void guardarProducto(ActionEvent event) {
        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String precioStr = txtPrecio.getText().trim();
        String cantidadStr = txtCantidad.getText().trim();

        if (codigo.isEmpty() || nombre.isEmpty() || precioStr.isEmpty() || cantidadStr.isEmpty()) {
            mostrarAlerta("Campos Vacíos", "Todos los campos son obligatorios.");
            return;
        }

        try {
            double precio = Double.parseDouble(precioStr);
            int cantidad = Integer.parseInt(cantidadStr);

            if (inventario.containsKey(codigo)) {
                mostrarAlerta("Duplicado", "Ya existe un producto con el código: " + codigo);
            } else {
                Producto nuevo = new Producto(codigo, nombre, precio, cantidad);
                inventario.put(codigo, nuevo);

                listaResultado.clear();
                listaResultado.add(nuevo);
                limpiarCampos();
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "El precio y la cantidad deben ser valores numéricos.");
        }
    }

    @FXML
    public void buscarConEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            String codigo = txtBuscar.getText().trim();
            Producto p = inventario.get(codigo);

            listaResultado.clear();
            if (p != null) {
                listaResultado.add(p);
            } else {
                mostrarAlerta("No encontrado", "No se encontró ningún producto con ese código.");
            }
            txtBuscar.clear();
        }
    }

    private void limpiarCampos() {
        txtCodigo.clear(); txtNombre.clear();
        txtPrecio.clear(); txtCantidad.clear();
        txtCodigo.requestFocus();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Clase interna que define los datos del producto
    public static class Producto {
        private String codigo;
        private String nombre;
        private double precio;
        private int cantidad;

        public Producto(String codigo, String nombre, double precio, int cantidad) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.precio = precio;
            this.cantidad = cantidad;
        }

        public String getCodigo() { return codigo; }
        public String getNombre() { return nombre; }
        public double getPrecio() { return precio; }
        public int getCantidad() { return cantidad; }
    }
}
