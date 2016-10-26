package example.com.tmdb.data.source;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import org.natuan.asynchttpclient.Error;
import org.natuan.asynchttpclient.JsonResponseHandler;

import example.com.tmdb.data.model.MoviesResponse;

/**
 * Created by Nguyen Anh Tuan on 26/10/2016.
 * natuan.org@gmail.com
 */
public class MoviesLoader extends BaseLoader<MoviesResponse> {

    public MoviesLoader(Context context) {
        super(context);
    }

    @Override
    public JsonResponseHandler getResponseHandler() {
        return new JsonResponseHandler<MoviesResponse, Error>(
                new TypeToken<MoviesResponse>(){}.getType(),
                new TypeToken<Error>(){}.getType()
            ) {
                @Override
                public void onSuccess(MoviesResponse response) {
                    mDataHolder = new DataHolder<>(response, null, null);
                    deliverResult(mDataHolder);
                }

                @Override
                public void onFailure(Error response) {
                    mDataHolder = new DataHolder<>(null, response, null);
                    deliverResult(mDataHolder);
                }

                @Override
                public void onError(Throwable error) {
                    mDataHolder = new DataHolder<>(null, null, error);
                    deliverResult(mDataHolder);
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
