package ni.edu.uam.reto_1;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;
import java.util.Optional;

public class RecepcionCafeController {

    @FXML private TextField txtIdLote;
    @FXML private TextField txtProductor;
    @FXML private TextField txtQuintales;
    @FXML private TextField txtVariedad;
    @FXML private DatePicker dpFechaEntrega;

    @FXML private TableView<LoteCafe> tablaLotes;
    @FXML private TableColumn<LoteCafe, String> colId;
    @FXML private TableColumn<LoteCafe, String> colProductor;
    @FXML private TableColumn<LoteCafe, Double> colQuintales;
    @FXML private TableColumn<LoteCafe, String> colVariedad;
    @FXML private TableColumn<LoteCafe, LocalDate> colFecha;

    @FXML private Label lblEstado;
    @FXML private Button btnGuardar;

    private ObservableList<LoteCafe> listaLotes = FXCollections.observableArrayList();
    private LoteCafe loteSeleccionadoParaEditar = null;

    @FXML
    public void initialize() {
        // Asignación de datos a columnas
        colId.setCellValueFactory(new PropertyValueFactory<>("idLote"));
        colProductor.setCellValueFactory(new PropertyValueFactory<>("productor"));
        colQuintales.setCellValueFactory(new PropertyValueFactory<>("quintales"));
        colVariedad.setCellValueFactory(new PropertyValueFactory<>("tipoVariedad"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fechaEntrega"));

        tablaLotes.setItems(listaLotes);

        // Crear menú contextual (ContextMenu)
        crearMenuContextual();
    }

    @FXML
    protected void guardarLoteOnClick() {
        try {
            String id = txtIdLote.getText().trim();
            String productor = txtProductor.getText().trim();
            double quintales = Double.parseDouble(txtQuintales.getText().trim());
            String variedad = txtVariedad.getText().trim();
            LocalDate fecha = dpFechaEntrega.getValue();

            if (id.isEmpty() || productor.isEmpty() || fecha == null) {
                mostrarAlerta("Campos vacíos", "Llene todos los campos requeridos.", Alert.AlertType.WARNING);
                return;
            }

            if (loteSeleccionadoParaEditar == null) {
                // Nuevo Lote
                LoteCafe nuevoLote = new LoteCafe(id, productor, quintales, variedad, fecha);
                listaLotes.add(nuevoLote);
                lblEstado.setText("Lote " + id + " registrado correctamente.");
            } else {
                // Editar Lote existente
                loteSeleccionadoParaEditar.setIdLote(id);
                loteSeleccionadoParaEditar.setProductor(productor);
                loteSeleccionadoParaEditar.setQuintales(quintales);
                loteSeleccionadoParaEditar.setTipoVariedad(variedad);
                loteSeleccionadoParaEditar.setFechaEntrega(fecha);

                tablaLotes.refresh();
                btnGuardar.setText("Guardar Lote");
                loteSeleccionadoParaEditar = null;
                lblEstado.setText("Lote actualizado.");
            }

            limpiarFormulario();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de entrada", "Ingrese un valor numérico válido en Quintales.", Alert.AlertType.ERROR);
        }
    }

    // Evento de ratón (MouseEvent) al seleccionar una fila
    @FXML
    protected void seleccionarLoteTabla(MouseEvent event) {
        LoteCafe seleccionado = tablaLotes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            lblEstado.setText("Seleccionado: " + seleccionado.getProductor() + " (" + seleccionado.getQuintales() + " QQ)");
        }
    }

    // Crear menú desplegable con clic derecho (ContextMenu)
    private void crearMenuContextual() {
        ContextMenu contextMenu = new ContextMenu();

        MenuItem itemEditar = new MenuItem("Editar Lote");
        MenuItem itemEliminar = new MenuItem("Eliminar Lote");

        itemEditar.setOnAction(e -> editarLote());
        itemEliminar.setOnAction(e -> eliminarLoteConfirmacion());

        contextMenu.getItems().addAll(itemEditar, itemEliminar);

        tablaLotes.setRowFactory(tv -> {
            TableRow<LoteCafe> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getButton() == MouseButton.SECONDARY && !row.isEmpty()) {
                    contextMenu.show(row, event.getScreenX(), event.getScreenY());
                } else {
                    contextMenu.hide();
                }
            });
            return row;
        });
    }

    private void editarLote() {
        LoteCafe seleccionado = tablaLotes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            loteSeleccionadoParaEditar = seleccionado;
            txtIdLote.setText(seleccionado.getIdLote());
            txtProductor.setText(seleccionado.getProductor());
            txtQuintales.setText(String.valueOf(seleccionado.getQuintales()));
            txtVariedad.setText(seleccionado.getTipoVariedad());
            dpFechaEntrega.setValue(seleccionado.getFechaEntrega());

            btnGuardar.setText("Actualizar Lote");
        }
    }

    // Confirmación mediante Alert antes de eliminar
    private void eliminarLoteConfirmacion() {
        LoteCafe seleccionado = tablaLotes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmar eliminación");
            alert.setHeaderText("Eliminar Lote " + seleccionado.getIdLote());
            alert.setContentText("¿Está seguro de que desea eliminar el lote del productor " + seleccionado.getProductor() + "?");

            Optional<ButtonType> resultado = alert.showAndWait();
            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                listaLotes.remove(seleccionado);
                lblEstado.setText("Lote eliminado.");
                limpiarFormulario();
            }
        }
    }

    @FXML
    protected void limpiarFormulario() {
        txtIdLote.clear();
        txtProductor.clear();
        txtQuintales.clear();
        txtVariedad.clear();
        dpFechaEntrega.setValue(null);
        loteSeleccionadoParaEditar = null;
        btnGuardar.setText("Guardar Lote");
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}