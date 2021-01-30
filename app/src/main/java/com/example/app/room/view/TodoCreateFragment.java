package com.example.app.room.view;

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
import com.example.app.room.model.Todo;
import com.example.app.room.viewmodel.TodoViewModel;

public class TodoCreateFragment extends Fragment {

    private TodoViewModel viewModel;
    private Todo todo;
    private TextView actionView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_create, container, false);

        actionView = view.findViewById(R.id.action_random);
        actionView.setOnClickListener(v -> actionTodo());

        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity() == null) return;
        TodoViewModel.Factory factory = new TodoViewModel.Factory(requireActivity().getApplication());
        viewModel = new ViewModelProvider(requireActivity(), factory).get(TodoViewModel.class);
        subscribe();
    }

    private void subscribe() {
        viewModel.getTodo().observe(getViewLifecycleOwner(), resource -> {
            todo = resource;
            actionView.setText(todo != null ? "Update Random Todo" : "Create Random Todo");
            actionView.setBackgroundResource(todo != null ? R.drawable.round_outline_blue : R.drawable.round_outline_green);
        });
    }

    private void actionTodo() {
        if(todo != null){
            todo.setColor(randomColor());
            viewModel.updateTodo(todo);
        }else{
            viewModel.insertTodo(new Todo("Todo " + randomInt(999), randomColor()));
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
