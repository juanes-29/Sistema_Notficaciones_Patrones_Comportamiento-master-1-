package co.edu.uniquindio.poo.sistemas_notificacion.model;

public class StrategyEmail extends NotificationStrategy{

    @Override
    public String buildNotification(User usuario) {
        String mensaje = "";
        mensaje = "Enviando notificación por email: " + usuario.getEmail();
        return mensaje;
    }


    public StrategyEmail() {
    }

}

