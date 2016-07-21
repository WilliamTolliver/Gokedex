package wttechnologies.com.pokedextest;

/**
 * Created by William on 7/16/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import utilities.DatabaseUtil;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private List<Pokemon> list = new ArrayList<>();

    // Keep all Images in array
    private Integer[] mThumbIds = new Integer[list.size()];

    // Constructor
    public ImageAdapter(Context c){
        this.list = DatabaseUtil.setAttacks(c, DatabaseUtil.getPokemonFromFile(c, R.raw.list));;
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

        View row = convertView;
        ViewHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            row = inflater.inflate(R.layout.grid_item_layout, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.text);
            holder.image = (ImageView) row.findViewById(R.id.image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        final PokemonButton imageView = new PokemonButton(mContext, list.get(position));
        holder.imageTitle.setText(imageView.getPokemon().getName());
        holder.image.setImageResource(imageView.getPokemon().getImageId());
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, PokemonInfoActivity.class);
                myIntent.putExtra("pokemon", imageView.getPokemon()); //Optional parameters
                mContext.startActivity(myIntent);
            }
        });
        return row;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }

}