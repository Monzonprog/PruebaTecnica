package jorgemonzon.pruebatecnica.Class;

/**
 * Created by jorge on 11/08/17.
 */

public class UserItem {

    private String $type;
    private int id;
    private String name;
    private DateTime birthdate;

    public UserItem(){

    }

    public void set$type(String $type) {
        this.$type = $type;
    }

    public String get$type() {
        return $type;
    }

    public int getID() {
        return id;
    }

    public void setID(int ID) {
        this.id = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }

    public DateTime getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(DateTime birthdate) {
        birthdate = birthdate;
    }
}
