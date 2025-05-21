package co.edu.uniquindio.poo.sistemas_notificacion.model;

public class ClinteTemplate extends Template {

    @Override
    protected String generarEncabezado(User usuario) {
        return "==============================\n"
                + "🎉 ¡Hola " + usuario.getNombre() + ", Cliente VIP! 🎉\n"
                + "==============================";
    }

    @Override
    protected String generarPie(User usuario) {
        return "------------------------------\n"
                + "No pierdas nuestras ofertas exclusivas, " + usuario.getNombre() + ".\n"
                + "¡Nos vemos en el próximo evento!\n"
                + "------------------------------";
    }

}
