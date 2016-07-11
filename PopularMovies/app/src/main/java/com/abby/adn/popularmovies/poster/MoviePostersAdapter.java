package com.abby.adn.popularmovies.poster;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.abby.adn.popularmovies.data.Poster;
import com.abby.adn.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Move Poster GridView Adapter
 */
public class MoviePostersAdapter extends ArrayAdapter<Poster> {
    private static final String THUMBNAIL_BASE_URL = "http://image.tmdb.org/t/p/w185";

    public MoviePostersAdapter(Activity activity, ArrayList<Poster> posters) {
        super(activity, 0, posters);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_poster, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        Poster poster = getItem(position);

        String url = THUMBNAIL_BASE_URL + poster.getPosterPath();
        Picasso.with(getContext())
                .load(url)
                .placeholder(R.drawable.poster_placeholder)
                .error(R.drawable.poster_placeholder_error)
                .into(holder.mThumbnailView);

        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.imageview_thumbnail)
        ImageView mThumbnailView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
