package com.example.app.sqlite.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.R;
import com.example.app.sqlite.model.Note;
import com.example.app.sqlite.viewmodel.NoteViewModel;

public class NoteCreateFragment extends Fragment {

    private NoteViewModel viewModel;
    private Note note;
    private TextView actionView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_create, container, false);

        actionView = view.findViewById(R.id.action_random);
        actionView.setOnClickListener(v -> actionNote());

        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity() == null) return;
        NoteViewModel.Factory factory = new NoteViewModel.Factory(requireActivity().getApplication());
        viewModel = new ViewModelProvider(requireActivity(), factory).get(NoteViewModel.class);
        subscribe();
    }

    private void subscribe() {
        viewModel.getNote().observe(getViewLifecycleOwner(), resource -> {
            note = resource;
            actionView.setText(note != null ? "Update Random Note" : "Create Random Note");
            actionView.setBackgroundResource(note != null ? R.drawable.round_outline_blue : R.drawable.round_outline_green);
        });
    }

    private void actionNote() {
        if(note != null){
            note.setColor(randomColor());
            viewModel.updateNote(note);
        }else{
            viewModel.insertNote(new Note("Note " + randomInt(999), randomColor()));
        }
        viewModel.setFragment(0);
    }

    private String randomColor(){
        int rgb = Color.rgb(randomInt(255), randomInt(255), randomInt(255));
        return "#" + Integer.toHexString(rgb);
    }

    private int randomInt(int max) {
        return (int) Math.floor(Math.random() * max);
    }



}
