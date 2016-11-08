package org.natuan.tmdb.data.source;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import org.natuan.asynchttpclient.AsyncHttpClient;
import org.natuan.asynchttpclient.AsyncHttpClientImpl;
import org.natuan.asynchttpclient.HTTPRequest;
import org.natuan.tmdb.util.Logger;

import java.lang.reflect.ParameterizedType;

/**
 * Created by Nguyen Anh Tuan on 26/10/2016.
 * natuan.org@gmail.com
 */
abstract class DataLoader<T> extends AsyncTaskLoader<T> {

    protected Class<T> typeOfT;
    protected T mData;

    public DataLoader(Context context) {
        super(context);
        this.typeOfT = (Class<T>)
                ((ParameterizedType) getClass()
                        .getGenericSuperclass())
                        .getActualTypeArguments()[0];
    }

    protected void onStartLoading() {
        Logger.enter();
        if (takeContentChanged() || mData == null) {
            forceLoad();
        }
        Logger.exit();
    }

    @Override
    public void deliverResult(T data) {
        super.deliverResult(data);
    }

    @Override
    public T loadInBackground() {
        return doRequest(getUrl());
    }

    @Override
    protected void onStopLoading() {
        cancelLoad();
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        mData = null;
    }

    private T doRequest(String url) {
        //Make HTTP Request
        HTTPRequest.Builder builder = new HTTPRequest.Builder();
        builder.setVerb(HTTPRequest.Verb.GET);
        builder.setUrl(url);
        HTTPRequest request = builder.build();
        //Run HTTP Request and Convert to Object
        AsyncHttpClient client = new AsyncHttpClientImpl();
        return (T) client
                .excuteSync(request, typeOfT);
    }

    protected abstract String getUrl();
}
