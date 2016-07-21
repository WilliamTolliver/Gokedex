package wttechnologies.com.pokedextest;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

import java.util.List;

import utilities.ConfigureViews;
import utilities.DatabaseUtil;

public class MainActivity extends AppCompatActivity {

    //Declarations
    boolean success;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        success = DatabaseUtil.createPokemonDB(this);

        // Create GridView and get list of Pokemon
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_main);
        ConfigureViews.setGridView(this, layout);

        Button mapsButton = (Button) findViewById(R.id.maps_button);
        mapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listIntent = new Intent(getBaseContext(), MapsActivity.class);
                startActivity(listIntent);
            }
        });

        Button listButton = (Button) findViewById(R.id.list_button);
        Context ctx = this;
        listButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapsIntent = new Intent(getBaseContext(), PokemonListActivity.class);
                startActivity(mapsIntent);
            }
        });
    }

}
