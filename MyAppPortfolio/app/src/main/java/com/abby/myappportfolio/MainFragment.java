package com.abby.myappportfolio;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        Button button = (Button) rootView.findViewById(R.id.button_movies);
        button.setOnClickListener(this);

        button = (Button) rootView.findViewById(R.id.button_stock);
        button.setOnClickListener(this);

        button = (Button) rootView.findViewById(R.id.button_bigger);
        button.setOnClickListener(this);

        button = (Button) rootView.findViewById(R.id.button4);
        button.setOnClickListener(this);

        button = (Button) rootView.findViewById(R.id.button_ubiquitois);
        button.setOnClickListener(this);

        button = (Button) rootView.findViewById(R.id.button_capstone);
        button.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        String message = "This button will launch my " + button.getText() + " app!";

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();

    }
}