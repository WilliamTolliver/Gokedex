package wttechnologies.com.pokedextest;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by William on 7/16/2016.
 */
public class PokemonButton extends ImageView {

    //Declarations
    Pokemon pokemon;


    public PokemonButton(Context c, Pokemon pokemon){
        super(c);
        this.pokemon = pokemon;
    }

    public Pokemon getPokemon() {
        return pokemon;
    }

    public void setPokemon(Pokemon pokemon) {
        this.pokemon = pokemon;
    }

    @Override
    public String toString() {
        return "PokemonButton{" +
                "pokemon=" + pokemon +
                '}';
    }
}
