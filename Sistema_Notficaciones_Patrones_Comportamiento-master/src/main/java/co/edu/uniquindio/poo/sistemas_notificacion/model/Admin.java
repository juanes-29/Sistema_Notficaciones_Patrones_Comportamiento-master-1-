package co.edu.uniquindio.poo.sistemas_notificacion.model;

import java.util.LinkedList;

public class Admin extends User {
    private LinkedList<Cliente> listaClientes;

    public Admin(String nombre, String email, String telefono, Template template, NotificationStrategy preferredStrategy) {
        super(nombre, email, telefono, template, preferredStrategy);
        this.listaClientes = new LinkedList<>();
    }

    public LinkedList<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(LinkedList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
}

