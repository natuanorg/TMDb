package example.com.tmdb.data.source;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.content.Loader;

import org.natuan.asynchttpclient.AsyncHttpClient;
import org.natuan.asynchttpclient.AsyncHttpClientImpl;
import org.natuan.asynchttpclient.HTTPRequest;
import org.natuan.asynchttpclient.JsonResponseHandler;

import example.com.tmdb.util.Logger;

/**
 * Created by Nguyen Anh Tuan on 26/10/2016.
 * natuan.org@gmail.com
 */
abstract class BaseLoader<T> extends Loader<DataHolder> {

    protected AsyncTask mAsyncTask;
    protected DataHolder<T> mDataHolder;

    public BaseLoader(Context context) {
        super(context);
        onContentChanged();
    }

    @Override
    protected void onStartLoading() {
        Logger.enter();
        super.onStartLoading();
        if (takeContentChanged()) {
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
        super.onStopLoading();
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
        super.onForceLoad();
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
