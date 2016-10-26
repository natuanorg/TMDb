package org.natuan.asynchttpclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Nguyen Anh Tuan on 15/10/2016.
 * natuan.org@gmail.com
 */
public class TextPlainBody extends Body {

    private String mContent;

    public TextPlainBody(String mMineType) {
        super(mMineType);
    }



    @Override
    void consume(InputStream inputStream) throws IOException {
        byte[] content = read(inputStream);
        mContent = new String(content);
    }

    @Override
    void write(OutputStream out) throws IOException {
        out.write(mContent.getBytes());
    }

    public String getContent() {
        return mContent;
    }
}
