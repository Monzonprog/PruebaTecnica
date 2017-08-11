package jorgemonzon.pruebatecnica.Interfaces;

import jorgemonzon.pruebatecnica.Class.ListaUsersObject;

/**
 * Created by jorge on 11/08/17.
 */

public interface IDataListaUsers {

    void conexionCorrecta(ListaUsersObject ListaUsuarios);
    void conexionIncorrecta();
}
