package org.natuan.tmdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.natuan.asynchttpclient.AsyncHttpClient;
import org.natuan.asynchttpclient.AsyncHttpClientImpl;
import org.natuan.asynchttpclient.HTTPProxy;
import org.natuan.asynchttpclient.HTTPRequest;
import org.natuan.asynchttpclient.JsonResponseResult;
import org.natuan.tmdb.data.model.Movie;
import org.natuan.tmdb.data.model.MoviesResponse;
import org.natuan.tmdb.movies.MoviesFragment;
import org.natuan.tmdb.util.ActivityUtils;
import org.natuan.tmdb.util.Logger;

/**
 * Created by Nguyen Anh Tuan on 26/10/2016.
 * natuan.org@gmail.com
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MoviesFragment moviesFragment =
                (MoviesFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (moviesFragment == null) {
            // Create the fragment
            moviesFragment = MoviesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(
                    getSupportFragmentManager(), moviesFragment, R.id.contentFrame);
        }
    }
}
