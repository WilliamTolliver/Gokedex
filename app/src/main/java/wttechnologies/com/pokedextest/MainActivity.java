package wttechnologies.com.pokedextest;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;

import java.util.List;

import utilities.ConfigureViews;
import utilities.DatabaseUtil;

public class MainActivity extends AppCompatActivity {

    //Declarations
    boolean success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);
        success = DatabaseUtil.createPokemonDB(this);
    }

    @Override
    protected void onStart(){
        super.onStart();
            // Create GridView and get list of Pokemon
            GridView gridView = (GridView) findViewById(R.id.grid_view);
            ConfigureViews.setGridView(this, gridView);

            //gridView.setBackground(getResources().getDrawable(R.drawable.teams2));
            List<Pokemon> pokemon = DatabaseUtil.setAttacks(this, DatabaseUtil.getPokemon(this, R.raw.list));

            // Instance of ImageAdapter Class
            ImageAdapter adapter = new ImageAdapter(this, pokemon);
            gridView.setAdapter(adapter);

    }
}
