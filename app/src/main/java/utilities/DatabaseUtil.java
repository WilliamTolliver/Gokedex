package utilities;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import wttechnologies.com.pokedextest.NormalAttack;
import wttechnologies.com.pokedextest.Pokemon;
import wttechnologies.com.pokedextest.R;
import wttechnologies.com.pokedextest.SpecialAttack;

/**
 * Created by William on 7/16/2016.
 */
public class DatabaseUtil {

    private static SQLiteDatabase mydatabase;

    public static boolean createPokemonDB(Context ctx){
        try {
            mydatabase = ctx.openOrCreateDatabase("gokedex", ctx.MODE_PRIVATE, null);
            makePokemonTable(ctx, mydatabase);
            makeNormalAttackTable(ctx, mydatabase);
            makeSpecialAttackTable(ctx, mydatabase);
            makeEffectivenessTable(ctx, mydatabase);
            mydatabase.close();
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public static List<Pokemon> queryAllPokemon(Context ctx){
        List<Pokemon> pokemonList = new ArrayList<>();
        mydatabase = ctx.openOrCreateDatabase("gokedex", ctx.MODE_PRIVATE, null);
        Cursor resultSet = mydatabase.rawQuery("Select * from pokemon",null);
        while(resultSet.moveToNext()){
            Pokemon pokemon = new Pokemon();
            pokemon.setId(resultSet.getInt(0));
            pokemon.setName(resultSet.getString(1).toString());
            pokemon.setType1(resultSet.getString(2));
            pokemon.setType2(resultSet.getString(3));
            pokemon.setImageId(resultSet.getInt(4));
        }

        return pokemonList;
    }

    public static List<Pokemon> getPokemonFromFile(Context ctx, int resId)
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
                        poke = new Pokemon(Integer.parseInt(pokemonValues.get(0)), pokemonValues.get(1), pokemonValues.get(2), "", 0, null, null);
                        poke.setImageId( ctx.getResources().getIdentifier(poke.getName().toString(), "drawable", ctx.getPackageName()));
                    }else if (pokemonValues.size() == 4) {
                        poke = new Pokemon(Integer.parseInt(pokemonValues.get(0)), pokemonValues.get(1), pokemonValues.get(2), pokemonValues.get(3), 0, null, null);
                        poke.setImageId( ctx.getResources().getIdentifier(poke.getName().toString(), "drawable", ctx.getPackageName()));
                    } else if (pokemonValues.size() == 5) {
                        poke = new Pokemon(Integer.parseInt(pokemonValues.get(0)), pokemonValues.get(1), pokemonValues.get(2), pokemonValues.get(3), 0, null, null);
                        poke.setImageId( ctx.getResources().getIdentifier(poke.getName().toString(), "drawable", ctx.getPackageName()));
                    }
                }
                pokemonList.add(poke);
            }
            for (Pokemon pokemon : pokemonList){
            }
        } catch (IOException e) {
            return null;
        }
        return pokemonList;
    }

    public static List<NormalAttack> getNormalAttacksFromFile(Context ctx, int resId){
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

    public static List<SpecialAttack> getSpecialAttacksFromFile(Context ctx, int resId){
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

    public static void makePokemonTable(Context ctx,SQLiteDatabase mydatabase){
        mydatabase.execSQL("DROP TABLE IF EXISTS pokemon;");
        mydatabase.execSQL("CREATE TABLE pokemon(id int,name varchar(255),type1 varchar(255),type2 varchar(255),imageId int);");

        List<Pokemon> result = getPokemonFromFile(ctx, R.raw.list);
        for(Pokemon pokemon : result) {
            mydatabase.execSQL("INSERT INTO pokemon (id, name, type1, type2, imageId)  VALUES(" + pokemon.getId() + ", '" + pokemon.getName().toString() + "', '" + pokemon.getType1().toString() + "', '" + pokemon.getType2().toString() + "', " + pokemon.getImageId() + ");");
        }
    }

    public static void makeEffectivenessTable(Context ctx,SQLiteDatabase mydatabase){
        mydatabase.execSQL("DROP TABLE IF EXISTS pokemon;");
        mydatabase.execSQL("CREATE TABLE effectiveness(id int AUTO_INCREMENT, type varchar(255), no_effective VARCHAR(255), not_effective VARCHARR(255), normal_effective VARCHAR(255), super_effective VARCHAR(255));");

        mydatabase.execSQL("INSERT INTO effectiveness(type) VALUES('normal')");
        mydatabase.execSQL("INSERT INTO effectiveness(type) VALUES('fight')");
        mydatabase.execSQL("INSERT INTO effectiveness(type) VALUES('flying')");
        mydatabase.execSQL("INSERT INTO effectiveness(type) VALUES('poison')");
        mydatabase.execSQL("INSERT INTO effectiveness(type) VALUES('ground')");
        mydatabase.execSQL("INSERT INTO effectiveness(type) VALUES('rock')");
        mydatabase.execSQL("INSERT INTO effectiveness(type) VALUES('bug')");
        mydatabase.execSQL("INSERT INTO effectiveness(type) VALUES('ghost')");
        mydatabase.execSQL("INSERT INTO effectiveness(type) VALUES('steel')");
        mydatabase.execSQL("INSERT INTO effectiveness(type) VALUES('fire')");
        mydatabase.execSQL("INSERT INTO effectiveness(type) VALUES('water')");
        mydatabase.execSQL("INSERT INTO effectiveness(type) VALUES('grass')");
        mydatabase.execSQL("INSERT INTO effectiveness(type) VALUES('electric')");
        mydatabase.execSQL("INSERT INTO effectiveness(type) VALUES('psychic')");
        mydatabase.execSQL("INSERT INTO effectiveness(type) VALUES('ice')");
        mydatabase.execSQL("INSERT INTO effectiveness(type) VALUES('dragon')");
        mydatabase.execSQL("INSERT INTO effectiveness(type) VALUES('dark')");
        mydatabase.execSQL("INSERT INTO effectiveness(type) VALUES('fairy')");
    }

    public static void makeNormalAttackTable(Context ctx,SQLiteDatabase mydatabase) {
        mydatabase.execSQL("DROP TABLE IF EXISTS normal_attack;");
        mydatabase.execSQL("CREATE TABLE normal_attack(name varchar(255), type varchar(255), damage int, known_by VARCHAR);");

        List<NormalAttack> normalAttacks = getNormalAttacksFromFile(ctx, R.raw.normal);
        for (NormalAttack attack : normalAttacks) {
            mydatabase.execSQL("INSERT INTO normal_attack (name, type, damage, known_by)  VALUES('" + attack.getName().toString() + "', '" + attack.getType().toString() + "', " + attack.getDamage() + ", '" + attack.getKnown_by() +  "');");

        }
    }

    public static void makeSpecialAttackTable(Context ctx,SQLiteDatabase mydatabase){
        mydatabase.execSQL("DROP TABLE IF EXISTS special_attack;");
        mydatabase.execSQL("CREATE TABLE special_attack(name varchar(255), type varchar(255), damage int, energy int, known_by VARCHAR);");

        List<SpecialAttack> specialAttacks = getSpecialAttacksFromFile(ctx,R.raw.special);
        for(SpecialAttack attack : specialAttacks) {
            mydatabase.execSQL("INSERT INTO special_attack (name, type, damage, energy, known_by)  VALUES('" +  attack.getName().toString() + "', '" + attack.getType().toString() + "', "  + attack.getDamage() + ", " +  attack.getEnergy() + ", '" + attack.getKnown_by() + "');");        }
    }

    public static List<String> convertToCommaDelimited(Context ctx, int resId){
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

    public static List<NormalAttack> getNormalsPer(Context ctx,Pokemon pokemon){
        SQLiteDatabase mydatabase = ctx.openOrCreateDatabase("gokedex", ctx.MODE_PRIVATE, null);
        List<NormalAttack> attacks = new ArrayList<>();
        Cursor resultSet = mydatabase.rawQuery("Select * from normal_attack WHERE known_by LIKE '%" + pokemon.getName() + "%'" ,null);
        NormalAttack norm;
        while(resultSet.moveToNext()){
            norm = new NormalAttack(resultSet.getString(0).toString(), resultSet.getString(1).toString(), Integer.parseInt(resultSet.getString(2)), pokemon.getName());
            attacks.add(norm);
        }

        mydatabase.close();

        return attacks;
    }

    public static List<SpecialAttack> getSpecialsPer(Context ctx, Pokemon pokemon){
        SQLiteDatabase mydatabase = ctx.openOrCreateDatabase("gokedex", ctx.MODE_PRIVATE, null);
        List<SpecialAttack> attacks = new ArrayList<>();
        Cursor resultSet = mydatabase.rawQuery("Select * from special_attack WHERE known_by LIKE '%" + pokemon.getName() + "%'" ,null);
        SpecialAttack norm;
        while(resultSet.moveToNext()){
            norm = new SpecialAttack(resultSet.getString(0).toString(), resultSet.getString(1).toString(), Integer.parseInt(resultSet.getString(2)), Integer.parseInt(resultSet.getString(3)), pokemon.getName());
            attacks.add(norm);
        }

        mydatabase.close();

        return attacks;
    }

    public static List<Pokemon> setAttacks(Context ctx, List<Pokemon> pokemons){

            pokemons = getPokemonFromFile(ctx, R.raw.list);

        for (Pokemon poke : pokemons) {
            if(getNormalsPer(ctx, poke).size() > 0) {
                poke.setNormals(getNormalsPer(ctx, poke));
            }
            if(getSpecialsPer(ctx, poke).size() > 0) {
                poke.setSpecials(getSpecialsPer(ctx, poke));
            }
        }
        return pokemons;
    }


}
