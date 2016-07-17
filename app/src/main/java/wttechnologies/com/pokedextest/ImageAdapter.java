package wttechnologies.com.pokedextest;

/**
 * Created by William on 7/16/2016.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<Pokemon> list = new ArrayList<>();

    // Keep all Images in array
    private Integer[] mThumbIds = new Integer[list.size()];

    // Constructor
    public ImageAdapter(Context c, List<Pokemon> list){
        this.list = list;
        mContext = c;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final PokemonButton imageView = new PokemonButton(mContext, list.get(position));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext.getApplicationContext(),imageView.getPokemon().getName() , Toast.LENGTH_SHORT).show();
            }
        });
        Log.i("Name:", imageView.getPokemon().getName());
        imageView.setImageResource(list.get(position).getImageId());
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
        return imageView;
    }

}