package org.natuan.tmdb.data.source;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import org.natuan.asynchttpclient.Error;
import org.natuan.asynchttpclient.JsonResponseHandler;
import org.natuan.tmdb.data.model.MoviesResponse;

/**
 * Created by Nguyen Anh Tuan on 26/10/2016.
 * natuan.org@gmail.com
 */
public class MoviesLoader extends BaseLoader<MoviesResponse> {

    public MoviesLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
    }

    @Override
    public void deliverResult(MoviesResponse data) {
        super.deliverResult(data);
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
    }

    @Override
    protected void onReset() {
        super.onReset();
    }

    @Override
    protected void onForceLoad() {
        super.onForceLoad();
    }

    @Override
    public JsonResponseHandler getResponseHandler() {
        return new JsonResponseHandler<MoviesResponse, Error>(
                new TypeToken<MoviesResponse>(){}.getType(),
                new TypeToken<Error>(){}.getType()
            ) {
                @Override
                public void onSuccess(MoviesResponse response) {
                    deliverResult(response);
                }

                @Override
                public void onFailure(Error response) {
                    deliverResult(null);
                }

                @Override
                public void onError(Throwable error) {
                    deliverResult(null);
                }
            };
    }

    @Override
    public String getUrl() {
        EndPointRequest.Builder endPointbuilder =
                new EndPointRequest.Builder()
                        .setSourceType(EndPointRequest.MOVIE_POPULAR)
                        .setParameter(EndPointRequest.LANGUAGE, EndPointRequest.LANGUAGE_EN_US);
        return endPointbuilder.build().getUrl();
    }
}
