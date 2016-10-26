package org.natuan.asynchttpclient;

/**
 * Created by Nguyen Anh Tuan on 15/10/2016.
 * natuan.org@gmail.com
 */
public interface BodyEncoder<T> {
    Body encode(T obj, String mimeType) throws Exception;
}
