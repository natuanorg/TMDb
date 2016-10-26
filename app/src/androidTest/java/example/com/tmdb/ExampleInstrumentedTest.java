package example.com.tmdb;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import example.com.tmdb.data.source.EndPointRequest;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("example.com.tmdb", appContext.getPackageName());
    }

    @Test
    public void endPointRequestTest() {
        EndPointRequest.Builder builder =
                new EndPointRequest.Builder()
                        .setSourceType(EndPointRequest.MOVIE_POPULAR)
                        .setParameter(EndPointRequest.LANGUAGE, EndPointRequest.LANGUAGE_EN_US);
        EndPointRequest endPointRequest = builder.build();
        Log.d("TMDB", endPointRequest.getUrl());
    }
}
