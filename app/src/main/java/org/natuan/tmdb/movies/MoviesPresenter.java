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
        LoaderManager.LoaderCallbacks<MoviesResponse> {

    private static final int MOVIES_REQUEST = 1992;
    private static final String KEY_LOAD_MORE = "is_load_more";
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
        loadMovies(false);
        Logger.exit();
    }

    public void loadMovies(boolean isLoadMore) {
        if (!isLoadMore) {
            mLoaderManager.initLoader(MOVIES_REQUEST, null, this);
        } else {
            mMoviesLoader.setLoadMore(true);
            mMoviesLoader.onContentChanged();
        }
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
        Logger.enter();
        if (args != null) {
            boolean isLoadMore = args.getBoolean(KEY_LOAD_MORE, false);
            mMoviesLoader.setLoadMore(isLoadMore);
        }
        Logger.exit();
        return mMoviesLoader;
    }

    @Override
    public void onLoadFinished(Loader<MoviesResponse> loader, MoviesResponse data) {
        Logger.enter();
        if (data != null && data.getMovies().size() > 0) {
            List<Movie> movieList = data.getMovies();
            if (mMoviesLoader != null && moviesView != null) {
                if (mMoviesLoader.isLoadMore()) {
                    this.moviesView.showMoreMovies(movieList);
                    mMoviesLoader.setLoadMore(false);
                } else {
                    this.moviesView.showMovies(movieList);
                }
            }
        }
        Logger.exit();
    }

    @Override
    public void onLoaderReset(Loader<MoviesResponse> loader) {
        Logger.enter();
        Logger.exit();
    }
}
