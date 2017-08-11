package jorgemonzon.pruebatecnica.Class;

import java.util.ArrayList;

/**
 * Created by jorge on 11/08/17.
 */

public class ListaUsersObject {

    private ArrayList<UserItem> ListaUsuarios;

    public ListaUsersObject() {
    }

    public ArrayList<UserItem> getListaUsuarios() {
        return ListaUsuarios;
    }

    public void setListaUsuarios(ArrayList<UserItem> listaUsuarios) {
        ListaUsuarios = listaUsuarios;
    }
}
