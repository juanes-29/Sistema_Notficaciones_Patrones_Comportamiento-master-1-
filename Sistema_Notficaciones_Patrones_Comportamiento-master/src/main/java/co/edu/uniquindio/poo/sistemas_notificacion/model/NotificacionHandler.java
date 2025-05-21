package co.edu.uniquindio.poo.sistemas_notificacion.model;

public class NotificacionHandler {

    protected NotificacionHandler next;

    public void setNext(NotificacionHandler next) {
        this.next = next;
    }

    public void handle(Notificacion notificacion) throws Exception {
        if (next != null) {
            next.handle(notificacion);
        }
    }

}
