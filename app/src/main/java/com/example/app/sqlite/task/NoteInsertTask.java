package com.example.app.sqlite.task;

import android.os.AsyncTask;

import com.example.app.sqlite.data.DatabaseController;
import com.example.app.sqlite.model.Note;

//TODO deprecated replace this
public class NoteInsertTask extends AsyncTask<Note, Void, Note> {

    private final DatabaseController controller;
    private final DataTaskCallback<Note> callback;

    public NoteInsertTask(DatabaseController controller, DataTaskCallback<Note> callback) {
        this.controller = controller;
        this.callback = callback;
    }
    @Override
    protected Note doInBackground(Note... params) {
        Note note = params[0];
        if(note != null){
            controller.insert(note);
        }
        return note;
    }

    @Override
    protected void onPostExecute(Note note) {
        super.onPostExecute(note);
        if (callback != null){
            callback.onSuccess(note);
        }
    }
}
