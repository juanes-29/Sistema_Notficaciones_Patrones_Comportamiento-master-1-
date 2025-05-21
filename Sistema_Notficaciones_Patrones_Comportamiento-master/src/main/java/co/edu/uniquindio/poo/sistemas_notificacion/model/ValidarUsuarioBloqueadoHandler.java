package co.edu.uniquindio.poo.sistemas_notificacion.model;

public class ValidarUsuarioBloqueadoHandler extends NotificacionHandler{
    @Override
    public void handle(Notificacion notificacion) throws Exception {
        if (notificacion.getUsuario().isEstaBloqueado()) {
            throw new Exception("El usuario está bloqueado.");
        }
        super.handle(notificacion);
    }
}
