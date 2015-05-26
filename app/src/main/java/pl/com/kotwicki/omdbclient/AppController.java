package pl.com.kotwicki.omdbclient;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import pl.com.kotwicki.omdbclient.di.ApplicationComponent;
import pl.com.kotwicki.omdbclient.di.ApplicationModule;
import pl.com.kotwicki.omdbclient.di.DaggerApplicationComponent;
import pl.com.kotwicki.omdbclient.rest.RestModule;

/**
 * Created by filipkotwicki on 25/05/15.
 */
public class AppController extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(new MainThreadBus())).restModule(new RestModule()).build();
    }

    public ApplicationComponent applicationComponent() {
        return applicationComponent;
    }

    public static AppController from(@NonNull final Context context) {
        return (AppController) context.getApplicationContext();
    }
}
