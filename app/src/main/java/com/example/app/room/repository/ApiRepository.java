package com.example.app.room.repository;

import android.app.Application;

import androidx.lifecycle.MediatorLiveData;

import com.example.app.room.data.ApiService;
import com.example.app.room.model.Todo;

import java.util.List;

public class ApiRepository {

    private static ApiRepository sInstance;
    private final ApiService service;

    private ApiRepository(final Application application) {
        this.service = new ApiService();
    }

    //TODO load all data from network if you need
    public static ApiRepository getInstance(final Application application) {
        if (sInstance == null) {
            synchronized (ApiRepository.class) {
                if (sInstance == null) {
                    sInstance = new ApiRepository(application);
                }
            }
        }
        return sInstance;
    }

    public MediatorLiveData<List<Todo>> loadTodos(String endpoint, String data) {
        MediatorLiveData<List<Todo>> observable = new MediatorLiveData<>();
        /*service.readAll(endpoint, data, response -> {
            if(response != null){
                //observable.postValue(response);
            }
        }, error -> {
            //errorMessage(error);
            //observable.postError(error.getMessage());
        });*/
        return observable;
    }

}
