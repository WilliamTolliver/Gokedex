package wttechnologies.com.pokedextest;

import android.content.Context;
import android.content.res.Resources;
import android.test.ActivityTestCase;

import utilities.DatabaseUtil;

import org.junit.Test;

import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityTestCase
{

    public ApplicationTest(){}

    @Test
    public void testResources() {

        Context testContext = getInstrumentation().getContext();
        Resources testRes = testContext.getResources();
        int normalRes = R.raw.normal;
        int specialRes = R.raw.special;
        int pokemonRes = R.raw.list;

        assertNotNull(normalRes);
        assertNotNull(specialRes);
        assertNotNull(pokemonRes);

        assertFalse(DatabaseUtil.createPokemonDB(testContext));
    }

    @Test
    public void createDatabase(){

        Context testContext = getInstrumentation().getContext();
        Resources testRes = testContext.getResources();
        int normalRes = R.raw.normal;
        int specialRes = R.raw.special;
        int pokemonRes = R.raw.list;

        List<Pokemon> pokeBool = DatabaseUtil.getPokemon(testContext, pokemonRes);
        List<NormalAttack> normalBool = DatabaseUtil.getNormalAttacks(testContext, normalRes);
        List<SpecialAttack> specialBool = DatabaseUtil.getSpecialAttacks(testContext, specialRes);

        assertNotNull(pokeBool);
        assertNotNull(normalBool);
        assertNotNull(specialBool);
    }
}