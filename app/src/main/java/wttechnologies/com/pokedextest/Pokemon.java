package wttechnologies.com.pokedextest;

import java.io.Serializable;
import java.util.List;

/**
 * Created by William on 7/15/2016.
 */
public class Pokemon implements Serializable {

    //Declarations
    int id, imageId;
    String name, type1, type2;
    List<NormalAttack> normals;
    List<SpecialAttack> specials;

    public Pokemon(int id, String name, String type1, String type2,int imageId, List<NormalAttack> normals, List<SpecialAttack> specials) {
        this.id = id;
        this.imageId = imageId;
        this.name = name;
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

    public List<NormalAttack> getNormals() {
        return normals;
    }

    public void setNormals(List<NormalAttack> normals) {
        this.normals = normals;
    }

    public List<SpecialAttack> getSpecials() {
        return specials;
    }

    public void setSpecials(List<SpecialAttack> specials) {
        this.specials = specials;
    }

    @Override
    public String toString() {
        return "Pokemon{" +
                "id=" + id +
                ", imageId=" + imageId +
                ", name='" + name + '\'' +
                ", type1='" + type1 + '\'' +
                ", type2='" + type2 + '\'' +
                ", normals=" + normals +
                ", specials=" + specials +
                '}';
    }
}
