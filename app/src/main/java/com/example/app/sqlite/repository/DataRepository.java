package com.example.app.sqlite.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.example.app.sqlite.data.DatabaseController;
import com.example.app.sqlite.model.Note;
import com.example.app.sqlite.task.DataTaskCallback;
import com.example.app.sqlite.task.NoteDeleteTask;
import com.example.app.sqlite.task.NoteInsertTask;
import com.example.app.sqlite.task.NoteUpdateTask;

import java.util.List;

public class DataRepository {

    private static DataRepository sInstance;

    private final DatabaseController database;
    private final MediatorLiveData<List<Note>> notesObservable;

    private DataRepository(final DatabaseController database) {
        this.database = database;
        notesObservable = new MediatorLiveData<>();
    }

    public static DataRepository getInstance(final DatabaseController database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

    public LiveData<List<Note>> loadNotes() {
        List<Note> notes = (List<Note>) database.readAll(new Note(), null, null, null);
        notesObservable.postValue(notes);
        return notesObservable;
    }

    public void insertNote(Note item) {
        new NoteInsertTask(database, new DataTaskCallback<Note>() {
            @Override
            public void onSuccess(Note data) {
                loadNotes();
            }
            @Override
            public void onError(String string) {}
        }).execute(item);
    }
    public void updateNote(Note item) {
        new NoteUpdateTask(database, new DataTaskCallback<Note>() {
            @Override
            public void onSuccess(Note data) {
                loadNotes();
            }
            @Override
            public void onError(String string) {}
        }).execute(item);
        loadNotes();
    }
    public void deleteNote(Note item) {
        new NoteDeleteTask(database, new DataTaskCallback<Integer>() {
            @Override
            public void onSuccess(Integer data) {
                loadNotes();
            }
            @Override
            public void onError(String string) {}
        }).execute(item);
        loadNotes();
    }
}
