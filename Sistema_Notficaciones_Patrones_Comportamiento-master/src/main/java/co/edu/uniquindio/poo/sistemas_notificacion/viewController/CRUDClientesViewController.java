package co.edu.uniquindio.poo.sistemas_notificacion.viewController;

import co.edu.uniquindio.poo.sistemas_notificacion.model.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class CRUDClientesViewController {

    @FXML private TextField campoUsuario;
    @FXML private PasswordField campoContrasena;
    @FXML private TextField campoCorreo;
    @FXML private TextField campoNombre;
    @FXML private TextField campoTelefono;
    @FXML private ComboBox<String> comboTemplate;
    @FXML private ComboBox<String> comboEstrategia;
    @FXML private Button btnCrear;
    @FXML private Button btnActualizar;
    @FXML private Button btnEliminar;
    @FXML private Button btnVolver;

    @FXML private TableView<Cliente> tablaClientes;
    @FXML private TableColumn<Cliente, String> colUsuario;
    @FXML private TableColumn<Cliente, String> colContrasena;
    @FXML private TableColumn<Cliente, String> colCorreo;

    private final ObservableList<Cliente> listaClientes = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colUsuario.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombreUsuario()));
        colContrasena.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getContraseña()));
        colCorreo.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));

        tablaClientes.setItems(listaClientes);
        comboTemplate.getItems().add("Cliente");
        comboEstrategia.getItems().addAll("EMAIL", "SMS", "PUSH");
    }

    @FXML
    public void onCrear() {
        String usuario = campoUsuario.getText();
        String contrasena = campoContrasena.getText();
        String correo = campoCorreo.getText();
        String nombre = campoNombre.getText();
        String telefono = campoTelefono.getText();
        String tipoTemplate = comboTemplate.getValue();
        String tipoEstrategia = comboEstrategia.getValue();

        if (tipoTemplate == null || tipoEstrategia == null) {
            mostrarAlerta("Debes seleccionar un template y una estrategia.");
            return;
        }

        Template template = obtenerTemplate(tipoTemplate);
        NotificationStrategy estrategia = obtenerEstrategia(tipoEstrategia);

        Cliente nuevoCliente = new Cliente(nombre, correo, telefono, template, estrategia, usuario, contrasena);
        listaClientes.add(nuevoCliente);
        limpiarCampos();
    }

    @FXML
    public void onActualizar() {
        Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            String tipoTemplate = comboTemplate.getValue();
            String tipoEstrategia = comboEstrategia.getValue();

            if (tipoTemplate == null || tipoEstrategia == null) {
                mostrarAlerta("Debes seleccionar un template y una estrategia.");
                return;
            }

            seleccionado.setNombreUsuario(campoUsuario.getText());
            seleccionado.setContraseña(campoContrasena.getText());
            seleccionado.setEmail(campoCorreo.getText());
            seleccionado.setNombre(campoNombre.getText());
            seleccionado.setTelefono(campoTelefono.getText());
            seleccionado.setTemplate(obtenerTemplate(tipoTemplate));
            seleccionado.setPreferredStrategy(obtenerEstrategia(tipoEstrategia));

            tablaClientes.refresh();
            limpiarCampos();
        }
    }

    @FXML
    public void onEliminar() {
        Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            listaClientes.remove(seleccionado);
        }
    }

    private void limpiarCampos() {
        campoUsuario.clear();
        campoContrasena.clear();
        campoCorreo.clear();
        campoNombre.clear();
        campoTelefono.clear();
        comboTemplate.getSelectionModel().clearSelection();
        comboEstrategia.getSelectionModel().clearSelection();
    }

    private Template obtenerTemplate(String tipo) {
        return new ClinteTemplate();
    }

    private NotificationStrategy obtenerEstrategia(String tipo) {
        return switch (tipo.trim().toUpperCase()) {
            case "EMAIL" -> new StrategyEmail();
            case "SMS" -> new StrategySMS();
            case "PUSH" -> new StrategyPush();
            default -> new StrategyEmail(); // Valor por defecto
        };
    }

    private void mostrarAlerta(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advertencia");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    @FXML
    public void onVolver(){
        // deberia ir a la pestaña de aplicacionadmin otra vez
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/sistemas_notificacion/Aplications/admin/AplicacionAdministrador.fxml"));
            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) btnVolver.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Panel Administrador");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error al cargar la vista de administrador.");
        }
    }
    }


