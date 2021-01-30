package com.example.app.sqlite.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.sqlite.data.DatabaseController;
import com.example.app.sqlite.model.Note;
import com.example.app.sqlite.repository.DataRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private final DataRepository dataRepository;

    private final LiveData<List<Note>> notes;

    private final MutableLiveData<Note> note;
    private final MutableLiveData<Integer> fragment;

    public NoteViewModel(@NonNull Application application) {
        super(application);

        this.dataRepository = DataRepository.getInstance(new DatabaseController(application));

        this.notes  = dataRepository.loadNotes();

        note = new MutableLiveData<>();
        fragment = new MutableLiveData<>();
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }

    public void insertNote(Note data) {
        dataRepository.insertNote(data);
    }
    public void updateNote(Note data) {
        dataRepository.updateNote(data);
        note.setValue(null);
    }
    public void deleteNote(Note data) {
        dataRepository.deleteNote(data);
        note.setValue(null);
    }

    public MutableLiveData<Integer> getFragment() {
        return fragment;
    }

    public void setFragment(int i){
        fragment.setValue(i);
    }

    public void setNote(Note note) {
        this.note.setValue(note);
    }

    public MutableLiveData<Note> getNote() {
        return note;
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
            return (T) new NoteViewModel(this.application);
        }
    }

}

