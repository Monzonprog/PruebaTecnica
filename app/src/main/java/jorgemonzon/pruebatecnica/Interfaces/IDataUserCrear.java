package jorgemonzon.pruebatecnica.Interfaces;

import jorgemonzon.pruebatecnica.Class.UserItem;

/**
 * Created by jorge on 15/08/17.
 */

public interface IDataUserCrear {

    void conexionCorrectaCrearUser(UserItem Usuario);

    void conexionIncorrectaCrearUser();
}
