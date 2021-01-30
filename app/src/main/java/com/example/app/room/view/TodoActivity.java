package com.example.app.room.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.R;
import com.example.app.room.viewmodel.TodoViewModel;

public class TodoActivity extends AppCompatActivity {


    private FragmentManager fragmentManager;
    private TodoViewModel viewModel;

    private TodoListFragment todoListFragment;
    private TodoCreateFragment todoCreateFragment;
    private TodoDeleteFragment todoDeleteFragment;
    private int activeFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        todoListFragment = new TodoListFragment();
        todoCreateFragment = new TodoCreateFragment();
        todoDeleteFragment = new TodoDeleteFragment();

        fragmentManager = getSupportFragmentManager();
        setFragment(todoListFragment);

        TodoViewModel.Factory factory = new TodoViewModel.Factory(getApplication());
        viewModel = new ViewModelProvider(this, factory).get(TodoViewModel.class);
        subscribe();
    }


    private void subscribe() {
        //TODO short way viewModel.getFragment().observe(this, fragmentId -> setFragment(fragmentId == 1 ? todoListFragment : todoListFragment));
        viewModel.getFragment().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer fragmentId) {
                activeFragment = fragmentId;
                String title;
                switch (fragmentId){
                    case 3 : setFragment(todoDeleteFragment); title = "Delete Todo"; break;
                    case 2 : setFragment(todoCreateFragment); title = "Update Todo"; break;
                    case 1 : setFragment(todoCreateFragment); title = "Create Todo"; break;
                    default: setFragment(todoListFragment); title = "Todo List";
                }
                /*if(getActionBar() != null) getActionBar().*/setTitle(title);
                //setFragment(fragmentId == 1 ? todoCreateFragment : todoListFragment);
            }
        });
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        if(activeFragment > 0) {
            viewModel.setFragment(0);
            return;
        }
        super.onBackPressed();
    }
}
