package org.natuan.asynchttpclient;

import com.google.gson.reflect.TypeToken;

/**
 * Created by Nguyen Anh Tuan on 15/10/2016.
 * natuan.org@gmail.com
 */
public abstract class JsonResponseHandler<ResponseType> extends ResponseHandler {

    abstract public void onSuccess(ResponseType response);

    @Override
    public void onSuccess(HTTPResponse response) {
        RawBody body = (RawBody) response.mBody;
        if (body != null) {
            ResponseType obj = null;
            try {
                obj = new JSONConverter<ResponseType>()
                        .decode(body, new TypeToken<ResponseType>(){}.getType());
                onSuccess(obj);
            } catch (Exception e) {
                onError(e);
            }
        } else {
            onSuccess((ResponseType) null);
        }
    }


}
