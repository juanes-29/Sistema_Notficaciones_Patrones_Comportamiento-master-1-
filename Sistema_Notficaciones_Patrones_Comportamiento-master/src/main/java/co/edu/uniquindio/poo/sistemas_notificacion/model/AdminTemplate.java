package co.edu.uniquindio.poo.sistemas_notificacion.model;

public class AdminTemplate extends  Template {

    @Override
    protected String generarEncabezado(User usuario) {
        return "==============================\n"
                + "🚨 Alerta Administrador, " + usuario.getNombre() + " 🚨\n"
                + "==============================";
    }

    @Override
    protected String generarPie(User usuario) {
        return "------------------------------\n"
                + "Gracias por mantener el sistema en funcionamiento.\n"
                + "¡Sigue con tu gran trabajo, " + usuario.getNombre() + "!\n"
                + "------------------------------";
    }

}
