package com.abby.adn.popularmovies;

import android.content.Context;
import android.os.Build;

import com.abby.adn.popularmovies.poster.MoviePostersContract;
import com.abby.adn.popularmovies.poster.MoviePostersPresenter;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;
import org.robolectric.shadows.ShadowLog;

import static org.mockito.Matchers.anyList;
import static org.mockito.Mockito.verify;

/**
 * Unit tests for the implementation of {@link MoviePostersPresenter}
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(sdk = Build.VERSION_CODES.LOLLIPOP, constants = BuildConfig.class)
public class PopularMoviesTest {

    @Mock
    private MoviePostersContract.View mView;

    private MoviePostersPresenter mPresenter;
    private Context applicationContext;


    @BeforeClass
    public static void init() {
        ShadowLog.stream = System.out;
        ShadowApplication.getInstance();
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        applicationContext = ShadowApplication.getInstance().getApplicationContext();

    }

    @Test
    public void testPopularMovies() throws InterruptedException {
        // fail("implements!");

        mPresenter = new MoviePostersPresenter(mView);
        String order = applicationContext.getString(R.string.pref_order_value_most_popular);
        mPresenter.updateMoviePosters(order);

        verify(mView).updateList(anyList());
    }

    @Test
    public void testMostRatedMovies() throws InterruptedException {
        // fail("implements!");
        mPresenter = new MoviePostersPresenter(mView);
        String order = applicationContext.getString(R.string.pref_order_value_top_rated);
        mPresenter.updateMoviePosters(order);

        verify(mView).updateList(anyList());
    }
}