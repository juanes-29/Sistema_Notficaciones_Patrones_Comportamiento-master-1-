package co.edu.uniquindio.poo.sistemas_notificacion.model;

public class StrategyPush extends NotificationStrategy{

    @Override
    public String buildNotification(User usuario) {
        String mensaje = "";
        mensaje = "Notificación push de Sistema de Notificaciones al usuario: " + usuario.getNombre() ;
        return mensaje;
    }

    public StrategyPush() {
    }

}
