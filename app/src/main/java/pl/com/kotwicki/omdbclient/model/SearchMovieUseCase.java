package pl.com.kotwicki.omdbclient.model;

import java.util.Collections;
import java.util.Comparator;

import pl.com.kotwicki.omdbclient.rest.MoviesService;
import pl.com.kotwicki.omdbclient.rest.model.MovieSearchResult;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
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
        return moviesService.findMovie(searchTitle).
                flatMap(new Func1<MovieSearchResult, Observable<?>>() {
                    @Override
                    public Observable<?> call(MovieSearchResult movieSearchResult) {
                        if(!movieSearchResult.isEmpty()) {
                            Collections.sort(movieSearchResult.entries, new Comparator<MovieSearchResult.Entry>() {
                                @Override
                                public int compare(MovieSearchResult.Entry lhs, MovieSearchResult.Entry rhs) {
                                    return rhs.year.compareTo(lhs.year);
                                }
                            });
                        }
                        return Observable.just(movieSearchResult);
                    }
                }).
                observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(subscriber);
    }

}
