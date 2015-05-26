package pl.com.kotwicki.omdbclient.ui.movie;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.com.kotwicki.omdbclient.R;
import pl.com.kotwicki.omdbclient.rest.model.MovieDetails;

/**
 * Created by filipkotwicki on 26/05/15.
 */
public class MovieDetailsView extends RelativeLayout {

    @InjectView(R.id.view_movie_details_poster)
    ImageView posterView;

    @InjectView(R.id.view_movie_details_plot)
    TextView plotView;

    @InjectView(R.id.view_movie_details_country_with_year)
    TextView countryWithYear;

    @InjectView(R.id.view_movie_details_country_genre)
    TextView genreView;

    @InjectView(R.id.view_movie_details_director)
    TextView directorView;

    @InjectView(R.id.view_movie_details_actors)
    TextView actorsView;

    @InjectView(R.id.view_movie_details_rating)
    TextView ratingView;

    public MovieDetailsView(Context context) {
        this(context, null, 0);
    }

    public MovieDetailsView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MovieDetailsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void fillDetails(MovieDetails movieDetails) {
        plotView.setText(movieDetails.plot);
        countryWithYear.setText(String.format("%s, %s", movieDetails.country, movieDetails.year));
        genreView.setText(movieDetails.genre);
        directorView.setText(movieDetails.director);
        actorsView.setText(movieDetails.actors);
        ratingView.setText(movieDetails.imdbRating);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.view_movie_details, this);
        ButterKnife.inject(this);
    }

    public ImageView getPosterImageView() {
        return posterView;
    }
}
