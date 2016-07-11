package com.abby.adn.popularmovies.detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.abby.adn.popularmovies.data.Poster;
import com.abby.adn.popularmovies.R;
import com.abby.adn.popularmovies.poster.MoviePostersFragment;

/**
 * Displays movie details screen.
 */
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle extras = getIntent().getExtras();
        Poster movie = extras.getParcelable(MoviePostersFragment.PARAM_KEY_MOVIE);

        // Display the fragment as the main content.
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, DetailFragment.newInstance(movie))
                    .commit();
        }
    }
}
