package org.natuan.asynchttpclient;

import android.os.AsyncTask;

/**
 * Created by Nguyen Anh Tuan on 15/10/2016.
 * natuan.org@gmail.com
 */
public interface AsyncHttpClient<T> {
    AsyncTask excuteAsync(HTTPRequest request, ResponseHandler handler);

    T excuteSync(HTTPRequest request, Class clazz);
}
