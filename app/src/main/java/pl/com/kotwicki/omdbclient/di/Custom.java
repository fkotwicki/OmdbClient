package pl.com.kotwicki.omdbclient.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by filipkotwicki on 25/05/15.
 */
@Scope
@Retention(RUNTIME)
public @interface Custom {
}
