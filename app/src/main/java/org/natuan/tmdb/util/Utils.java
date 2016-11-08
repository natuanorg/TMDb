package org.natuan.tmdb.util;

/**
 * Created by Nguyen Anh Tuan on 26/10/2016.
 * natuan.org@gmail.com
 */
public class Utils {
    public static String getPosterPath(String poster_path) {
        return "http://image.tmdb.org/t/p/w500" + poster_path;
    }
}
