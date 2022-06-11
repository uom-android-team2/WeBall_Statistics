package uom.team2.weball_statistics.Model;

import java.io.Serializable;

/*
 * @author Minas - Theodoros Charakopoulos ics20072
 */
public class Coach implements Serializable {
    private int id;
    private int coachedTeamID; //The obj Team that he coaching
    private String firstname;
    private String surname;
    private boolean isHeadCoach;
    private String imagePath;

    public Coach(int id, int coachedTeamID, String firstname, String surname, boolean isHeadCoach, String imagePath) {
        this.id = id;
        this.coachedTeamID = coachedTeamID;
        this.firstname = firstname;
        this.surname = surname;
        this.isHeadCoach = isHeadCoach;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoachedTeamID() {
        return coachedTeamID;
    }

    public void setCoachedTeamID(int coachedTeamID) {
        this.coachedTeamID = coachedTeamID;
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

    public boolean isHeadCoach() {
        return isHeadCoach;
    }

    public void setHeadCoach(boolean headCoach) {
        isHeadCoach = headCoach;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
