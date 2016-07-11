package com.abby.adn.popularmovies.poster;

import android.os.AsyncTask;
import android.util.Log;

import com.abby.adn.popularmovies.BuildConfig;
import com.abby.adn.popularmovies.data.Poster;
import com.abby.adn.popularmovies.data.TheMovieDBApiService;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Listens to user actions from the UI ({@link MoviePostersFragment}), retrieves the data and updates
 * the UI as required.
 */
public class MoviePostersPresenter implements MoviePostersContract.Presenter {
    private final MoviePostersContract.View mView;
    private final TheMovieDBApiService mService;

    // use test case
//    private CountDownLatch signal=null;

    public MoviePostersPresenter(MoviePostersContract.View view) {
        this.mView = view;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TheMovieDBApiService.POPULAR_MOVE_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        mService = retrofit.create(TheMovieDBApiService.class);
    }


    @Override
    public void updateMoviePosters(String order) throws InterruptedException {
        // use test case
//        signal = new CountDownLatch(1);
        new FetchMoviePostersTask().execute(order);
        // use test case
//        signal.await();
    }


    /**
     * fetch data with theMovieDB API.
     */
    public class FetchMoviePostersTask extends AsyncTask<String, Void, List<Poster>> {
        private final String LOG_TAG = FetchMoviePostersTask.class.getSimpleName();

        private List<Poster> getMoviePostersDataFromJson(String moviePostersJsonStr) throws JSONException {

            // These are the names of the JSON objects that need to be extracted.
            final String OWM_RESULTS = "results";

            JSONObject posterJson = new JSONObject(moviePostersJsonStr);
            JSONArray posterArray = posterJson.getJSONArray(OWM_RESULTS);
            Poster[] posters =  new Gson().fromJson(posterArray.toString(), Poster[].class);

            return Arrays.asList(posters);
        }


        @Override
        protected List<Poster> doInBackground(String... params) {

            // If there's no zip code, there's nothing to look up.  Verify size of params.
            if (params.length == 0) {
                return null;
            }

            // Will contain the raw JSON response as a string.
            String moviePostersJsonStr = null;

            try {
                Call<String> call = mService.getMoviePosters(params[0], BuildConfig.THE_MOVIE_DB_API_KEY);
                Response<String> response = call.execute();
                moviePostersJsonStr = response.body();

                Log.v(LOG_TAG, moviePostersJsonStr);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // If the code didn't successfully get the weather data, there's no point in attemping
                // to parse it.
                return null;
            }

            try {
                return getMoviePostersDataFromJson(moviePostersJsonStr);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }

            // This will only happen if there was an error getting or parsing the forecast.
            return null;
        }

        @Override
        protected void onPostExecute(List<Poster> posters) {
            super.onPostExecute(posters);
            if (posters.size() > 0) {
                mView.updateList(posters);
            }
            // use test case
//            signal.countDown();
        }
    }

}
