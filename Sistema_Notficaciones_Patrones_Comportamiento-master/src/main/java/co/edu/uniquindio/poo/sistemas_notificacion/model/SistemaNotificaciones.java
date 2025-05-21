package co.edu.uniquindio.poo.sistemas_notificacion.model;

import java.util.LinkedList;

public class SistemaNotificaciones {

    private LinkedList<Notificacion> notificaciones;
    private LinkedList<User> usuarios;
    private static SistemaNotificaciones instance;
    private EventoInformacion eventoInformacion;
    private EventoPromocion eventoPromocion;
    private EventoSeguridad eventoSeguridad;

    private SistemaNotificaciones() {
        notificaciones = new LinkedList<>();
        usuarios = new LinkedList<>();
        eventoInformacion = new EventoInformacion();
        eventoPromocion = new EventoPromocion();
        eventoSeguridad = new EventoSeguridad();
    }

    public static  SistemaNotificaciones getInstance() {
        if (instance == null) {
            instance = new SistemaNotificaciones();
        }
        return instance;
    }

    // CRUD: Buscar un objeto <T> por nombre
    public <T> T buscarUsuario(String nombre) {
            for (User usuario : usuarios) {
                if (usuario.getNombre().equals(nombre)) {
                    return (T) usuario; // Devuelve el objeto User encontrado
                }
            }

        return null;
    }

    // CRUD: Añadir un objeto <T> a la lista indicada
    public <T> void añadir(T item, boolean isUsuario) {
        if (isUsuario) {
            if (item instanceof User) {
                usuarios.add((User) item);
            }
        } else {
            if (item instanceof Notificacion) {
                notificaciones.add((Notificacion) item);
            }
        }
    }

    // CRUD: Eliminar un objeto <T> de la lista indicada
    public <T> boolean eliminar(T item, boolean isUsuario) {
        if (isUsuario) {
            return usuarios.remove(item);
        } else {
            return notificaciones.remove(item);
        }
    }



    public String generarMensajeBase(EventManager evento, String mensaje) {
        evento.setMensajeEvento(mensaje);
        return "mensaje set to: " + evento.getMensajeEvento();
    }

    private String generarNotificacion(User user, EventManager evento) {
        String mensajeBase = evento.getMensajeEvento();
        String mensajeFormateado = user.getTemplate().formatMessage(user, mensajeBase);
        String metodoEnvio = user.getPreferredStrategy().buildNotification(user);

        return mensajeFormateado + "\n\n" + metodoEnvio;
    }


    public void generarNotificaciones(EventManager evento){
        for (User suscriptor : evento.getSuscriptores()){
            String mensajeFinal = generarNotificacion(suscriptor, evento);


            Notificacion notificacion = new Notificacion(suscriptor, mensajeFinal);
            ComandoNotificacion comando = new EnviarNotificacionCommand(notificacion);

            try {
                comando.ejecutar();
            } catch (Exception e) {
                System.out.println("Error al enviar notificación a " + suscriptor.getNombre() + ": " + e.getMessage());
            }
        }
    }


    public LinkedList<Notificacion> getNotificaciones() {
        return notificaciones;
    }

    public LinkedList<User> getUsuarios() {
        return usuarios;
    }

    public EventoInformacion getEventoInformacion() {
        return eventoInformacion;
    }

    public EventoPromocion getEventoPromocion() {
        return eventoPromocion;
    }

    public EventoSeguridad getEventoSeguridad() {
        return eventoSeguridad;
    }
}
