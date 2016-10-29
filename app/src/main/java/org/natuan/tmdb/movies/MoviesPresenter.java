package org.natuan.tmdb.movies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import org.natuan.tmdb.base.Presenter;
import org.natuan.tmdb.data.model.Movie;
import org.natuan.tmdb.data.model.MoviesResponse;
import org.natuan.tmdb.data.source.MoviesLoader;
import org.natuan.tmdb.util.Logger;

import java.util.List;

/**
 * Created by Nguyen Anh Tuan on 29/10/2016.
 * natuan.org@gmail.com
 */

class MoviesPresenter implements Presenter<MoviesView>,
        LoaderManager.LoaderCallbacks<MoviesResponse>{

    private final int DATA_LOADER = 102;
    private MoviesView moviesView;
    private MoviesLoader mMoviesLoader;
    private LoaderManager mLoaderManager;

    public MoviesPresenter(@NonNull MoviesLoader moviesLoader,
                           @NonNull LoaderManager loaderManager) {
        this.mMoviesLoader = moviesLoader;
        this.mLoaderManager = loaderManager;
    }

    @Override
    public void onViewAttached(MoviesView view) {
        Logger.enter();
        this.moviesView = view;
        mLoaderManager.initLoader(DATA_LOADER, null, this);
        Logger.exit();
    }

    @Override
    public void onViewDetached() {
        Logger.enter();
        this.moviesView = null;
        Logger.exit();
    }

    @Override
    public void onDestroyed() {
        Logger.enter();
        Logger.exit();
        //Nothing to clean up.
    }

    @Override
    public Loader<MoviesResponse> onCreateLoader(int id, Bundle args) {
        return mMoviesLoader;
    }

    @Override
    public void onLoadFinished(Loader<MoviesResponse> loader, MoviesResponse data) {
        if (data != null) {
            List<Movie> movieList = data.getMovies();
            this.moviesView.showMovies(movieList);
        }
    }

    @Override
    public void onLoaderReset(Loader<MoviesResponse> loader) {

    }
}
