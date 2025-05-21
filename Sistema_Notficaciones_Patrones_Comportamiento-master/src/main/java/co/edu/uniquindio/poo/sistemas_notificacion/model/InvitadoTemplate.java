package co.edu.uniquindio.poo.sistemas_notificacion.model;

public class InvitadoTemplate extends  Template {
    @Override
    protected String generarEncabezado(User usuario) {
        return "==============================\n"
                + "👋 ¡Bienvenido, " + usuario.getNombre() + " (Invitado)!\n"
                + "==============================";
    }

    @Override
    protected String generarPie(User usuario) {
        return "------------------------------\n"
                + "Gracias por tu visita, " + usuario.getNombre() + ".\n"
                + "Esperamos verte nuevamente pronto.\n"
                + "------------------------------";
    }
}



