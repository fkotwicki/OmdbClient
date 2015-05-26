package pl.com.kotwicki.omdbclient.rest.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by filipkotwicki on 26/05/15.
 */
public class MovieDetails {

    @SerializedName("Year")
    public final String year;

    @SerializedName("Released")
    public final String released;

    @SerializedName("Genre")
    public final String genre;

    @SerializedName("Director")
    public final String director;

    @SerializedName("Actors")
    public final String actors;

    @SerializedName("Plot")
    public final String plot;

    @SerializedName("Country")
    public final String country;

    @SerializedName("Poster")
    public final String posterURL;

    public final String imdbRating;

    public MovieDetails(String year, String released, String genre, String director, String actors, String plot, String country, String posterURL, String imdbRating) {
        this.year = year;
        this.released = released;
        this.genre = genre;
        this.director = director;
        this.actors = actors;
        this.plot = plot;
        this.country = country;
        this.posterURL = posterURL;
        this.imdbRating = imdbRating;
    }

    @Override
    public String toString() {
        return "MovieDetails{" +
                "year='" + year + '\'' +
                ", released='" + released + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                ", country='" + country + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                '}';
    }
}
