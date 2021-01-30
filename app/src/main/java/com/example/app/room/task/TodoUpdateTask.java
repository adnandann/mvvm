package com.example.app.room.task;

import android.os.AsyncTask;

import com.example.app.room.dao.TodoDao;
import com.example.app.room.model.Todo;

//TODO deprecated replace this
public class TodoUpdateTask extends AsyncTask<Todo, Void, Void> {

    private final TodoDao todoDao;

    public TodoUpdateTask(TodoDao todoDao) {
        this.todoDao = todoDao;
    }
    @Override
    protected Void doInBackground(Todo... params) {
        Todo todo = params[0];
        if(todo != null){
            todoDao.update(todo);
        }
        return null;
    }
}
