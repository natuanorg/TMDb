package org.natuan.asynchttpclient;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Nguyen Anh Tuan on 15/10/2016.
 * natuan.org@gmail.com
 */
public class BodyFactory {

    public static Body read(String mimeType, InputStream is) throws IOException {

        Body result = null;

        if (mimeType.startsWith("text")) {
            result = new TextPlainBody(mimeType);
        } else if (mimeType.startsWith("application/json")
                || mimeType.startsWith("application/xml")) {
            result = new RawBody(mimeType);
        }

        if (result != null) {
            result.consume(is);
        }

        return result;
    }
}
