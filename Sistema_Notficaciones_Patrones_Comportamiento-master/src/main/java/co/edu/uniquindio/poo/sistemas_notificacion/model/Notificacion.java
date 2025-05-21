
package co.edu.uniquindio.poo.sistemas_notificacion.model;

public class Notificacion {
    private User usuarioDestino;
    private String mensaje;

    public Notificacion(User usuario, String mensaje) {
        this.usuarioDestino = usuario;
        this.mensaje = mensaje;
    }

    public User getUsuario() {
        return usuarioDestino;
    }

    public String getMensaje() {
        return mensaje;
    }
}
