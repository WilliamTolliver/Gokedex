package wttechnologies.com.pokedextest;

/**
 * Created by William on 7/15/2016.
 */
public class Pokemon {

    //Declarations
    int id, imageId;
    String name, type1, type2;

    public Pokemon(int id, String name, String type1, String type2, int imageId) {
        this.id = id;
        this.name = name;
        this.imageId = imageId;
        this.type1 = type1;
        this.type2 = type2;
    }

    public Pokemon(){}

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

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", imageId=" + imageId +
                ", name='" + name + '\'' +
                ", type1='" + type1 + '\'' +
                ", type2='" + type2 + '\'' +
                '}';
    }
}
