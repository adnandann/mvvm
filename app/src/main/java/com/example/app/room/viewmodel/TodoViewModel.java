package com.example.app.room.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.app.App;
import com.example.app.room.model.Todo;
import com.example.app.room.repository.DataRepository;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {

    //private final ApiRepository apiRepository;
    private final DataRepository dataRepository;

    private final LiveData<List<Todo>> todos;

    private final MutableLiveData<Todo> todo;
    private final MutableLiveData<Integer> fragment;

    public TodoViewModel(@NonNull Application application) {
        super(application);
        //this.apiRepository = ApiRepository.getInstance(application);
        this.dataRepository = ((App) application).getRepository();

        this.todos  = dataRepository.loadTodos();

        todo = new MutableLiveData<>();
        fragment = new MutableLiveData<>();
    }

    public LiveData<List<Todo>> getTodos() {
        return todos;
    }

    public void insertTodo(Todo data) {
        dataRepository.insertTodo(data);
    }
    public void updateTodo(Todo data) {
        dataRepository.updateTodo(data);
        todo.setValue(null);
    }
    public void deleteTodo(Todo data) {
        dataRepository.deleteTodo(data);
        todo.setValue(null);
    }

    public MutableLiveData<Integer> getFragment() {
        return fragment;
    }

    public void setFragment(int i){
        fragment.setValue(i);
    }

    public void setTodo(Todo todo) {
        this.todo.setValue(todo);
    }

    public MutableLiveData<Todo> getTodo() {
        return todo;
    }


    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Application application;

        public Factory(@NonNull Application application) {
            this.application = application;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            //noinspection unchecked
            return (T) new TodoViewModel(this.application);
        }
    }

}

