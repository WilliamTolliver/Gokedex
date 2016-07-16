package wttechnologies.com.pokedextest;

/**
 * Created by William on 7/16/2016.
 */
public class SpecialAttack {

    //Declarations
    String name, type;
    int damage, energy;

    public SpecialAttack(String name, String type, int damage, int energy) {
        this.name = name;
        this.type = type;
        this.damage = damage;
        this.energy = energy;
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

    @Override
    public String toString() {
        return "SpecialAttack{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", damage=" + damage +
                ", energy=" + energy +
                '}';
    }
}
