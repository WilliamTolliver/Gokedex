package wttechnologies.com.pokedextest;

import java.io.Serializable;
import java.util.List;

/**
 * Created by William on 7/16/2016.
 */
public class NormalAttack implements Serializable {

    //Declarations
    String name, type, known_by;
    int damage;

    public NormalAttack(String name, String type, int damage, String known_by) {
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.known_by = known_by;
    }

    public NormalAttack(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public String getKnown_by() {
        return known_by;
    }

    public void setKnown_by(String known_by) {
        this.known_by = known_by;
    }

    @Override
    public String toString() {
        return "NormalAttack{" +
                "known_by=" + known_by +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", damage=" + damage +
                '}';
    }
}
