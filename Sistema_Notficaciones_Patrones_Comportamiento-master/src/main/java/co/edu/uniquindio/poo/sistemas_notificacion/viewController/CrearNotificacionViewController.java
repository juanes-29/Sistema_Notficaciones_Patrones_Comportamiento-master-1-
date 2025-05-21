package co.edu.uniquindio.poo.sistemas_notificacion.viewController;

import co.edu.uniquindio.poo.sistemas_notificacion.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class CrearNotificacionViewController {

    @FXML
    private ComboBox<EventManager> comboEventos;

    @FXML
    private TextField campoMensaje;

    @FXML
    private Button btnEnviar;

    @FXML
    private Button btn_Volver;


    private final ObservableList<EventManager> listaEventos = FXCollections.observableArrayList();

    SistemaNotificaciones sistemaNotificaciones = SistemaNotificaciones.getInstance();

    @FXML
    public void initialize() {
        // Cargar los eventos disponibles en el ComboBox
        comboEventos.setItems(listaEventos);
        comboEventos.setPromptText("Seleccione un evento");

        listaEventos.setAll(
                sistemaNotificaciones.getEventoInformacion(),
                sistemaNotificaciones.getEventoPromocion(),
                sistemaNotificaciones.getEventoSeguridad()
        );
    }


    @FXML
    public void onEnviarNotificacion() {
        EventManager eventoSeleccionado = comboEventos.getValue();
        String mensaje = campoMensaje.getText();

        if (eventoSeleccionado == null || mensaje.isBlank()) {
            mostrarAlerta("Por favor seleccione un evento y escriba un mensaje.");
            return;
        }

        sistemaNotificaciones.generarMensajeBase(eventoSeleccionado, mensaje);
        sistemaNotificaciones.generarNotificaciones(eventoSeleccionado);

        mostrarConfirmacion("Notificaciones enviadas correctamente.");
        campoMensaje.clear();
        comboEventos.getSelectionModel().clearSelection();
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Éxito");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    void onVolver(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/poo/sistemas_notificacion/Aplications/admin/AplicacionAdministrador.fxml"
            ));
            Scene escena = new Scene(loader.load());

            Stage escenarioActual = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            escenarioActual.setScene(escena);
            escenarioActual.setTitle("Panel Administrador");
            escenarioActual.show();

        } catch (IOException e) {
            mostrarError("Error al volver", "No se pudo cargar la ventana del administrador.");
            e.printStackTrace();
        }
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}

