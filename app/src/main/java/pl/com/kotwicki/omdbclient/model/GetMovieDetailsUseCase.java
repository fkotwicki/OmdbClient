package pl.com.kotwicki.omdbclient.model;

import pl.com.kotwicki.omdbclient.rest.MoviesService;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by filipkotwicki on 26/05/15.
 */
public class GetMovieDetailsUseCase implements UseCase {

    private static final String PLOT_FULL = "full";

    private final MoviesService moviesService;

    private final String imdbID;

    public GetMovieDetailsUseCase(MoviesService moviesService, String imdbID) {
        this.moviesService = moviesService;
        this.imdbID = imdbID;
    }

    @Override
    public Subscription execute(Subscriber subscriber) {
        return moviesService.getMovieDetails(imdbID, PLOT_FULL).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(subscriber);
    }

}
