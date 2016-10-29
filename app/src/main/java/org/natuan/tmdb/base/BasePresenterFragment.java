package org.natuan.tmdb.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import org.natuan.tmdb.util.Logger;

/**
 * Created by Nguyen Anh Tuan on 29/10/2016.
 * natuan.org@gmail.com
 */

public abstract class BasePresenterFragment<P extends Presenter<V>, V> extends Fragment{

    private static final int LOADER_ID = 101;
    private Presenter<V> presenter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Logger.enter();
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(loaderId(), null, new LoaderManager.LoaderCallbacks<P>() {
            @Override
            public Loader<P> onCreateLoader(int id, Bundle args) {
                Logger.enter();
                Logger.exit();
                return new PresenterLoader<>(getContext(), getPresenterFactory(), tag());
            }

            @Override
            public void onLoadFinished(Loader<P> loader, P p) {
                Logger.enter();
                BasePresenterFragment.this.presenter = p;
                onPresenterPrepared(p);
                Logger.exit();
            }

            @Override
            public void onLoaderReset(Loader<P> loader) {
                Logger.enter();
                BasePresenterFragment.this.presenter = null;
                onPresenterDestroyed();
                Logger.exit();
            }
        });
        Logger.exit();
    }

    @Override
    public void onResume() {
        Logger.enter();
        super.onResume();
        presenter.onViewAttached(getPresenterView());
        Logger.exit();
    }

    @Override
    public void onPause() {
        Logger.enter();
        presenter.onViewDetached();
        super.onPause();
        Logger.exit();
    }

    protected void onPresenterDestroyed() {
    }

    protected abstract void onPresenterPrepared(@NonNull P presenter);

    @NonNull
    protected abstract String tag();

    private int loaderId() {
        return LOADER_ID;
    }

    protected abstract PresenterFactory<P> getPresenterFactory();

    public V getPresenterView() {
        return (V) this;
    }
}
