package org.natuan.asynchttpclient;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;

/**
 * Created by Nguyen Anh Tuan on 15/10/2016.
 * natuan.org@gmail.com
 */
public class JSONConverter<POJO> implements BodyEncoder<POJO>, BodyDecoder<POJO> {

    @Override
    public POJO decode(Body body, Type type) throws Exception {
        Gson gson = new Gson();
        RawBody rawBody = (RawBody) body;
        InputStream is = null;
        POJO ojb = null;
        try {
            is = new ByteArrayInputStream(rawBody.getContent());
            BufferedReader bfReader = new BufferedReader(new InputStreamReader(is));
            ojb = gson.fromJson(bfReader, type);
        } finally {
            if (is != null) {
                is.close();
            }
        }

        return ojb;
    }

    @Override
    public POJO decode(Body body, Class<POJO> clazz) throws Exception {
        Gson gson = new Gson();
        RawBody rawBody = (RawBody) body;
        InputStream is = null;
        POJO ojb = null;
        try {
            is = new ByteArrayInputStream(rawBody.getContent());
            BufferedReader bfReader = new BufferedReader(new InputStreamReader(is));
            ojb = gson.fromJson(bfReader, clazz);
        } finally {
            if (is != null) {
                is.close();
            }
        }

        return ojb;
    }

    @Override
    public Body encode(POJO obj, String mimeType) throws Exception {
        Gson gson = new Gson();
        String result = gson.toJson(obj);
        RawBody body = new RawBody(mimeType);
        body.setContent(result.getBytes());
        return body;
    }
}
