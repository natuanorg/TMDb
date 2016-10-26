package org.natuan.asynchttpclient;

import android.content.Context;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by Nguyen Anh Tuan on 15/10/2016.
 * natuan.org@gmail.com
 */
public class SSLOptions {

    public enum CipherSuite {
        DEFAULT("Default"),
        SSL("SSL"),
        SSLv3("SSLv3"),
        TLS("TLS"),
        TLSv1("TLSv1"),
        TLSv1_1("TLSv1.1"),
        TLSv1_2("TLSv1.2");

        private final String cipherSuite;

        private CipherSuite(String cipherSuite) {
            this.cipherSuite = cipherSuite;
        }

        @Override
        public String toString() {
            return cipherSuite;
        }
    }

    final SSLContext sslContext;
    final CipherSuite cipherSuite;
    private final String keyStore;
    private final String keyStorePassword;
    private final String trustStore;
    private final String trustStorePassword;

    SSLOptions(Context context, Builder builder) throws Exception{
        cipherSuite = builder.cipherSuite;
        keyStore = builder.keyStore;
        keyStorePassword = builder.keyStorePassword;
        trustStore = builder.trustStore;
        trustStorePassword = builder.trustStorePassword;
        sslContext = initSSLContext(context);
    }

    private SSLContext initSSLContext(Context context) throws Exception{
        KeyManagerFactory kmf = getKeyManagerFactory(context);
        TrustManagerFactory tmf = getTrustManagerFactory(context);
        SSLContext result = SSLContext.getInstance(cipherSuite.toString());
        result.init(kmf != null ? kmf.getKeyManagers() : null,
                tmf != null ? tmf.getTrustManagers() : null,
                new SecureRandom());
        return result;
    }

    KeyManagerFactory getKeyManagerFactory(Context context) throws Exception {
        KeyManagerFactory kmf = null;
        if (keyStore != null) {
            InputStream keyStoreIs = context.getResources().getAssets().open(keyStore);
            String algorithm = KeyManagerFactory.getDefaultAlgorithm();
            kmf = KeyManagerFactory.getInstance(algorithm);
            KeyStore ks = KeyStore.getInstance("BKS");
            ks.load(keyStoreIs, keyStorePassword.toCharArray());
            kmf.init(ks, keyStorePassword.toCharArray());
        }
        return kmf;
    }

    TrustManagerFactory getTrustManagerFactory(Context context) throws Exception{
        TrustManagerFactory tmf = null;
        if (trustStore != null) {
            InputStream keyStoreIs = context.getResources().getAssets().open(trustStore);
            String algorithm = TrustManagerFactory.getDefaultAlgorithm();
            tmf = TrustManagerFactory.getInstance(algorithm);
            KeyStore ts = KeyStore.getInstance("BKS");
            ts.load(keyStoreIs, trustStorePassword.toCharArray());
            tmf.init(ts);
        }
        return tmf;
    }

    public static class Builder {
        private CipherSuite cipherSuite = CipherSuite.DEFAULT;
        private String keyStore = null;
        private String keyStorePassword = null;
        private String trustStore = null;
        private String trustStorePassword = null;

        public Builder() {
        }

        public Builder setCipherSuite(CipherSuite cipherSuite) {
            this.cipherSuite = cipherSuite;
            return this;
        }

        public Builder setTrustStore(String trustStore, String trustStorePassword) {
            this.trustStore = trustStore;
            this.trustStorePassword = trustStorePassword;
            return this;
        }

        public Builder setKeyStore(String keyStore, String keyStorePassword) {
            this.keyStore = keyStore;
            this.keyStorePassword = keyStorePassword;
            return this;
        }

        public SSLOptions build(Context context) throws Exception{
            return new SSLOptions(context, this);
        }
    }

}
