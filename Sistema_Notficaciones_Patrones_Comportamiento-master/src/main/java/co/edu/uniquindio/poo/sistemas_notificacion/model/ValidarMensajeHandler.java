package co.edu.uniquindio.poo.sistemas_notificacion.model;

public class ValidarMensajeHandler extends  NotificacionHandler{
    @Override
    public void handle(Notificacion notificacion) throws Exception {
        if (notificacion.getMensaje() == null || notificacion.getMensaje().isBlank()) {
            throw new Exception("El mensaje está vacío.");
        }
        super.handle(notificacion);
    }
}
