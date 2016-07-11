package com.abby.adn.popularmovies.setting;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Show Setting for Movie Poster
 */
public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new SettingPreferenceFragment())
                    .commit();
        }
    }

}
