package example.com.tmdb.movies;

import java.util.List;

import example.com.tmdb.BasePresenter;
import example.com.tmdb.BaseView;
import example.com.tmdb.data.model.Movie;

/**
 * Created by Nguyen Anh Tuan on 26/10/2016.
 * natuan.org@gmail.com
 */
public class MoviesContract {

    interface View extends BaseView<Presenter> {

        void setLoadingIndicator(boolean active);

        void showMovies(List<Movie> moviesResponse);

        void showLoadingMoviesError();
    }

    interface Presenter extends BasePresenter {

    }

}
