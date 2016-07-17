package wttechnologies.com.pokedextest;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

/**
 * Created by William on 7/17/2016.
 */
public class PokemonInfoActivity extends AppCompatActivity {

    // Declarations

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_info_layout);

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            Pokemon poke = (Pokemon)getIntent().getSerializableExtra("pokemon");
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageResource(poke.getImageId());

            ListView listView = (ListView) findViewById(R.id.listView);
        }

    }
}
