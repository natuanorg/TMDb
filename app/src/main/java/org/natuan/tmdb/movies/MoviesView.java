package org.natuan.tmdb.movies;

import org.natuan.tmdb.data.model.Movie;

import java.util.List;

/**
 * Created by Nguyen Anh Tuan on 29/10/2016.
 * natuan.org@gmail.com
 */

public interface MoviesView {
    void showMovies(List<Movie> movies);

    void showMoreMovies(List<Movie> movies);
}
