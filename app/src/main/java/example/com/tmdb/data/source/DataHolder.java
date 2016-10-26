package example.com.tmdb.data.source;

import org.natuan.asynchttpclient.Error;

/**
 * Created by Nguyen Anh Tuan on 26/10/2016.
 * natuan.org@gmail.com
 */
public class DataHolder<Data> {
    private Data mData;
    private Error mError;
    private Throwable mThrowable;

    public DataHolder(Data mData, Error mError, Throwable mThrowable) {
        this.mData = mData;
        this.mError = mError;
        this.mThrowable = mThrowable;
    }

    public Data getData() {
        return mData;
    }

    public void setData(Data mData) {
        this.mData = mData;
    }

    public Error getError() {
        return mError;
    }

    public void setError(Error mError) {
        this.mError = mError;
    }

    public Throwable getThrowable() {
        return mThrowable;
    }

    public void setThrowable(Throwable mThrowable) {
        this.mThrowable = mThrowable;
    }
}
