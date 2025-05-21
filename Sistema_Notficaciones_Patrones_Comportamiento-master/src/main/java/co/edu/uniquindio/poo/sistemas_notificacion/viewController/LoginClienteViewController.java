package co.edu.uniquindio.poo.sistemas_notificacion.viewController;

import co.edu.uniquindio.poo.sistemas_notificacion.model.Cliente;
import co.edu.uniquindio.poo.sistemas_notificacion.model.Sesion;
import co.edu.uniquindio.poo.sistemas_notificacion.model.SistemaNotificaciones;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginClienteViewController {

    @FXML
    private Button loginBoton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Button volverBoton;

    @FXML
    private Label errorLabel;

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

        if(passwordField.getText().isEmpty() || usernameField.getText().isEmpty()) {
            mostrarAlerta("Debe llenar todos los campos!", Alert.AlertType.ERROR);
        }

        Cliente cliente = notificaciones.buscarUsuario(usernameField.getText());

        if(cliente != null) {
            sesion.setUsuario(cliente);
            mostrarAlerta("Bienvenido " + cliente.getNombre(), Alert.AlertType.INFORMATION);
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/sistemas_notificacion/Aplications/cliente/AplicacionCliente.fxml"));
                Scene newScene = new Scene(loader.load());

                Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                currentStage.setScene(newScene);
                currentStage.setTitle("Login");

            } catch (IOException e) {
                System.out.println("Error al cargar la nueva ventana: " + e.getMessage());
                e.printStackTrace();
            }
        }

        if (cliente == null) {
            mostrarAlerta("No encontrado", Alert.AlertType.ERROR);
        }

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
        assert errorLabel != null : "fx:id=\"errorLabel\" was not injected: check your FXML file 'LoginCliente.fxml'.";
        assert loginBoton != null : "fx:id=\"loginBoton\" was not injected: check your FXML file 'LoginCliente.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'LoginCliente.fxml'.";
        assert usernameField != null : "fx:id=\"usernameField\" was not injected: check your FXML file 'LoginCliente.fxml'.";
        assert volverBoton != null : "fx:id=\"volverBoton\" was not injected: check your FXML file 'LoginCliente.fxml'.";

    }

}
