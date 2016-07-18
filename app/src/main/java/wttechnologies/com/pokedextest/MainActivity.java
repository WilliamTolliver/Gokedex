package wttechnologies.com.pokedextest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.GridView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);
        SQLiteDatabase mydatabase = openOrCreateDatabase("gokedex",MODE_PRIVATE,null);
        makePokemonTable(mydatabase);
        makeNormalAttackTable(mydatabase);
        makeSpecialAttackTable(mydatabase);

        List<String> someNames = convertToCommaDelimited(this, R.raw.normalmovespokemon);

        // Create GridView and get list of Pokemon
        GridView gridView = (GridView) findViewById(R.id.grid_view);

        Bitmap bmp = BitmapFactory.decodeResource(getResources(),
                R.drawable.teams2);
        BitmapDrawable bitmapDrawable = new BitmapDrawable(bmp);
        bitmapDrawable.setGravity(Gravity.CENTER);
        gridView.setBackgroundDrawable(bitmapDrawable);

        //gridView.setBackground(getResources().getDrawable(R.drawable.teams2));
        List<Pokemon> pokemon = getPokemon(this, R.raw.list);

        // Instance of ImageAdapter Class
        ImageAdapter adapter = new ImageAdapter(this, pokemon);
        gridView.setAdapter(adapter);

    }


    public static List<Pokemon> getPokemon(Context ctx, int resId)
    {
        InputStream inputStream = ctx.getResources().openRawResource(resId);
        List<String> pokemonValues;
        List<Pokemon> pokemonList = new ArrayList<>();
        Pokemon poke = null;
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;

        try {
            while (( line = buffreader.readLine()) != null) {
                line = line.replaceAll("\\s+", ",");
                pokemonValues = Arrays.asList(line.split(","));
                if(pokemonValues != null) {
                    if (pokemonValues.size() < 4) {
                        poke = new Pokemon(Integer.parseInt(pokemonValues.get(0)), pokemonValues.get(1), pokemonValues.get(2), "", 0);
                        poke.setImageId( ctx.getResources().getIdentifier(poke.getName().toString(), "drawable", ctx.getPackageName()));
                    }else if (pokemonValues.size() == 4) {
                        poke = new Pokemon(Integer.parseInt(pokemonValues.get(0)), pokemonValues.get(1), pokemonValues.get(2), pokemonValues.get(3), 0);
                        poke.setImageId( ctx.getResources().getIdentifier(poke.getName().toString(), "drawable", ctx.getPackageName()));
                    } else if (pokemonValues.size() == 5) {
                        poke = new Pokemon(Integer.parseInt(pokemonValues.get(0)), pokemonValues.get(1), pokemonValues.get(2), pokemonValues.get(3), 0);
                        poke.setImageId( ctx.getResources().getIdentifier(poke.getName().toString(), "drawable", ctx.getPackageName()));
                    }
                }
                pokemonList.add(poke);
            }
        } catch (IOException e) {
            return null;
        }
        return pokemonList;
    }

    public List<NormalAttack> getNormalAttacks(Context ctx, int resId){
        InputStream inputStream = ctx.getResources().openRawResource(resId);
        List<String> normalAttackValues = new ArrayList<>();
        List<NormalAttack> normalAttackList = new ArrayList<>();
        NormalAttack attack = null;
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;

        try {
            while (( line = buffreader.readLine()) != null) {
                line = line.replaceAll("\\s+", ",");
                normalAttackValues = Arrays.asList(line.split(","));
                String known = "";
                if(normalAttackValues != null) {
                    for(int i = 2; i < normalAttackValues.size(); i++){
                        known += normalAttackValues.get(i);
                    }
                    attack = new NormalAttack(normalAttackValues.get(0), normalAttackValues.get(1), Integer.parseInt(normalAttackValues.get(2)), known);
                }
                normalAttackList.add(attack);
            }
        } catch (IOException e) {
            return null;
        }
        return normalAttackList;
    }

    public List<SpecialAttack> getSpecialAttacks(Context ctx, int resId){
        List<String> names = new ArrayList<>();
        InputStream inputStream = ctx.getResources().openRawResource(resId);
        List<String> specialAttackValues = new ArrayList<>();
        List<SpecialAttack> specialAttackList = new ArrayList<>();
        SpecialAttack attack = null;
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;

        try {
            while (( line = buffreader.readLine()) != null) {
                line = line.replaceAll("\\s+", ",");
                specialAttackValues = Arrays.asList(line.split(","));
                String known = "";
                if(specialAttackValues != null) {

                    for(int i = 3; i < specialAttackValues.size(); i++){
                        known += specialAttackValues.get(i);
                    }
                    attack = new SpecialAttack(specialAttackValues.get(0), specialAttackValues.get(1), Integer.parseInt(specialAttackValues.get(2)),Integer.parseInt(specialAttackValues.get(3)), known);
                }
                specialAttackList.add(attack);
            }
        } catch (IOException e) {
            return null;
        }
        return specialAttackList;
    }

    public void makePokemonTable(SQLiteDatabase mydatabase){
        mydatabase.execSQL("DROP TABLE IF EXISTS pokemon;");
        mydatabase.execSQL("CREATE TABLE pokemon(id int,name varchar(255),type1 varchar(255),type2 varchar(255),imageId int);");

        List<Pokemon> result = getPokemon(this, R.raw.list);
        for(Pokemon pokemon : result) {
            mydatabase.execSQL("INSERT INTO pokemon (id, name, type1, type2, imageId)  VALUES(" + pokemon.getId() + ", '" + pokemon.getName().toString() + "', '" + pokemon.getType1().toString() + "', '" + pokemon.getType2().toString() + "', " + pokemon.getImageId() + ");");
        }
    }

    public void makeNormalAttackTable(SQLiteDatabase mydatabase) {
        mydatabase.execSQL("DROP TABLE IF EXISTS normal_attack;");
        mydatabase.execSQL("CREATE TABLE normal_attack(name varchar(255), type varchar(255), damage int, known_by VARCHAR);");

        List<NormalAttack> normalAttacks = getNormalAttacks(this, R.raw.normal);
        for (NormalAttack attack : normalAttacks) {
            mydatabase.execSQL("INSERT INTO normal_attack (name, type, damage, known_by)  VALUES('" + attack.getName().toString() + "', '" + attack.getType().toString() + "', " + attack.getDamage() + ", '" + attack.getKnown_by() +  "');");

        }
    }

    public void makeSpecialAttackTable(SQLiteDatabase mydatabase){
        mydatabase.execSQL("DROP TABLE IF EXISTS special_attack;");
        mydatabase.execSQL("CREATE TABLE special_attack(name varchar(255), type varchar(255), damage int, energy int, known_by VARCHAR);");

        List<SpecialAttack> specialAttacks = getSpecialAttacks(this,R.raw.special);
        for(SpecialAttack attack : specialAttacks) {
            mydatabase.execSQL("INSERT INTO normal_attack (name, type, damage, known_by)  VALUES('" +  attack.getName().toString() + "', '" + attack.getType().toString() + "', "  + attack.getDamage() + ", '" + attack.getKnown_by() + "');");        }
    }

    public List<String> convertToCommaDelimited(Context ctx, int resId){
        List<String> names = new ArrayList<>();
        InputStream inputStream = ctx.getResources().openRawResource(resId);
        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;

        try {
            while (( line = buffreader.readLine()) != null) {
                line = line.replaceAll("\\s+", ",");
                names.add(line.toString() + "\n");
            }
        } catch (IOException e) {
            return null;
        }
        return names;
    }


}
