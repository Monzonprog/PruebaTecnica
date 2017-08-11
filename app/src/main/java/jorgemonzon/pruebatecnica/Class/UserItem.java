package jorgemonzon.pruebatecnica.Class;

/**
 * Created by jorge on 11/08/17.
 */

public class UserItem {

    private String $type;
    private int ID;
    private String Name;
    private DateTime Birthdate;

    public UserItem(){

    }

    public void set$type(String $type) {
        this.$type = $type;
    }

    public String get$type() {
        return $type;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public DateTime getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(DateTime birthdate) {
        Birthdate = birthdate;
    }
}
