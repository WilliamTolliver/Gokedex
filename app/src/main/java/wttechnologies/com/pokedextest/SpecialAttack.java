package wttechnologies.com.pokedextest;

import java.io.Serializable;

/**
 * Created by William on 7/16/2016.
 */
public class SpecialAttack implements Serializable {

    //Declarations
    String name, type, known_by;
    int damage, energy;

    public SpecialAttack(String name, String type, int damage, int energy, String known_by) {
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.energy = energy;
        this.known_by = known_by;
    }

    public SpecialAttack(){}

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

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public String getKnown_by() {
        return known_by;
    }

    public void setKnown_by(String known_by) {
        this.known_by = known_by;
    }

    @Override
    public String toString() {
        return "SpecialAttack{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", known_by='" + known_by + '\'' +
                ", damage=" + damage +
                ", energy=" + energy +
                '}';
    }
}
