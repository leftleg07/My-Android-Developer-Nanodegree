package com.abby.adn.popularmovies.poster;

import com.abby.adn.popularmovies.data.Poster;

import java.util.List;

/**
 * Created by gsshop on 2016. 7. 8..
 */
public interface MoviePostersContract {
    interface View {
        void updateList(List<Poster> posters);
    }
    interface Presenter {
        void updateMoviePosters(String order) throws InterruptedException;
    }


}
