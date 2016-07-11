package com.abby.adn.popularmovies.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by gsshop on 2016. 7. 11..
 */
public interface TheMovieDBApiService {
    String POPULAR_MOVE_BASE_URL = "http://api.themoviedb.org/";

    @GET("3/movie/{order}")
    Call<String> getMoviePosters(@Path("order") String order, @Query("api_key") String apiKey);
}
