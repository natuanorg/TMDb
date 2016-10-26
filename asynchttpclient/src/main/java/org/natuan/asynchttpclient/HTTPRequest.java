package org.natuan.asynchttpclient;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Nguyen Anh Tuan on 15/10/2016.
 * natuan.org@gmail.com
 */
public class HTTPRequest {

    public enum Verb {
        POST,
        GET,
        PUT,
        UPDATE,
        DELETE
    }

    final String mVerb;
    final String mUrl;
    final List<Header> mHeader;
    final Map<String, String> mParameters;
    final Body mBody;
    final int mReadTimeout;
    final int mConnectTimeout;
    final SSLOptions mSSslOptions;

    public HTTPRequest(Builder builder) {
        this.mVerb = toString(builder.mVerb);
        this.mUrl = builder.mUrl;
        this.mHeader = builder.mHeader;
        this.mBody = builder.mBody;
        this.mParameters = builder.mParameters;
        this.mReadTimeout = builder.mRealTimeout;
        this.mConnectTimeout = builder.mConnectTimeout;
        this.mSSslOptions = builder.mSSLOptions;
    }

    String toString(Verb verb) {
        switch (verb) {
            case POST:
                return "POST";
            case GET:
                return "GET";
            case PUT:
                return "PUT";
            case UPDATE:
                return "UPDATE";
            case DELETE:
                return "DELETE";
            default:
                return "";
        }
    }

    public static class Builder {
        private Verb mVerb;
        private String mUrl;
        private List<Header> mHeader = new LinkedList<>();
        private Map<String, String> mParameters = new HashMap<>();
        private Body mBody;
        private int mRealTimeout;
        private int mConnectTimeout;
        private SSLOptions mSSLOptions = null;

        public Builder setVerb(Verb verb) {
            this.mVerb = verb;
            return this;
        }

        public Builder setUrl(String url) {
            this.mUrl = url;
            return this;
        }

        public Builder addHeader(List<Header> header) {
            this.mHeader = header;
            return this;
        }

        public Builder setParameters(String name, String value) {
            this.mParameters.put(name, value);
            return this;
        }

        public Builder setBody(Body body) {
            this.mBody = body;
            return this;
        }

        public Builder setSSLOptions(SSLOptions sslOptions) {
            this.mSSLOptions = sslOptions;
            return this;
        }

        public void setRealTimeout(int realTimeout) {
            this.mRealTimeout = realTimeout;
        }

        public void setConnectTimeout(int connectTimeout) {
            this.mConnectTimeout = connectTimeout;
        }

        public HTTPRequest build() {
            return new HTTPRequest(this);
        }
    }

}
