package co.edu.uniquindio.poo.sistemas_notificacion.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.sistemas_notificacion.model.Admin;
import co.edu.uniquindio.poo.sistemas_notificacion.model.Sesion;
import co.edu.uniquindio.poo.sistemas_notificacion.model.SistemaNotificaciones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class LoginAdministradorViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button atrasBoton;

    @FXML
    private Label errorLabel;

    @FXML
    private Button inicioSesionBoton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    Sesion sesion = Sesion.getInstance();
    SistemaNotificaciones notificaciones = SistemaNotificaciones.getInstance();

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


    /**
     * Accion al oprimir el boton de Login
     * @param event
     */
    @FXML
    void onLoginClick(ActionEvent event) {

        if (passwordField.getText().isEmpty() || usernameField.getText().isEmpty()) {
            mostrarAlerta("Debe llenar todos los campos!", Alert.AlertType.ERROR);
            return; // <- Necesario para evitar que siga ejecutando
        }

        Admin administrador = notificaciones.buscarUsuario(usernameField.getText());

        if (administrador != null) {
            sesion.setUsuario(administrador);
            mostrarAlerta("Bienvenido " + administrador.getNombre(), Alert.AlertType.INFORMATION);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                        "/co/edu/uniquindio/poo/sistemas_notificacion/Aplications/admin/AplicacionAdministrador.fxml"));
                Scene newScene = new Scene(loader.load());

                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentStage.setScene(newScene);
                currentStage.setTitle("Administrador");
            } catch (IOException e) {
                mostrarAlerta("Error al cargar la ventana de administrador.", Alert.AlertType.ERROR);
                e.printStackTrace();
            }

            return; // <- Opcionalmente, evitar el siguiente if innecesario
        }

        // Si no encontró usuario
        mostrarAlerta("No encontrado", Alert.AlertType.ERROR);
    }


    /**
     * Accion al oprimir el boton volver
     * @param event
     */
    @FXML
    void onVolverClick(ActionEvent event) {

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
    void initialize() {
        assert atrasBoton != null : "fx:id=\"atrasBoton\" was not injected: check your FXML file 'LoginAdministrador.fxml'.";
        assert errorLabel != null : "fx:id=\"errorLabel\" was not injected: check your FXML file 'LoginAdministrador.fxml'.";
        assert inicioSesionBoton != null : "fx:id=\"inicioSesionBoton\" was not injected: check your FXML file 'LoginAdministrador.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'LoginAdministrador.fxml'.";
        assert usernameField != null : "fx:id=\"usernameField\" was not injected: check your FXML file 'LoginAdministrador.fxml'.";

    }

}
