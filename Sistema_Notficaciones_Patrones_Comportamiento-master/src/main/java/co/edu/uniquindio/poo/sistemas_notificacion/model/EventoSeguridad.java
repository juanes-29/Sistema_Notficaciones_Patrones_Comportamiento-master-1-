package co.edu.uniquindio.poo.sistemas_notificacion.model;

public class EventoSeguridad extends EventManager {

    public EventoSeguridad() {
        super();
        setTipoEvento("Seguridad");
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
        return "Evento de Seguridad";
    }

}
