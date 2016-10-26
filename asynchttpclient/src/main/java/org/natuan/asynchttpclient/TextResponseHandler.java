package org.natuan.asynchttpclient;

/**
 * Created by Nguyen Anh Tuan on 15/10/2016.
 * natuan.org@gmail.com
 */
public abstract class TextResponseHandler extends ResponseHandler {

    abstract void onSuccess(String response);

    @Override
    public void onSuccess(HTTPResponse response) {
        TextPlainBody body = (TextPlainBody) response.mBody;
        onSuccess(body.getContent());
    }
}
