package com.example.app.sqlite.task;

import android.os.AsyncTask;

import com.example.app.sqlite.data.DatabaseController;
import com.example.app.sqlite.model.Note;

//TODO deprecated replace this
public class NoteUpdateTask extends AsyncTask<Note, Void, Note> {

    private final DatabaseController controller;
    private final DataTaskCallback<Note> callback;

    public NoteUpdateTask(DatabaseController controller, DataTaskCallback<Note> callback) {
        this.controller = controller;
        this.callback = callback;
    }
    @Override
    protected Note doInBackground(Note... params) {
        Note note = params[0];
        if(note != null){
            controller.update(note, null);
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
