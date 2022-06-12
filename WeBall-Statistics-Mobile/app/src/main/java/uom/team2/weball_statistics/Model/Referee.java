package uom.team2.weball_statistics.Model;

import java.io.Serializable;

/*
 * @author Minas - Theodoros Charakopoulos ics20072
 */
public class Referee implements Serializable {
    private int id;
    private String firstname;
    private  String surname;

    public Referee(int id, String firstname, String surname) {
        this.id = id;
        this.firstname = firstname;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
