package co.edu.uniquindio.poo.sistemas_notificacion;

import co.edu.uniquindio.poo.sistemas_notificacion.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/sistemas_notificacion/Logins/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {


        ///  Invitado funciona
        Sesion sesion = Sesion.getInstance();

        SistemaNotificaciones sistemaNotificaciones = SistemaNotificaciones.getInstance();

        Invitado invitado = Sesion.getInstance().getInvitado();

        invitado.agregarSuscripcion(sistemaNotificaciones.getEventoPromocion());

        sistemaNotificaciones.añadir(invitado, true);

        sistemaNotificaciones.generarMensajeBase(sistemaNotificaciones.getEventoPromocion(), "Promocion Disponible!");

        sistemaNotificaciones.generarNotificaciones(sistemaNotificaciones.getEventoPromocion());


        ///  Creacion de varios clientes y usuarios

        ClinteTemplate clienteTemplate = new ClinteTemplate();
        AdminTemplate adminTemplate = new AdminTemplate();

        StrategySMS strategySMS = new StrategySMS();
        StrategyEmail strategyEmail = new StrategyEmail();
        StrategyPush strategyPush = new StrategyPush();

        Cliente cliente = new Cliente("Juan", "juan@email.com", "1234", clienteTemplate, strategySMS, "juan", "123");

        Cliente cliente1 = new Cliente("Luis Alberto", "juan@email.com", "1234", clienteTemplate, strategySMS, "juan", "123");
        Cliente cliente2 = new Cliente("Laura", "laura@email.com", "5678", clienteTemplate, strategyPush, "laura", "456");
        Cliente cliente3 = new Cliente("Carlos", "carlos@email.com", "9101", clienteTemplate, strategyEmail, "carlos", "789");
        Cliente cliente4 = new Cliente("Ana", "ana@email.com", "1121", clienteTemplate, strategyPush, "ana", "321");
        Cliente cliente5 = new Cliente("Pedro", "pedro@email.com", "3141", clienteTemplate, strategySMS, "pedro", "654");
        Cliente cliente6 = new Cliente("Marta", "marta@email.com", "5161", clienteTemplate, strategyPush, "marta", "987");


        Admin admin = new Admin("Juan", "juan@email.com", "1234", adminTemplate , strategyEmail);

        Admin admin1 = new Admin("Jaime", "juan@email.com", "1234", adminTemplate, strategySMS);
        Admin admin2 = new Admin("Sofia", "sofia@email.com", "2345", adminTemplate, strategyEmail);
        Admin admin3 = new Admin("Luis", "luis@email.com", "3456", adminTemplate, strategyPush);
        Admin admin4 = new Admin("Camila", "camila@email.com", "4567", adminTemplate, strategyEmail);
        Admin admin5 = new Admin("Diego", "diego@email.com", "5678", adminTemplate, strategyPush);
        Admin admin6 = new Admin("Elena", "elena@email.com", "6789", adminTemplate, strategySMS);


        invitado.agregarSuscripcion(sistemaNotificaciones.getEventoPromocion());

        // Suscripciones para clientes
        cliente.agregarSuscripcion(sistemaNotificaciones.getEventoPromocion());
        cliente1.agregarSuscripcion(sistemaNotificaciones.getEventoPromocion());
        cliente2.agregarSuscripcion(sistemaNotificaciones.getEventoPromocion());
        cliente3.agregarSuscripcion(sistemaNotificaciones.getEventoPromocion());
        cliente4.agregarSuscripcion(sistemaNotificaciones.getEventoPromocion());
        cliente5.agregarSuscripcion(sistemaNotificaciones.getEventoPromocion());
        cliente6.agregarSuscripcion(sistemaNotificaciones.getEventoPromocion());

// Suscripciones para administradores
        admin.agregarSuscripcion(sistemaNotificaciones.getEventoPromocion());
        admin1.agregarSuscripcion(sistemaNotificaciones.getEventoPromocion());
        admin2.agregarSuscripcion(sistemaNotificaciones.getEventoPromocion());
        admin3.agregarSuscripcion(sistemaNotificaciones.getEventoPromocion());
        admin4.agregarSuscripcion(sistemaNotificaciones.getEventoPromocion());
        admin5.agregarSuscripcion(sistemaNotificaciones.getEventoPromocion());
        admin6.agregarSuscripcion(sistemaNotificaciones.getEventoPromocion());



        sistemaNotificaciones.añadir(cliente, true);

        sistemaNotificaciones.añadir(cliente1, true);
        sistemaNotificaciones.añadir(cliente2, true);
        sistemaNotificaciones.añadir(cliente3, true);
        sistemaNotificaciones.añadir(cliente4, true);
        sistemaNotificaciones.añadir(cliente5, true);
        sistemaNotificaciones.añadir(cliente6, true);


        sistemaNotificaciones.añadir(admin, true);

        sistemaNotificaciones.añadir(admin1, true);
        sistemaNotificaciones.añadir(admin2, true);
        sistemaNotificaciones.añadir(admin3, true);
        sistemaNotificaciones.añadir(admin4, true);
        sistemaNotificaciones.añadir(admin5, true);
        sistemaNotificaciones.añadir(admin6, true);






        launch();
    }



}