package com.abby.myappportfolio;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment =
                (MainFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        if (mainFragment == null) {
            // Create the fragment
            mainFragment = MainFragment.newInstance();

            getSupportFragmentManager().beginTransaction().add(R.id.container, mainFragment).commit();
        }
    }

}
