package co.edu.uniquindio.poo.sistemas_notificacion.viewController;

import co.edu.uniquindio.poo.sistemas_notificacion.model.Notificacion;
import co.edu.uniquindio.poo.sistemas_notificacion.model.Cliente;
import co.edu.uniquindio.poo.sistemas_notificacion.model.Sesion;
import co.edu.uniquindio.poo.sistemas_notificacion.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.scene.control.TableRow;
import javafx.stage.Stage;

import java.io.IOException;

public class ClienteEmailViewController {

    @FXML
    private Label lblNombreUsuario;

    @FXML
    private TableView<Notificacion> tablaNotificaciones;

    @FXML
    private TableColumn<Notificacion, String> columnaNombreUsuario;

    @FXML
    private TableColumn<Notificacion, String> columnaMensaje;


    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnVolver;

    @FXML
    private Button btnMarcarLeida;

    private ObservableList<Notificacion> notificaciones;

    private User userActivo = Sesion.getInstance().getUsuario();

    // Aquí guardamos las notificaciones leídas para colorear
    private final java.util.Set<Notificacion> notificacionesLeidas = new java.util.HashSet<>();

    @FXML
    public void initialize() {
        columnaNombreUsuario.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getUsuario().getNombre())
        );

        columnaMensaje.setCellValueFactory(new PropertyValueFactory<>("mensaje"));

        tablaNotificaciones.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        tablaNotificaciones.setRowFactory(tv -> new TableRow<>() {
            @Override
            protected void updateItem(Notificacion item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setStyle("");
                } else {
                    if (notificacionesLeidas.contains(item)) {
                        setStyle("-fx-background-color: lightgray;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });

        btnEliminar.setDisable(true);
        btnMarcarLeida.setDisable(true);

        tablaNotificaciones.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            boolean disable = newSel == null;
            btnEliminar.setDisable(disable);
            btnMarcarLeida.setDisable(disable);
        });
    }

    public void cargarNotificaciones(User userActivo) {
        lblNombreUsuario.setText("Usuario: " + userActivo.getNombre());

        notificaciones = FXCollections.observableArrayList(obtenerNotificacionesCliente(userActivo));
        tablaNotificaciones.setItems(notificaciones);
    }


    private java.util.List<Notificacion> obtenerNotificacionesCliente(User userActivo) {
            return new java.util.ArrayList<>(userActivo.getNotificaciones());
        }


    @FXML
    private void eliminarNotificacion() {
        Notificacion seleccionada = tablaNotificaciones.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            notificaciones.remove(seleccionada);
            notificacionesLeidas.remove(seleccionada);
        }
    }

    @FXML
    private void marcarLeida() {
        Notificacion seleccionada = tablaNotificaciones.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            marcarNotificacionComoLeida(seleccionada);
            tablaNotificaciones.refresh();
        }
    }

    private void marcarNotificacionComoLeida(Notificacion notificacion) {
        notificacionesLeidas.add(notificacion);
    }

    // Métodos para los botones que mencionas:
    @FXML
    public void onEliminarClick(ActionEvent actionEvent) {
        eliminarNotificacion();
    }

    @FXML
    public void onMarcarLeidoClick(ActionEvent actionEvent) {
        marcarLeida();
    }

    @FXML
    public void onVolver(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/sistemas_notificacion/Aplications/cliente/AplicacionCliente.fxml"));
            Scene newScene = new Scene(loader.load());

            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.setTitle("Login");

        } catch (IOException e) {
            System.out.println("Error al cargar la nueva ventana: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
