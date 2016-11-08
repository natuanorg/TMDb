package org.natuan.asynchttpclient;

import java.lang.reflect.Type;

/**
 * Created by Nguyen Anh Tuan on 15/10/2016.
 * natuan.org@gmail.com
 */
public interface BodyDecoder<T> {
    T decode(Body body, Type type) throws Exception;
    T decode(Body body, Class<T> clazz) throws Exception;
}
