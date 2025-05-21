package co.edu.uniquindio.poo.sistemas_notificacion.model;

public class Invitado extends User{

    public Invitado(String nombre, String email, String telefono, Template template, NotificationStrategy preferredStrategy) {
        super(nombre, email, telefono, template, preferredStrategy);
    }
}
