package utilities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.widget.GridView;

import wttechnologies.com.pokedextest.R;

/**
 * Created by William on 7/18/2016.
 */
public class ConfigureViews {

    public static boolean setGridView(Context ctx, GridView gridView) {
        // Create GridView and get list of Pokemon
        try {
            Bitmap bmp = BitmapFactory.decodeResource(ctx.getResources(),
                    R.drawable.teams2);
            BitmapDrawable bitmapDrawable = new BitmapDrawable(bmp);
            bitmapDrawable.setGravity(Gravity.CENTER);
            gridView.setBackgroundDrawable(bitmapDrawable);
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
