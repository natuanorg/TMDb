package org.natuan.tmdb.movies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.natuan.tmdb.R;
import org.natuan.tmdb.base.BasePresenterFragment;
import org.natuan.tmdb.base.PresenterFactory;
import org.natuan.tmdb.data.model.Movie;
import org.natuan.tmdb.data.source.MoviesLoader;
import org.natuan.tmdb.util.Logger;

import java.util.List;

/**
 * Created by Nguyen Anh Tuan on 30/10/2016.
 * natuan.org@gmail.com
 */

public class MoviesFragment extends BasePresenterFragment<MoviesPresenter, MoviesView> implements MoviesView {

    private MoviesPresenter mMoviesPresenter;
    private RecyclerView mRv;
    private MoviesAdapter mAdapter;
    private ProgressBar mProgressbar;

    public static MoviesFragment newInstance() {
        return new MoviesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.d("onCreate");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Logger.d("onCreateView");
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        return view;
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
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Logger.d("onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Logger.d("onStart");
    }

    @Override
    public void onPause() {
        super.onPause();
        Logger.d("onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void onPresenterPrepared(
            @NonNull MoviesPresenter presenter) {
        mMoviesPresenter = presenter;
        Logger.d("onPresenterPrepared");
    }

    @NonNull
    @Override
    protected String tag() {
        return "movies-fragment";
    }

    @Override
    protected PresenterFactory<MoviesPresenter> getPresenterFactory() {
        MoviesLoader moviesLoader = new MoviesLoader(getActivity());
        return new MoviesPresenterFactory(moviesLoader, getLoaderManager());
    }

    @Override
    public void showMovies(List<Movie> movies) {
        Logger.enter();
        mAdapter.initData(movies);
        Logger.exit();
    }
}
