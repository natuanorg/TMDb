package org.natuan.asynchttpclient;

/**
 * Created by Nguyen Anh Tuan on 15/10/2016.
 * natuan.org@gmail.com
 */
public class Header {
    final String mName;
    final String mValue;

    public Header(String mName, String mValue) {
        this.mName = mName;
        this.mValue = mValue;
    }

    public String getName() {
        return mName;
    }

    public String getValue() {
        return mValue;
    }
}
