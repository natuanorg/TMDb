package example.com.tmdb.movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import example.com.tmdb.R;
import example.com.tmdb.data.model.Movie;
import example.com.tmdb.data.source.MoviesLoader;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Nguyen Anh Tuan on 26/10/2016.
 * natuan.org@gmail.com
 */
public class MoviesFragment extends Fragment implements MoviesContract.View{
    private MoviesContract.Presenter mPresenter;
    private RecyclerView mRv;
    private MoviesAdapter mAdapter;
    private ProgressBar mProgressbar;

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MoviesLoader moviesLoader = new MoviesLoader(getActivity());
        new MoviesPresenter(moviesLoader, getActivity().getSupportLoaderManager(), this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mProgressbar = (ProgressBar) view.findViewById(R.id.prb);
        mRv = (RecyclerView) view.findViewById(R.id.rv);
        setupRececlerView();
    }

    private void setupRececlerView() {
        mRv.setHasFixedSize(true);
        mRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new MoviesAdapter();
        mRv.setAdapter(mAdapter);
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        if (active) {
            mProgressbar.setVisibility(View.VISIBLE);
        } else {
            mProgressbar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMovies(List<Movie> moviesResponse) {
        checkNotNull(moviesResponse);
        mAdapter.initData(moviesResponse);
    }

    @Override
    public void showLoadingMoviesError() {
        mProgressbar.setVisibility(View.GONE);
    }

    @Override
    public void setPresenter(MoviesContract.Presenter presenter) {
        checkNotNull(presenter);
        mPresenter = presenter;
    }
}
