package org.natuan.asynchttpclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Nguyen Anh Tuan on 15/10/2016.
 * natuan.org@gmail.com
 */
public class RawBody extends Body {

    private byte[] mContent = null;

    public RawBody(String mMineType) {
        super(mMineType);
    }

    public byte[] getContent() {
        return mContent;
    }

    public void setContent(byte[] content) {
        mContent = content;
    }

    @Override
    void consume(InputStream inputStream) throws IOException {
        mContent = this.read(inputStream);
    }

    @Override
    void write(OutputStream out) throws IOException {
        out.write(mContent);
        out.flush();
        out.close();
    }
}
