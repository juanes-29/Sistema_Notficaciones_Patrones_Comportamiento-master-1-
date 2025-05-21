
package co.edu.uniquindio.poo.sistemas_notificacion.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.sistemas_notificacion.model.EventManager;
import co.edu.uniquindio.poo.sistemas_notificacion.model.Sesion;
import co.edu.uniquindio.poo.sistemas_notificacion.model.SistemaNotificaciones;
import co.edu.uniquindio.poo.sistemas_notificacion.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class AplicacionClienteViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnHistorial;

    @FXML
    private Button btnSuscribirse;

    @FXML
    private ComboBox<EventManager> comboEventos;

    @FXML
    private TextArea cuadroPush;

    Sesion sesion = Sesion.getInstance();


    /**
     * Muestra una alerta.
     *
     * @param mensaje El mensaje que se mostrará en la alerta.
     * @param tipo El tipo de alerta (en este caso, se utiliza Alert.AlertType.INFORMATION).
     */
    public void mostrarAlerta(String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle("!");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }


    @FXML
    void onCerrarSesionClick(ActionEvent event) {
        sesion.setUsuario(null);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/sistemas_notificacion/Logins/Login.fxml"));
            Scene newScene = new Scene(loader.load());

            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            currentStage.setScene(newScene);
            currentStage.setTitle("Login");

        } catch (IOException e) {
            System.out.println("Error al cargar la nueva ventana: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void onHistorialAction(ActionEvent event) {

    }

    @FXML
    void onSuscribirseAction(ActionEvent event) {

        EventManager eventoSeleccionado = comboEventos.getSelectionModel().getSelectedItem();

        if (eventoSeleccionado == null) {
            mostrarAlerta("No se ha seleccionado ningún evento.", Alert.AlertType.WARNING);
            return;
        }

        User usuario = Sesion.getInstance().getUsuario();

        if (eventoSeleccionado.getSuscriptores().contains(usuario)) {
            eventoSeleccionado.getSuscriptores().remove(usuario);
            mostrarAlerta("Te has desuscrito del evento exitosamente.", Alert.AlertType.INFORMATION);
        } else {
            eventoSeleccionado.getSuscriptores().add(usuario);
            System.out.println("Suscriptor: " + eventoSeleccionado.getSuscriptores());
            mostrarAlerta("Te has suscrito al evento exitosamente.", Alert.AlertType.INFORMATION);
        }

    }


    public void mostrarNotificacionPush() {

        limpiarNotificacion();
        var primeraNotificacion = sesion.getUsuario().getNotificaciones().peekFirst();
        if (primeraNotificacion != null && primeraNotificacion.getMensaje() != null) {
            cuadroPush.setText(primeraNotificacion.getMensaje());
        }
    }


    public void limpiarNotificacion() {
        cuadroPush.clear();
    }


    private final ObservableList<EventManager> listaEventos = FXCollections.observableArrayList();

    SistemaNotificaciones sistemaNotificaciones = SistemaNotificaciones.getInstance();


    @FXML
    void initialize() {

        mostrarNotificacionPush();

        comboEventos.setItems(listaEventos);
        comboEventos.setPromptText("Seleccione un evento");

        listaEventos.setAll(
                sistemaNotificaciones.getEventoInformacion(),
                sistemaNotificaciones.getEventoPromocion(),
                sistemaNotificaciones.getEventoSeguridad()
        );


        assert btnCerrarSesion != null : "fx:id=\"btnCerrarSesion\" was not injected: check your FXML file 'AplicacionCliente.fxml'.";
        assert btnHistorial != null : "fx:id=\"btnHistorial\" was not injected: check your FXML file 'AplicacionCliente.fxml'.";
        assert btnSuscribirse != null : "fx:id=\"btnSuscribirse\" was not injected: check your FXML file 'AplicacionCliente.fxml'.";
        assert comboEventos != null : "fx:id=\"comboEventos\" was not injected: check your FXML file 'AplicacionCliente.fxml'.";
        assert cuadroPush != null : "fx:id=\"cuadroPush\" was not injected: check your FXML file 'AplicacionCliente.fxml'.";

    }

}







