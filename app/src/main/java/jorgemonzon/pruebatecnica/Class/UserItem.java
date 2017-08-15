package jorgemonzon.pruebatecnica.Class;

/**
 * Created by jorge on 11/08/17.
 */

public class UserItem {

    private int id;
    private String name;
    private String birthdate;

    public UserItem(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {

        String fechaMostrar = birthdate.replace("T", " ");

        return fechaMostrar;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public String toString() {
        return "UserItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthdate='" + birthdate + '\'' +
                '}';
    }
}
