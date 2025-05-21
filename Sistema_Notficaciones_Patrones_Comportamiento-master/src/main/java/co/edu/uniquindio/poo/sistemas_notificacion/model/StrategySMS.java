package co.edu.uniquindio.poo.sistemas_notificacion.model;

public class StrategySMS extends NotificationStrategy{

    @Override
    public String buildNotification(User usuario) {
        String mensaje = "";
        mensaje = "Enviando notificación por SMS al número: " + usuario.getTelefono() ;
        return mensaje;
    }

    public StrategySMS() {
    }
}
