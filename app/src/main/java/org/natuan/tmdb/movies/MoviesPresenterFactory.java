package org.natuan.tmdb.movies;

import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;

import org.natuan.tmdb.base.PresenterFactory;
import org.natuan.tmdb.data.source.MoviesLoader;

/**
 * Created by Nguyen Anh Tuan on 29/10/2016.
 * natuan.org@gmail.com
 */

public class MoviesPresenterFactory implements PresenterFactory<MoviesPresenter> {

    private MoviesLoader mMoviesLoader;
    private LoaderManager mLoaderManager;

    public MoviesPresenterFactory(
                                @NonNull MoviesLoader mMoviesLoader,
                                @NonNull LoaderManager mLoaderManager) {
        this.mMoviesLoader = mMoviesLoader;
        this.mLoaderManager = mLoaderManager;
    }

    @Override
    public MoviesPresenter create() {
        return new MoviesPresenter(mMoviesLoader, mLoaderManager);
    }
}
