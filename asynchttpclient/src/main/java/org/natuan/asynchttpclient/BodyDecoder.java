package org.natuan.asynchttpclient;

/**
 * Created by Nguyen Anh Tuan on 15/10/2016.
 * natuan.org@gmail.com
 */
public interface BodyDecoder<T> {
    T decode(Body body) throws Exception;
}
