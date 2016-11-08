package org.natuan.asynchttpclient;

/**
 * Created by Nguyen Anh Tuan on 02/11/2016.
 * natuan.org@gmail.com
 */
public class JsonResponseConverter<ResponseType> {
    /**
     * Convert HTTPResponse to Object
     * @param response
     * @param clazz
     * @return
     */
    public ResponseType convert(HTTPResponse response, Class clazz) {
        RawBody body = (RawBody) response.mBody;
        if (body != null) {
            ResponseType obj = null;
            try {
                obj = (ResponseType) new JSONConverter<ResponseType>()
                        .decode(body, clazz);
                return obj;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
