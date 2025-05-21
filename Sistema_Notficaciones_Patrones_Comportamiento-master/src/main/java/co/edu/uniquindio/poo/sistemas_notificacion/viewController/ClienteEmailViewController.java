package co.edu.uniquindio.poo.sistemas_notificacion.viewController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import co.edu.uniquindio.poo.sistemas_notificacion.model.Notificacion;
import co.edu.uniquindio.poo.sistemas_notificacion.model.Sesion;
import co.edu.uniquindio.poo.sistemas_notificacion.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ClienteEmailViewController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnVolver;

    @FXML
    private TableColumn<Notificacion, String> columnaMensaje;

    @FXML
    private Label lblNombreUsuario;

    @FXML
    private TableView<Notificacion> tablaNotificaciones;

    User usuario = Sesion.getInstance().getUsuario();

    @FXML
    public void onVolver(ActionEvent actionEvent) {

        Sesion.getInstance().setUsuario(null);
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/sistemas_notificacion/Logins/Login.fxml"));
            Scene newScene = new Scene(loader.load());

            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            currentStage.setScene(newScene);
            currentStage.setTitle("Login");

        } catch (IOException e) {
            System.out.println("Error al cargar la nueva ventana: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        assert btnVolver != null : "fx:id=\"btnVolver\" was not injected: check your FXML file 'ClienteEmail.fxml'.";
        assert columnaMensaje != null : "fx:id=\"columnaMensaje\" was not injected: check your FXML file 'ClienteEmail.fxml'.";
        assert lblNombreUsuario != null : "fx:id=\"lblNombreUsuario\" was not injected: check your FXML file 'ClienteEmail.fxml'.";
        assert tablaNotificaciones != null : "fx:id=\"tablaNotificaciones\" was not injected: check your FXML file 'ClienteEmail.fxml'.";


        // Mostrar nombre del usuario en el Label
        lblNombreUsuario.setText("Usuario: " + usuario.getNombre());

        // Configurar la columna para mostrar el mensaje
        columnaMensaje.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getMensaje()));

        // Asignar la lista de notificaciones del usuario a la tabla
        tablaNotificaciones.setItems(javafx.collections.FXCollections.observableArrayList(usuario.getNotificaciones()));
    }


}


