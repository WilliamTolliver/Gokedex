package wttechnologies.com.pokedextest;

/**
 * Created by William on 7/16/2016.
 */
public class NormalAttack {

    //Declarations
    String name, type;
    int damage;

    public NormalAttack(String name, String type, int damage) {
        this.name = name;
        this.type = type;
        this.damage = damage;
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

    @Override
    public String toString() {
        return "NormalAttack{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", damage=" + damage +
                '}';
    }
}
