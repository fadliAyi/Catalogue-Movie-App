package com.dicoding.academies.ayi.cataloguemovie.db;

import android.provider.BaseColumns;

/**
 * Created by ayi on 03/01/18.
 */

public class DatabaseContract {
    public static String TABLE_MOVIE = "favorite";
    public static final class FavoriteColumns implements BaseColumns {
        //Note title
      public static String TITLE = "title";
        //Note description
      public static String DESCRIPTION = "description";
        //Note date
      public static String RELEASE_DATE = "date";
      public static String POPULARITY = "popularity";
      public static String BANNER = "banner";
      public static String POSTER = "poster";
    }
}
