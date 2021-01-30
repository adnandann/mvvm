package com.example.app.app;

import android.app.Application;

import com.example.app.room.data.AppDatabase;
import com.example.app.room.repository.DataRepository;

public class App extends Application {

    public static final String TAG = App.class.getSimpleName();
    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

    }

    public static synchronized App getInstance() {
        return mInstance;
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase());
    }
}
