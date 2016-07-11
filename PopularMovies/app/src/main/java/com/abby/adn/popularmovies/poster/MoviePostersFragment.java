package com.abby.adn.popularmovies.poster;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.abby.adn.popularmovies.data.Poster;
import com.abby.adn.popularmovies.R;
import com.abby.adn.popularmovies.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Encapsulates fetching movie posters and displaying it as a {@link GridView} layout.
 */
public class MoviePostersFragment extends Fragment implements MoviePostersContract.View{


    public static final String PARAM_KEY_MOVIE = "_param_key_movie";
    private ArrayAdapter<Poster> mPosterAdapter;
    private MoviePostersContract.Presenter mPresenter;

    @BindView(R.id.gridview_movie)
    GridView mGridView;

    public MoviePostersFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // The ArrayAdapter will take data from a source and
        // use it to populate the ListView it's attached to.
        mPosterAdapter = new MoviePostersAdapter(getActivity(), new ArrayList<Poster>());
        mPresenter = new MoviePostersPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_poster, container, false);
        ButterKnife.bind(this, rootView);

        // Get a reference to the GridView, and attach this adapter to it.
        mGridView.setAdapter(mPosterAdapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Poster movie = mPosterAdapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putParcelable(PARAM_KEY_MOVIE, movie);
                intent.putExtras(mBundle);
                startActivity(intent);

            }
        });

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        updatePopularMovies();
    }

    @Override
    public void updateList(List<Poster> posters) {
        mPosterAdapter.clear();
        mPosterAdapter.addAll(posters);
        mPosterAdapter.notifyDataSetChanged();
    }

    private void updatePopularMovies() {
        if(!isOnline()) {
            Toast.makeText(getContext(), R.string.message_no_network_connection, Toast.LENGTH_SHORT).show();
            return;
        }
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String order = prefs.getString(getString(R.string.pref_order_key),
                getString(R.string.pref_order_value_most_popular));
        try {
            mPresenter.updateMoviePosters(order);
        } catch (InterruptedException e) {
        }
    }


    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

}
