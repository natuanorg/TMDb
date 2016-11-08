package org.natuan.asynchttpclient;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Base64;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Nguyen Anh Tuan on 15/10/2016.
 * natuan.org@gmail.com
 */
public class AsyncHttpClientImpl<T> implements AsyncHttpClient {

    @Override
    public AsyncTask excuteAsync(HTTPRequest request, ResponseHandler handler) {
        return new HTTPAsyncTask(handler)
                .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request);
    }

    @Override
    public T excuteSync(HTTPRequest request, Class clazz) {
        try {
            Result<HTTPResponse> result = new HTTPAsyncTask()
                    .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, request).get();
            if (result.error == null) {
                return new JsonResponseConverter<T>()
                        .convert(result.obj, clazz);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    public class HTTPAsyncTask extends AsyncTask<HTTPRequest, Void, Result<HTTPResponse>> {

        private ResponseHandler mHandler;

        public HTTPAsyncTask() {
        }

        public HTTPAsyncTask(ResponseHandler mHandler) {
            this.mHandler = mHandler;
        }

        void fillHeaders(Map<String, List<String>> headers, HTTPResponse.Builder builder) {
            for (String name : headers.keySet()) {
                for (String value : headers.get(name)) {
                    builder.addHeader(new Header(name, value));
                }
            }
        }

        void setRequestHeaders(@NonNull HttpURLConnection con,@NonNull HTTPRequest request) {
            for (Header header : request.mHeader) {
                con.addRequestProperty(header.getName(), header.getValue());
            }
        }

        void setRequestBody(@NonNull HttpURLConnection con, @NonNull HTTPRequest request) throws IOException {
            if (request.mBody != null) {
                //Allow sending data on the request
                con.setDoOutput(true);
                con.setRequestProperty("Content-type", request.mBody.getMineType());
                OutputStream os = con.getOutputStream();
                request.mBody.write(os);
            }
        }

        HttpURLConnection setupProxyConnection(URL url,HTTPProxy httpProxy) throws IOException {
            HttpsURLConnection con = null;

            Proxy proxy = new Proxy( httpProxy.mType.toType(),
                    new InetSocketAddress(httpProxy.mHost,
                            httpProxy.mPort));

            con =  (HttpsURLConnection) url.openConnection(proxy);

            if ( httpProxy.mUsername !=  null ) {
                String authString = httpProxy.mUsername + ":" + httpProxy.mPassword;
                String encoded = Base64.encodeToString(authString.getBytes(), 0);
                con.setRequestProperty("Proxy-Authorization", "Basic " + encoded);
            }
            return con;
        }

        HttpURLConnection initConnection(HTTPRequest request, URL url) throws IOException {
            HttpURLConnection result;
            HTTPProxy httpProxy = request.mProxy;
            if (httpProxy != null) {
                result = setupProxyConnection(url, httpProxy);
            } else {
                result = (HttpURLConnection) url.openConnection();
            }
            return result;
        }

        @Override
        protected Result<HTTPResponse> doInBackground(HTTPRequest... params) {
            HTTPRequest request = params[0];
            Body body = null;
            HttpURLConnection conn = null;
            Result<HTTPResponse> response = new Result<>();

            try {
                //Retrieve the request URL from the request object.
                URL url = new URL(request.mUrl);
                //Open the connection to the remote
                conn = initConnection(request, url);
                //Set read timeout.
                conn.setReadTimeout(request.mReadTimeout);
                //Set connect timeout.
                conn.setConnectTimeout(request.mConnectTimeout);
                //Set the HTTP Request Verb.
                conn.setRequestMethod(request.mVerb);
                //Allow Receiving data on the response.
                conn.setDoInput(true);
                //Set the request headers
                setRequestHeaders(conn, request);
                //Set and write the request body
                setRequestBody(conn, request);

                int responseCode = conn.getResponseCode();
                HTTPResponse.Builder builder = new HTTPResponse.Builder()
                        .setResponseCode(responseCode)
                        .setResponseMessage(conn.getResponseMessage());
                fillHeaders(conn.getHeaderFields(), builder);
                body = BodyFactory.read(conn.getContentType(),
                        conn.getInputStream());
                builder.setBody(body);

                //Build the HTTP Response Object.
                response.obj = builder.build();
            } catch (IOException e) {
                response.error = e;
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }

            return response;
        }

        @Override
        protected void onPostExecute(Result<HTTPResponse> result) {
            if (mHandler != null) {
                if (result.error != null) {
                    mHandler.onError(result.error);
                } else {
                    mHandler.onSuccess(result.obj);
                }
            }
        }
    }
}
