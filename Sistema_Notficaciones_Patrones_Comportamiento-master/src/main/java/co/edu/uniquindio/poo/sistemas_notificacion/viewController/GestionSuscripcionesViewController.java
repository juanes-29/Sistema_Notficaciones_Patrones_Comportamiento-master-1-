package co.edu.uniquindio.poo.sistemas_notificacion.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class GestionSuscripcionesViewController {
    @FXML
    private Button btnvolver;
    @FXML
    private Label lblUsuario;

    @FXML
    private TableView<?> tablaEventos;

    @FXML
    private TableColumn<?, ?> colTipoEvento;

    @FXML
    private Button btnSuscribirse;

    @FXML
    private Button btnDesuscribirse;

    @FXML
    private TableColumn<?, ?> colSuscrito;

    @FXML
    void onSuscribirseClick(ActionEvent event) {
        //logica para suscribirse

    }

    @FXML
    void onDesuscribirseClick(ActionEvent event) {
        //logica para desuscribirse

    }
    @FXML
    void onVolver(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/co/edu/uniquindio/poo/sistemas_notificacion/Aplications/cliente/AplicacionCliente.fxml"
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

