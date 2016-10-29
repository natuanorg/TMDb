package org.natuan.tmdb.data.source;

import android.net.Uri;

import org.natuan.tmdb.BuildConfig;

import java.util.HashMap;


/**
 * Created by Nguyen Anh Tuan on 26/10/2016.
 * natuan.org@gmail.com
 */
public class EndPointRequest {

    final String TMDB_BASE_URL = "https://api.themoviedb.org/3/";
    final String APIKEY = "api_key";
    public static final String MOVIE_POPULAR = "movie/popular";
    public static final String LANGUAGE = "language";
    public static final String LANGUAGE_EN_US = "en-US";


    final String mSourceType;
    final HashMap<String, String> mParameters;

    public EndPointRequest(Builder builder) {
        this.mSourceType = builder.mSourceType;
        this.mParameters = builder.mParameters;
    }

    public static class Builder {
        private String mSourceType;
        private HashMap<String, String> mParameters = new HashMap<>();

        public Builder setSourceType(String sourceType) {
            this.mSourceType = sourceType;
            return this;
        }

        public Builder setParameter(String key, String value) {
            this.mParameters.put(key, value);
            return this;
        }

        public EndPointRequest build() {
            return new EndPointRequest(this);
        }
    }

    public String getUrl() {
        Uri.Builder uriBuilder = Uri.parse(TMDB_BASE_URL).buildUpon();
        uriBuilder.appendEncodedPath(MOVIE_POPULAR);
        uriBuilder.appendQueryParameter(APIKEY, BuildConfig.TMDB_API_KEY);
        if (mParameters != null && mParameters.size() > 0) {
            for (String key:
                 mParameters.keySet()) {
                uriBuilder.appendQueryParameter(key, mParameters.get(key));
            }
        }
        Uri builtUri = uriBuilder.build();
        return builtUri.toString();
    }

}
