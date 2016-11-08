package org.natuan.asynchttpclient;

/**
 * Created by Nguyen Anh Tuan on 02/11/2016.
 * natuan.org@gmail.com
 */
public class JsonResponseResult<T> {
    T object;
    Throwable error;

    public JsonResponseResult() {
    }

    public JsonResponseResult(T object, Throwable error) {
        this.object = object;
        this.error = error;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
