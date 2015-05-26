package pl.com.kotwicki.omdbclient.ui.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.com.kotwicki.omdbclient.R;
import pl.com.kotwicki.omdbclient.rest.model.MovieSearchResult;
import pl.com.kotwicki.omdbclient.ui.ViewHolderClickListener;

/**
 * Created by filipkotwicki on 25/05/15.
 */
class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder> implements ViewHolderClickListener {

    public interface Listener {
        void onMovieSelected(MovieSearchResult.Entry movieSearchResultEntry);
    }

    private final List<MovieSearchResult.Entry> container = new ArrayList<>();

    private final Context context;

    private final Listener listener;

    public SearchResultAdapter(@NonNull final Context context, @NonNull final Listener listener) {
        this.context = context;
        this.listener = listener;
    }

    public void update(final MovieSearchResult movieSearchResult) {
        container.clear();
        if (!movieSearchResult.isEmpty()) {
            container.addAll(movieSearchResult.entries);
        }
        notifyDataSetChanged();
    }

    @Override
    public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(context).inflate(R.layout.row_movie_search_result, parent, false);
        return new SearchResultViewHolder(itemView, this);
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {
        holder.bind(container.get(position));
    }

    @Override
    public int getItemCount() {
        return container.size();
    }

    @Override
    public void onClickViewHolder(int position) {
        listener.onMovieSelected(container.get(position));
    }

    public static class SearchResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @InjectView(R.id.row_movie_search_result_title)
        TextView titleView;

        @InjectView(R.id.row_movie_search_result_year)
        TextView yearView;

        @InjectView(R.id.row_movie_search_result_type)
        TextView typeView;

        final ViewHolderClickListener viewHolderClickListener;

        public SearchResultViewHolder(View itemView, ViewHolderClickListener viewHolderClickListener) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            this.viewHolderClickListener = viewHolderClickListener;
            itemView.setOnClickListener(this);
        }

        void bind(final MovieSearchResult.Entry movieSearchResultEntry) {
            titleView.setText(movieSearchResultEntry.title);
            yearView.setText(movieSearchResultEntry.year);
            typeView.setText(movieSearchResultEntry.type);
        }

        @Override
        public void onClick(View v) {
            viewHolderClickListener.onClickViewHolder(getPosition());
        }
    }

}
