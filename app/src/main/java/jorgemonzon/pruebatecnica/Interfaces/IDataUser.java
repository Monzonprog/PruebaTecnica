package jorgemonzon.pruebatecnica.Interfaces;

import jorgemonzon.pruebatecnica.Class.UserItem;

/**
 * Created by jorge on 11/08/17.
 */

public interface IDataUser {

    void conexionCorrecta(UserItem Usuario);
    void conexionIncorrecta();
    void conexionNoEncontrado();

}
