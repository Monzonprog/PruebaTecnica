package jorgemonzon.pruebatecnica.Class;

import java.util.List;

/**
 * Created by jorge on 11/08/17.
 */

public class ListaUsersObject {

    private List<UserItem> ListaUsuarios;

    public ListaUsersObject() {
    }

    public List<UserItem> getListaUsuarios() {
        return ListaUsuarios;
    }

    public void setListaUsuarios(List<UserItem> listaUsuarios) {
        ListaUsuarios = listaUsuarios;
    }
}
