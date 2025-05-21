package co.edu.uniquindio.poo.sistemas_notificacion.model;

public class EventoInformacion extends EventManager {

    public EventoInformacion() {
        super();
        setTipoEvento("Informacion");
    }

    @Override
    public void añadirSuscriptor(User suscriptor) {
        if (suscriptor != null) {
            getSuscriptores().add(suscriptor);
        }
    }

    @Override
    public void eliminarSuscriptor(User suscriptor) {
        if (suscriptor != null) {
            getSuscriptores().remove(suscriptor);
        }
    }

    @Override
    public String toString() {
        return "Evento de Informacion";
    }

}
