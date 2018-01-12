package com.dicoding.academies.ayi.cataloguecontentprovider.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dicoding.academies.ayi.cataloguecontentprovider.MainActivity;
import com.dicoding.academies.ayi.cataloguecontentprovider.R;
import com.squareup.picasso.Picasso;

import static com.dicoding.academies.ayi.cataloguecontentprovider.db.DatabaseContract.FavoriteColumns.BANNER;
import static com.dicoding.academies.ayi.cataloguecontentprovider.db.DatabaseContract.FavoriteColumns.DESCRIPTION;
import static com.dicoding.academies.ayi.cataloguecontentprovider.db.DatabaseContract.FavoriteColumns.POSTER;
import static com.dicoding.academies.ayi.cataloguecontentprovider.db.DatabaseContract.FavoriteColumns.TITLE;
import static com.dicoding.academies.ayi.cataloguecontentprovider.db.DatabaseContract.getColumnString;

/**
 * Created by ayi on 04/01/18.
 */

public class FavoriteAdapter extends CursorAdapter {

    private Context mContex;

    public FavoriteAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        this.mContex = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_content_provider, parent, false);
        return view;
    }

    @Override
    public Cursor getCursor() {
        return super.getCursor();
    }

    @Override
    public void bindView(View itemView, Context context, Cursor cursor) {
        if (cursor != null){
            TextView tvJudul = (TextView)itemView.findViewById(R.id.np_text_judul);
            TextView tvDesc = (TextView)itemView.findViewById(R.id.np_text_desc);
            //tvTerbit = (TextView)itemView.findViewById(R.id.np_text_terbit);
            ImageView imgMovie = (ImageView)itemView.findViewById(R.id.np_img_movie);
            //Button btnDetail = (Button) itemView.findViewById(R.id.btn_detail);

            tvJudul.setText(getColumnString(cursor,TITLE));
            tvDesc.setText(getColumnString(cursor,DESCRIPTION));
           Picasso.with(context).load(getColumnString(cursor,POSTER)).into(imgMovie);
        }
    }
}
