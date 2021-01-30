package com.example.app.room.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.app.room.data.AppDatabase;
import com.example.app.room.model.Todo;
import com.example.app.room.task.TodoDeleteTask;
import com.example.app.room.task.TodoInsertTask;

import java.util.List;

public class DataRepository {

    private static DataRepository sInstance;

    private final AppDatabase database;

    private DataRepository(final AppDatabase database) {
        this.database = database;
    }

    public static DataRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<Todo>> loadTodos() {
        MediatorLiveData<List<Todo>> observable = new MediatorLiveData<>();
        observable.addSource(this.database.getTodoDao().readDataAll(), new Observer<List<Todo>>() {
            @Override
            public void onChanged(List<Todo> todos) {
                observable.postValue(todos);
            }
        });
        return observable;
    }

    public void insertTodo(Todo item) { new TodoInsertTask(database.getTodoDao()).execute(item); }
    public void updateTodo(Todo item) { new TodoInsertTask(database.getTodoDao()).execute(item); }
    public void deleteTodo(Todo item) {
        new TodoDeleteTask(database.getTodoDao()).execute(item);
    }
}
