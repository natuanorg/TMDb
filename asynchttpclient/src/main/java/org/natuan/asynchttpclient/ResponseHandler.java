package org.natuan.asynchttpclient;

/**
 * Created by Nguyen Anh Tuan on 15/10/2016.
 * natuan.org@gmail.com
 */
public abstract class ResponseHandler {
    abstract public void onSuccess(HTTPResponse response);

    abstract public void onFailure(HTTPResponse response);

    abstract public void onError(Throwable error);
}
