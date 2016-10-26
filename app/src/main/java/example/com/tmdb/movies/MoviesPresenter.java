package example.com.tmdb.movies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.util.List;

import example.com.tmdb.data.model.Movie;
import example.com.tmdb.data.model.MoviesResponse;
import example.com.tmdb.data.source.DataHolder;
import example.com.tmdb.data.source.MoviesLoader;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Nguyen Anh Tuan on 26/10/2016.
 * natuan.org@gmail.com
 */
public class MoviesPresenter implements MoviesContract.Presenter,
        LoaderManager.LoaderCallbacks<DataHolder>{

    private final static int MOVIES_QUERY = 1;
    private final MoviesContract.View mMoviesView;
    private final MoviesLoader mMoviesLoader;
    private final LoaderManager mLoaderManager;

    public MoviesPresenter(@NonNull MoviesLoader loader,
                           @NonNull LoaderManager loaderManager,
                           @NonNull MoviesContract.View moviesView) {
        this.mMoviesLoader = checkNotNull(loader);
        this.mMoviesView = checkNotNull(moviesView);
        this.mLoaderManager = checkNotNull(loaderManager);

        mMoviesView.setPresenter(this);
    }

    @Override
    public Loader<DataHolder> onCreateLoader(int id, Bundle args) {
        mMoviesView.setLoadingIndicator(true);
        return mMoviesLoader;
    }

    @Override
    public void start() {
        mLoaderManager.initLoader(MOVIES_QUERY, null, this);
    }

    @Override
    public void onLoadFinished(Loader<DataHolder> loader, DataHolder data) {
        mMoviesView.setLoadingIndicator(false);
        MoviesResponse response = (MoviesResponse) data.getData();
        if (response != null) {
            List<Movie> movies = response.getMovies();
            mMoviesView.showMovies(movies);
        } else {
            mMoviesView.showLoadingMoviesError();
        }
    }

    @Override
    public void onLoaderReset(Loader<DataHolder> loader) {

    }
}
