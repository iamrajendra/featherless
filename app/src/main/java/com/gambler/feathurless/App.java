package com.gambler.feathurless;

import android.app.Application;

import org.feathersjs.client.Feathers;
import org.feathersjs.client.plugins.authentication.AuthenticationOptions;
import org.feathersjs.client.plugins.authentication.FeathersAuthenticationConfiguration;
import org.feathersjs.client.plugins.providers.FeathersSocketIO;
import org.feathersjs.client.plugins.storage.SharedPreferencesStorageProvider;

public class App extends Application {
    private static final String BASE_URL = "http://192.168.1.62:3030/";

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPreferencesStorageProvider storage = new SharedPreferencesStorageProvider(this);

        AuthenticationOptions options = new AuthenticationOptions();

        options.storage = storage;


        Feathers.getInstance()

                .setBaseUrl(BASE_URL)

//                .configure(new FeathersRest())

                .configure(new FeathersSocketIO())

                .configure(new FeathersAuthenticationConfiguration(options));
    }
}
