package pl.com.kotwicki.omdbclient.ui.search;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.ButterKnife;
import butterknife.InjectView;
import pl.com.kotwicki.omdbclient.R;

/**
 * Created by filipkotwicki on 25/05/15.
 */
public class SearchForm extends LinearLayout implements View.OnClickListener {

    public interface Listener {
        void onRequestSearch(String searchText);
    }

    @InjectView(R.id.form_search_text_et)
    EditText searchTextView;

    @InjectView(R.id.form_search_btn)
    Button doSearchView;

    private Listener formListener;

    public SearchForm(Context context) {
        this(context, null, 0);
    }

    public SearchForm(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SearchForm(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onClick(View v) {
        final String searchText = searchTextView.getText().toString();
        if (formListener != null) {
            hideKeyboard();
            formListener.onRequestSearch(searchText);
        }
    }

    public void setFormListener(@Nullable Listener formListener) {
        this.formListener = formListener;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        LayoutInflater.from(getContext()).inflate(R.layout.form_search, this);
        ButterKnife.inject(this);

        doSearchView.setOnClickListener(this);
        doSearchView.setEnabled(false);
        searchTextView.addTextChangedListener(searchTextWatcher);
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getContext().getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchTextView.getWindowToken(), 0);
    }

    private final TextWatcher searchTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            final boolean empty = TextUtils.isEmpty(s.toString());
            doSearchView.setEnabled(!empty);
        }
    };
}
