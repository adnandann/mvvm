package com.example.app.room.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.R;
import com.example.app.adapter.base.ModelAdapter;
import com.example.app.adapter.base.SimpleItemTouchHelperCallback;
import com.example.app.room.adapter.TodoAdapter;
import com.example.app.room.model.Todo;
import com.example.app.room.viewmodel.TodoViewModel;

import java.util.ArrayList;

public class TodoListFragment extends Fragment {

    private TodoViewModel viewModel;
    private TodoAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);

        RecyclerView contentList = view.findViewById(R.id.content_list);
        contentList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new TodoAdapter(getContext(), new ArrayList<>());
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(contentList);
        adapter.setOnStartDragListener(itemTouchHelper::startDrag);
        contentList.setAdapter(adapter);
        adapter.setOnItemClickListener(new ModelAdapter.OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Todo item = (Todo) adapter.getItem(position);
                if(view.getId() == R.id.item_text){
                    viewModel.setTodo(item);
                    viewModel.setFragment(2);
                }else if(view.getId() == R.id.item_delete){
                    viewModel.setTodo(item);
                    viewModel.setFragment(3);
                }else{
                    Toast.makeText(getContext(), "Unknown action", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        });

        view.findViewById(R.id.action_create).setOnClickListener(v -> {
            if(viewModel != null){
                viewModel.setFragment(1);
            }
        });

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
        viewModel.getTodos().observe(getViewLifecycleOwner(), resource -> {
            if(resource != null){
                if(adapter != null) adapter.swapData(resource);
            }else{
                //TODO show error
            }
        });
    }
}
