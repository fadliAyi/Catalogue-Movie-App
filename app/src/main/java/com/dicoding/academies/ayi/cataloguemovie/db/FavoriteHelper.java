package com.dicoding.academies.ayi.cataloguemovie.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.dicoding.academies.ayi.cataloguemovie.entity.Favorite;

import java.util.ArrayList;

import static com.dicoding.academies.ayi.cataloguemovie.db.DatabaseContract.FavoriteColumns.BANNER;
import static com.dicoding.academies.ayi.cataloguemovie.db.DatabaseContract.FavoriteColumns.DESCRIPTION;
import static com.dicoding.academies.ayi.cataloguemovie.db.DatabaseContract.FavoriteColumns.POPULARITY;
import static com.dicoding.academies.ayi.cataloguemovie.db.DatabaseContract.FavoriteColumns.POSTER;
import static com.dicoding.academies.ayi.cataloguemovie.db.DatabaseContract.FavoriteColumns.RELEASE_DATE;
import static com.dicoding.academies.ayi.cataloguemovie.db.DatabaseContract.FavoriteColumns.TITLE;
import static com.dicoding.academies.ayi.cataloguemovie.db.DatabaseContract.TABLE_MOVIE;
import static com.dicoding.academies.ayi.cataloguemovie.db.DatabaseContract.FavoriteColumns._ID;

/**
 * Created by ayi on 03/01/18.
 */

public class FavoriteHelper {
    private static String DATABASE_TABLE = TABLE_MOVIE;
    private Context context;
    private DatabaseHelper dataBaseHelper;

    private SQLiteDatabase database;

    public FavoriteHelper(Context context){
        this.context = context;
    }

    public FavoriteHelper open() throws SQLException {
        dataBaseHelper = new DatabaseHelper(context);
        database = dataBaseHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dataBaseHelper.close();
    }

    public ArrayList<Favorite> query(){
        ArrayList<Favorite> arrayList = new ArrayList<Favorite>();
        Cursor cursor = database.query(DATABASE_TABLE,null,null,null,null,null,_ID +" DESC",null);
        cursor.moveToFirst();
        Favorite note;
        if (cursor.getCount()>0) {
            do {

                note = new Favorite();
                note.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                note.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                note.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION)));
                note.setRelease_date(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_DATE)));
                note.setPopulariry(cursor.getString(cursor.getColumnIndexOrThrow(POPULARITY)));
                note.setBanner(cursor.getString(cursor.getColumnIndexOrThrow(BANNER)));
                note.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(POSTER)));
                arrayList.add(note);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }


    public long insert(Favorite note){
        ContentValues initialValues =  new ContentValues();
        initialValues.put(TITLE, note.getTitle());
        initialValues.put(DESCRIPTION, note.getOverview());
        initialValues.put(RELEASE_DATE, note.getRelease_date());
        initialValues.put(POPULARITY, note.getPopulariry());
        initialValues.put(BANNER, note.getBanner());
        initialValues.put(POSTER, note.getPoster());
        return database.insert(DATABASE_TABLE, null, initialValues);
    }

    public int delete(int id){
        return database.delete(TABLE_MOVIE, _ID + " = '"+id+"'", null);
    }

}
