package com.abby.adn.popularmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Immutable model class for a Poster.
 */
public class Poster implements Parcelable {
    // original title
    @SerializedName("original_title")
    private String mOriginalTitle;

    //    movie poster image thumbnail
    @SerializedName("poster_path")
    private final String mPosterPath;

    //    A plot synopsis (called overview in the api)
    @SerializedName("overview")
    private final String mOverview;

    //    user rating (called vote_average in the api)
    @SerializedName("vote_average")
    private final double mVoteAverage;

    // release date
    @SerializedName("release_date")
    private final String releaseDate;

    public Poster(Parcel in) {
        this.mOriginalTitle = in.readString();
        this.mPosterPath = in.readString();
        this.mOverview = in.readString();
        this.mVoteAverage = in.readDouble();
        this.releaseDate = in.readString();
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getOverview() {
        return mOverview;
    }

    public double getVoteAverage() {
        return mVoteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mOriginalTitle);
        dest.writeString(this.mPosterPath);
        dest.writeString(this.mOverview);
        dest.writeDouble(this.mVoteAverage);
        dest.writeString(this.releaseDate);

    }

    public static final Parcelable.Creator<Poster> CREATOR = new Parcelable.Creator<Poster>() {
        @Override
        public Poster createFromParcel(Parcel source) {
            return new Poster(source);
        }

        @Override
        public Poster[] newArray(int size) {
            return new Poster[size];
        }
    };
}

