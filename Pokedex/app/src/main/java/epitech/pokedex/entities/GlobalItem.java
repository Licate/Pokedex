package epitech.pokedex.entities;

public class GlobalItem {
    private int id;
    private String name;
    private String default_sprite;

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

    public String getDefault_sprite() {
        return default_sprite;
    }

    public void setDefault_sprite(String default_sprite) {
        this.default_sprite = default_sprite;
    }
}
