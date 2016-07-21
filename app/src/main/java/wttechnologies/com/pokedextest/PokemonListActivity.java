package wttechnologies.com.pokedextest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import utilities.ConfigureViews;

/**
 * Created by William on 7/21/2016.
 */
public class PokemonListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);

        // Create GridView and get list of Pokemon
        GridView gridView = (GridView) findViewById(R.id.grid_view);
        ConfigureViews.setGridView(this, gridView);


        // Instance of ImageAdapter Class
        ImageAdapter adapter = new ImageAdapter(this);
        gridView.setAdapter(adapter);

    }
}
