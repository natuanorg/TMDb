package org.natuan.asynchttpclient;

import android.os.AsyncTask;

/**
 * Created by Nguyen Anh Tuan on 15/10/2016.
 * natuan.org@gmail.com
 */
public interface AsyncHttpClient {
    AsyncTask excuteAsync(HTTPRequest request, ResponseHandler handler);

    Result<HTTPResponse> excuteSync(HTTPRequest request);
}
