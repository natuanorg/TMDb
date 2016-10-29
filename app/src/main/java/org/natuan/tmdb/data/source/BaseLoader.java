package org.natuan.tmdb.data.source;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.content.Loader;

import org.natuan.asynchttpclient.AsyncHttpClient;
import org.natuan.asynchttpclient.AsyncHttpClientImpl;
import org.natuan.asynchttpclient.HTTPRequest;
import org.natuan.asynchttpclient.JsonResponseHandler;
import org.natuan.tmdb.util.Logger;

/**
 * Created by Nguyen Anh Tuan on 26/10/2016.
 * natuan.org@gmail.com
 */
abstract class BaseLoader<T> extends Loader<T> {

    protected AsyncTask mAsyncTask;
    protected T mDataHolder;

    public BaseLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        Logger.enter();
        if (mDataHolder != null) {
            deliverResult(mDataHolder);
        }
        if (takeContentChanged() || mDataHolder == null) {
            forceLoad();
        }
        Logger.exit();
    }

    @Override
    protected void onStopLoading() {
        Logger.enter();
        if (mAsyncTask != null
                && !mAsyncTask.isCancelled()) {
            mAsyncTask.cancel(true);
        }
        Logger.exit();
    }

    @Override
    public void deliverResult(T data) {
        Logger.enter();
        if (isReset()) {
            if (mDataHolder != null) {
                mDataHolder = null;
                return;
            }
        }
        mDataHolder = data;
        if (isStarted()) {
            super.deliverResult(data);
        }
        Logger.exit();
    }

    @Override
    protected void onReset() {
        Logger.enter();
        if (mAsyncTask != null
                && !mAsyncTask.isCancelled()) {
            mAsyncTask.cancel(true);
        }
        super.onReset();
        Logger.exit();
    }

    @Override
    protected void onForceLoad() {
        Logger.enter();
        doRequest();
        Logger.exit();
    }

    private void doRequest() {
        Logger.enter();
        HTTPRequest.Builder builder = new HTTPRequest.Builder();
        builder.setVerb(HTTPRequest.Verb.GET);
        builder.setUrl(getUrl());
        HTTPRequest request = builder.build();
        AsyncHttpClient client = new AsyncHttpClientImpl();
        mAsyncTask = client.excuteAsync(request, getResponseHandler());
        Logger.exit();
    }

    public abstract JsonResponseHandler getResponseHandler();

    public abstract String getUrl();
}
