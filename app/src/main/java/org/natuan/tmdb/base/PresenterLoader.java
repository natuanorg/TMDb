package org.natuan.tmdb.base;

import android.content.Context;
import android.support.v4.content.Loader;

import org.natuan.tmdb.util.Logger;

/**
 * Created by Nguyen Anh Tuan on 29/10/2016.
 * natuan.org@gmail.com
 */

public class PresenterLoader<T extends Presenter>
        extends Loader<T> {

    private final PresenterFactory<T> mFactory;
    private T mPresenter;
    private final String mTag;

    public PresenterLoader(Context context, PresenterFactory<T> factory, String tag) {
        super(context);
        this.mFactory = factory;
        this.mTag = tag;
    }
    @Override
    protected void onStartLoading() {
        Logger.enter();
        if (mPresenter != null) {
            Logger.d("Presenter is not null");
            deliverResult(mPresenter);
            return;
        }
        forceLoad();
        Logger.exit();
    }
    @Override
    protected void onForceLoad() {
        Logger.enter();
        //Create the Presenter using the Factory
        mPresenter = mFactory.create();
        // Deliver the result
        deliverResult(mPresenter);
        Logger.exit();
    }

    @Override
    public void deliverResult(T data) {
        Logger.enter();
        Logger.exit();
        super.deliverResult(data);
    }

    @Override
    protected void onStopLoading() {
        Logger.enter();
        Logger.exit();
    }

    @Override
    protected void onReset() {
        Logger.enter();
        if (mPresenter != null) {
            mPresenter.onDestroyed();
            mPresenter = null;
        }
        Logger.exit();
    }
}
