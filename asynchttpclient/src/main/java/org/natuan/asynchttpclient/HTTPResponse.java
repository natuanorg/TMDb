package org.natuan.asynchttpclient;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Nguyen Anh Tuan on 15/10/2016.
 * natuan.org@gmail.com
 */
public class HTTPResponse {
    final String mResponseMessage;
    final int mResponseCode;
    final List<Header> mHeaders;
    final Body mBody;

    public HTTPResponse(Builder builder) {
        this.mResponseCode = builder.mResponseCode;
        this.mResponseMessage = builder.mResponseMessage;
        this.mBody = builder.mBody;
        this.mHeaders = builder.mHeader;
    }

    public static class Builder {
        private int mResponseCode;
        private String mResponseMessage;
        private List<Header> mHeader = new LinkedList<>();
        private Body mBody;

        public Builder setResponseCode(int responseCode) {
            this.mResponseCode = responseCode;
            return this;
        }

        public Builder setResponseMessage(String message) {
            this.mResponseMessage = message;
            return this;
        }

        public Builder setBody(Body body) {
            this.mBody = body;
            return this;
        }

        public Builder addHeader(Header header) {
            this.mHeader.add(header);
            return this;
        }

        HTTPResponse build() {
            return new HTTPResponse(this);
        }
    }
}
