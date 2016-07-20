package wttechnologies.com.pokedextest;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by William on 7/17/2016.
 */
public class PokemonInfoActivity extends AppCompatActivity {

    // Declarations
    List<String> attacks = new ArrayList<>();
    String[] movelist = new String[6];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokemon_info_layout);

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            Pokemon poke = (Pokemon)getIntent().getSerializableExtra("pokemon");

            if(poke.getNormals().size() > 0)
                for(NormalAttack atk : poke.getNormals())
                    attacks.add(atk.toString());
            if(poke.getNormals().size() > 0)
                for(SpecialAttack atk : poke.getSpecials())
                    attacks.add(atk.toString());


            ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview, attacks);
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageResource(poke.getImageId());

            ListView listView = (ListView) findViewById(R.id.moveList);
            listView.setAdapter(adapter);
        }

    }
}
