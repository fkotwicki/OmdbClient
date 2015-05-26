package pl.com.kotwicki.omdbclient.rest.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by filipkotwicki on 25/05/15.
 */
public class MovieSearchResult {

    @SerializedName("Search")
    public final List<Entry> entries;

    public MovieSearchResult(List<Entry> entries) {
        this.entries = entries;
    }

    public boolean isEmpty() {
        return (entries == null) ? true : entries.isEmpty();
    }

    public static class Entry {

        @SerializedName("Title")
        public final String title;
        @SerializedName("Year")
        public final String year;
        public final String imdbID;
        @SerializedName("Type")
        public final String type;

        public Entry(String title, String year, String imdbID, String type) {
            this.title = title;
            this.year = year;
            this.imdbID = imdbID;
            this.type = type;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "title='" + title + '\'' +
                    ", year='" + year + '\'' +
                    ", imdbID='" + imdbID + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }
}
