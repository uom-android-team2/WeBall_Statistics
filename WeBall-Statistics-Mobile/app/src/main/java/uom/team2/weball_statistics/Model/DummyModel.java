package uom.team2.weball_statistics.Model;

/*
 *  Dummy model only for placeholder
 */
public class DummyModel {
    private Long id;
    private String name;
    private String email;

    public DummyModel(Long id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
