package com.example.app.sqlite.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.R;
import com.example.app.sqlite.viewmodel.NoteViewModel;

public class NoteActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private NoteViewModel viewModel;

    private NoteListFragment noteListFragment;
    private NoteCreateFragment noteCreateFragment;
    private NoteDeleteFragment noteDeleteFragment;
    private int activeFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        noteListFragment = new NoteListFragment();
        noteCreateFragment = new NoteCreateFragment();
        noteDeleteFragment = new NoteDeleteFragment();

        fragmentManager = getSupportFragmentManager();
        setFragment(noteListFragment);

        NoteViewModel.Factory factory = new NoteViewModel.Factory(getApplication());
        viewModel = new ViewModelProvider(this, factory).get(NoteViewModel.class);
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
                    case 3 : setFragment(noteDeleteFragment); title = "Delete Note"; break;
                    case 2 : setFragment(noteCreateFragment); title = "Update Note"; break;
                    case 1 : setFragment(noteCreateFragment); title = "Create Note"; break;
                    default: setFragment(noteListFragment); title = "Note List";
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
