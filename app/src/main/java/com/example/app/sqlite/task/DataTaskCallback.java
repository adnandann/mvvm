package com.example.app.sqlite.task;

public interface DataTaskCallback<T> {
    void onSuccess(T data);
    void onError(String string);
}
