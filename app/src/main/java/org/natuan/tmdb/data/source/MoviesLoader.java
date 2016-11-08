package org.natuan.tmdb.data.source;

import android.content.Context;

import org.natuan.tmdb.data.model.Movie;
import org.natuan.tmdb.data.model.MoviesResponse;
import org.natuan.tmdb.util.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nguyen Anh Tuan on 26/10/2016.
 * natuan.org@gmail.com
 */
public class MoviesLoader extends DataLoader<MoviesResponse> {

    private boolean isLoadMore = false;
    private int mCurrentPage = 1;

    public MoviesLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        Logger.d("isLoadMore" + isLoadMore);
        Logger.d("mCurrentPage" + mCurrentPage);
        if (mData != null) {
            deliverResult(mData);
            return;
        }
        super.onStartLoading();
    }

    @Override
    public void deliverResult(MoviesResponse data) {
        Logger.enter();
        displayMoviesResponse(data);
        if (isLoadMore && mData != null) {
            mData = update(data);
        } else {
            mData = data;
        }
        super.deliverResult(mData);
        Logger.exit();
    }

    private MoviesResponse update(MoviesResponse newData) {
        Logger.enter();

        if (newData == null) return mData;

        MoviesResponse oldData = mData;

        Logger.d("Old Data: " + mData.getMovies().size());
        Logger.d("New Data: " + newData.getMovies().size());

        List<Movie> movies = new ArrayList<>(oldData.getMovies());
        movies.addAll(newData.getMovies());

        Logger.d("Updated Data: " + movies.size());

        MoviesResponse moviesResponse = new MoviesResponse();
        moviesResponse.setMovies(movies);
        mCurrentPage = newData.getPage();
        moviesResponse.setPage(newData.getPage());
        moviesResponse.setTotalPages(newData.getTotalPages());
        moviesResponse.setTotalResults(newData.getTotalResults());
        Logger.exit();
        return moviesResponse;
    }

    @Override
    protected String getUrl() {
        Logger.enter();
        EndPointRequest.Builder endPointBuilder = new EndPointRequest.Builder();
        endPointBuilder.setSourceType(EndPointRequest.MOVIE_POPULAR);
        if (isLoadMore) {
            endPointBuilder.setParameter(EndPointRequest.PAGE, String.valueOf(++mCurrentPage));
        }
        EndPointRequest endPointRequest = endPointBuilder.build();
        Logger.d(endPointRequest.getUrl());
        Logger.exit();
        return endPointRequest.getUrl();
    }

    @Override
    protected void onReset() {
        super.onReset();
        isLoadMore = false;
        mCurrentPage = 1;
    }

    public boolean isLoadMore() {
        return isLoadMore;
    }

    public void setLoadMore(boolean loadMore) {
        isLoadMore = loadMore;
    }

    private void displayMoviesResponse(MoviesResponse response) {
        if (response != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(response.getPage()+"\n");
            stringBuilder.append(response.getTotalPages()+"\n");
            stringBuilder.append(response.getTotalResults()+"\n");
            for (Movie movie : response.getMovies()) {
                stringBuilder.append(movie.getTitle()+"\n");
            }
            Logger.d("Data: " + stringBuilder.toString());
        }
    }
}
