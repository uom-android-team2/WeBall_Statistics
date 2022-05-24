package uom.team2.weball_statistics.Model;

/*
 * @author Minas - Theodoros Charakopoulos ics20072
 */
public class Player {
    private int id;
    private String name;
    private String surname;
    private int number; //Player's number
    private PlayerPosition playerPosition; //POINT_GUARD, SHOOTING_GUARD, SMALL_FORWARD, POWER_FORWARD, CENTER
    private Team team; //The team onj that the player plays in
    private String imagePath;
    //Other fields for Statistics that made in other branch...

    public Player(int id, String name, String surname, int number, PlayerPosition playerPosition, Team team, String imagePath) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.number = number;
        this.playerPosition = playerPosition;
        this.team = team;
        this.imagePath = imagePath;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public PlayerPosition getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(PlayerPosition playerPosition) {
        this.playerPosition = playerPosition;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
