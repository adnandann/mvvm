package com.example.app.simple.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.R;
import com.example.app.simple.model.Item;
import com.example.app.simple.viewmodel.ItemViewModel;

public class ItemDeleteFragment extends Fragment {

    private ItemViewModel viewModel;
    private Item todo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_delete, container, false);

        view.findViewById(R.id.action_delete).setOnClickListener(v -> deleteItem());

        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity() == null) return;
        ItemViewModel.Factory factory = new ItemViewModel.Factory(requireActivity().getApplication());
        viewModel = new ViewModelProvider(requireActivity(), factory).get(ItemViewModel.class);
        subscribe();
    }

    private void subscribe() {
        viewModel.getItem().observe(getViewLifecycleOwner(), resource -> {
            todo = resource;
        });
    }

    private void deleteItem() {
        if(todo != null) viewModel.deleteItem(todo);
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
