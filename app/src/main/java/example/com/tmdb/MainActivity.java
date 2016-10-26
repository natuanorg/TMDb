package example.com.tmdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import example.com.tmdb.movies.MoviesFragment;
import example.com.tmdb.util.ActivityUtils;
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
