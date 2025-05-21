package co.edu.uniquindio.poo.sistemas_notificacion.model;

import java.util.LinkedList;

public abstract class User {

    private String nombre, email, telefono;
    private Template template;
    private NotificationStrategy preferredStrategy;
    private LinkedList<EventManager> suscripciones;
    private LinkedList<Notificacion> notificaciones;
    private boolean estaBloqueado = false;



    public User(String nombre, String email, String telefono, Template template, NotificationStrategy preferredStrategy) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.template = template;
        this.preferredStrategy = preferredStrategy;
        this.suscripciones = new LinkedList<>();
        this.notificaciones = new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }

    public NotificationStrategy getPreferredStrategy() {
        return preferredStrategy;
    }

    public void setPreferredStrategy(NotificationStrategy preferredStrategy) {
        this.preferredStrategy = preferredStrategy;
    }

    public LinkedList<EventManager> getSuscripciones() {
        return suscripciones;
    }

    public void setSuscripciones(LinkedList<EventManager> suscripciones) {
        this.suscripciones = suscripciones;
    }

    public LinkedList<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isEstaBloqueado() {
        return estaBloqueado;
    }

    public void setEstaBloqueado(boolean estaBloqueado) {
        this.estaBloqueado = estaBloqueado;
    }

    public void agregarSuscripcion(EventManager suscripcionConreta){
        suscripcionConreta.añadirSuscriptor(this);
        suscripciones.add(suscripcionConreta);
    }

    public void eliminarSuscripcion(EventManager suscripcionConreta){
        suscripcionConreta.eliminarSuscriptor(this);
        suscripciones.remove(suscripcionConreta);
    }


}
