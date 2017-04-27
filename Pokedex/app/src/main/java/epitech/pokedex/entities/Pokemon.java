package epitech.pokedex.entities;

import java.util.ArrayList;

public class Pokemon {
    private int id;
    private String name;
    // Il peut y avoir un ou deux type(s), je n'ai besoin que de leurs noms
    private ArrayList<String> types_name;
    // Juste une image, la default_front
    private String default_sprite;
    private String height;
    private String weight;

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

    public String getDefault_sprite() {
        return default_sprite;
    }

    public void setDefault_sprite(String default_sprite) {
        this.default_sprite = default_sprite;
    }

    public ArrayList<String> getTypes_name() {
        return types_name;
    }

    public void setTypes_name(ArrayList<String> types) {
        this.types_name = types;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
