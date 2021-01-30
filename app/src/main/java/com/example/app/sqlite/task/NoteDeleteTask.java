package com.example.app.sqlite.task;

import android.os.AsyncTask;

import com.example.app.sqlite.data.DatabaseController;
import com.example.app.sqlite.model.Note;

//TODO deprecated replace this
public class NoteDeleteTask extends AsyncTask<Note, Void, Integer> {

    private final DatabaseController controller;
    private final DataTaskCallback<Integer> callback;

    public NoteDeleteTask(DatabaseController controller, DataTaskCallback<Integer> callback) {
        this.controller = controller;
        this.callback = callback;
    }
    @Override
    protected Integer doInBackground(Note... params) {
        Note todo = params[0];
        if(todo != null){
            return controller.delete(todo);
        }
        return 0;
    }
    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        if (callback != null){
            callback.onSuccess(result);
        }
    }
}
