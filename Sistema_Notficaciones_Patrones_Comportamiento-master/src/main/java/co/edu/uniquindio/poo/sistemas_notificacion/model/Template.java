package co.edu.uniquindio.poo.sistemas_notificacion.model;

public abstract class Template {


    public Template() {
    }

    public final String formatMessage(User usuario ,String mensajeBase) {
        return generarEncabezado(usuario) + "\n" + mensajeBase + "\n" + generarPie(usuario);
    }

    protected abstract String generarEncabezado(User usuario);
    protected abstract String generarPie(User usuario);
}
