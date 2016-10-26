package org.natuan.asynchttpclient;

import java.lang.reflect.Type;

/**
 * Created by Nguyen Anh Tuan on 15/10/2016.
 * natuan.org@gmail.com
 */
public abstract class JsonResponseHandler<ResponseType, ErrorType> extends ResponseHandler {
    private final Type responseType;
    private final Type errorType;

    public JsonResponseHandler(Type responseType, Type errorType) {
        this.responseType = responseType;
        this.errorType = errorType;
    }

    abstract public void onSuccess(ResponseType response);

    abstract public void onFailure(ErrorType response);

    @Override
    public void onSuccess(HTTPResponse response) {
        RawBody body = (RawBody) response.mBody;
        if (body != null) {
            ResponseType obj = null;
            try {
                obj = new JSONConverter<ResponseType>(responseType).decode(body);
                onSuccess(obj);
            } catch (Exception e) {
                onError(e);
            }
        } else {
            onSuccess((ResponseType) null);
        }
    }

    @Override
    public void onFailure(HTTPResponse response) {
        RawBody body = (RawBody) response.mBody;
        if (body != null) {
            ErrorType obj = null;
            try {
                obj = new JSONConverter<ErrorType>(errorType).decode(body);
                onFailure(obj);
            } catch (Exception e) {
                onError(e);
            }
        } else {
            onFailure((ErrorType) null);
        }
    }
}
