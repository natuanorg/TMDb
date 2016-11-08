package org.natuan.asynchttpclient;

import java.net.Proxy.Type;

/**
 * 11/1/2016
 */
public class HTTPProxy {

    public enum ProxyType {
        SOCKS,
        HTTP;
        Type toType() {
            switch (this) {
                case SOCKS:
                    return Type.SOCKS;
                case HTTP:
                    return Type.HTTP;
            }
            return Type.DIRECT;
        }
    }


    final String mHost;
    final int mPort;
    final String mUsername;
    final String mPassword;
    final ProxyType mType;

    public HTTPProxy(Builder builder) {
        this.mHost = builder.mHost;
        this.mPort = builder.mPort;
        this.mUsername = builder.mUserName;
        this.mPassword = builder.mPassword;
        this.mType = builder.mType;
    }

    public static class Builder {
        String mHost;
        int mPort;
        String mUserName;
        String mPassword;
        ProxyType mType;

        public Builder setHost(String host) {
            this.mHost = host;
            return this;
        }
        public Builder setPort(int port) {
            this.mPort = port;
            return this;
        }
        public Builder setUsername(String username) {
            this.mUserName = username;
            return this;
        }
        public Builder setPassword(String password) {
            this.mPassword = password;
            return this;
        }
        public Builder setType(ProxyType type) {
            this.mType = type;
            return this;
        }

        public HTTPProxy build() {
            return new HTTPProxy(this);
        }

    }

}
