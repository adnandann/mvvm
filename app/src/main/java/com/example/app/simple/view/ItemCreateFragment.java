package com.example.app.simple.view;

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
import com.example.app.simple.adapter.ItemAdapter;
import com.example.app.simple.model.Item;
import com.example.app.simple.viewmodel.ItemViewModel;

public class ItemCreateFragment extends Fragment {

    private ItemViewModel viewModel;
    private ItemAdapter adapter;
    private Item todo;
    private TextView actionView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_create, container, false);

        actionView = view.findViewById(R.id.action_random);
        actionView.setOnClickListener(v -> actionItem());

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
            actionView.setText(todo != null ? "Update Random Item" : "Create Random Item");
            actionView.setBackgroundResource(todo != null ? R.drawable.round_outline_blue : R.drawable.round_outline_green);
        });
    }

    private void actionItem() {
        if(todo != null){
            todo.setColor(randomColor());
            viewModel.updateItem(todo);
        }else{
            viewModel.insertItem(new Item("Item " + randomInt(999), randomColor()));
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
