package example.com.tmdb;

import org.junit.Test;

import example.com.tmdb.data.source.EndPointRequest;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void endPointRequestTest() {
        EndPointRequest.Builder builder =
                new EndPointRequest.Builder()
                .setSourceType(EndPointRequest.MOVIE_POPULAR)
                .setParameter(EndPointRequest.LANGUAGE, EndPointRequest.LANGUAGE_EN_US);
        EndPointRequest endPointRequest = builder.build();
        System.out.println(endPointRequest.getUrl());
    }
}