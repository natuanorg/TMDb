package org.natuan.asynchttpclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Nguyen Anh Tuan on 15/10/2016.
 * natuan.org@gmail.com
 */
public abstract class Body {

    final String mMineType;

    public Body(String mMineType) {
        this.mMineType = mMineType;
    }

    public String getMineType() {
        return mMineType;
    }

    abstract void consume(InputStream inputStream) throws IOException;

    abstract void write(OutputStream out) throws IOException;

    protected byte[] read(InputStream is) throws IOException {
        byte[] result = null;
        try {
            if (is != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[128];
                int rc = 0;
                while ((rc = is.read(buffer)) > 0) {
                    baos.write(buffer, 0, rc);
                }
                result = baos.toByteArray();
            }
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return result;
    }
}
