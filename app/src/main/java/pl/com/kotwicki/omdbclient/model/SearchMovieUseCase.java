package pl.com.kotwicki.omdbclient.model;

import pl.com.kotwicki.omdbclient.rest.MoviesService;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by filipkotwicki on 25/05/15.
 */
public class SearchMovieUseCase implements UseCase {

    private final MoviesService moviesService;

    private final String searchTitle;

    public SearchMovieUseCase(MoviesService moviesService, String searchTitle) {
        this.moviesService = moviesService;
        this.searchTitle = searchTitle;
    }

    @Override
    public Subscription execute(Subscriber subscriber) {
        return moviesService.findMovie(searchTitle).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(subscriber);
    }

}
