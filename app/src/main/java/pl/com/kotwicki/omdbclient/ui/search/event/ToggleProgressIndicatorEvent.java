package pl.com.kotwicki.omdbclient.ui.search.event;

/**
 * Created by filipkotwicki on 26/05/15.
 */
public class ToggleProgressIndicatorEvent {

    public final boolean show;

    public ToggleProgressIndicatorEvent(boolean show) {
        this.show = show;
    }

}
