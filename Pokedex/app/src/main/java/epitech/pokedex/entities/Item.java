package epitech.pokedex.entities;

public class Item {
    private int id;
    private String name;
    private int cost;
    // Uniquement l'url de la default
    private String default_sprite;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getDefault_sprite() {
        return default_sprite;
    }

    public void setDefault_sprite(String default_sprite) {
        this.default_sprite = default_sprite;
    }
}
