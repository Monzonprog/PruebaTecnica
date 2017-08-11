package jorgemonzon.pruebatecnica.Interfaces;

import java.util.List;

import jorgemonzon.pruebatecnica.Class.ListaUsersObject;
import jorgemonzon.pruebatecnica.Class.UserItem;

/**
 * Created by jorge on 11/08/17.
 */

public interface IDataListaUsers {

    void conexionCorrecta(List<UserItem> ListaUsuarios);
    void conexionIncorrecta();
}
