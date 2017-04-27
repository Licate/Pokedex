package epitech.pokedex.entities;

public class Berry {
    private int id;
    private String name;
    private int growth_time;
    private int size;


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

    public int getGrowth_time() {
        return growth_time;
    }

    public void setGrowth_time(int growth_time) {
        this.growth_time = growth_time;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
