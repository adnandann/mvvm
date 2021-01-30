package com.example.app.simple.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.R;
import com.example.app.simple.viewmodel.ItemViewModel;

public class ItemActivity extends AppCompatActivity {


    private FragmentManager fragmentManager;
    private ItemViewModel viewModel;

    private ItemListFragment itemListFragment;
    private ItemCreateFragment itemCreateFragment;
    private ItemDeleteFragment itemDeleteFragment;
    private int activeFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        itemListFragment = new ItemListFragment();
        itemCreateFragment = new ItemCreateFragment();
        itemDeleteFragment = new ItemDeleteFragment();

        fragmentManager = getSupportFragmentManager();
        setFragment(itemListFragment);

        ItemViewModel.Factory factory = new ItemViewModel.Factory(getApplication());
        viewModel = new ViewModelProvider(this, factory).get(ItemViewModel.class);
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
                    case 3 : setFragment(itemDeleteFragment); title = "Delete Item"; break;
                    case 2 : setFragment(itemCreateFragment); title = "Update Item"; break;
                    case 1 : setFragment(itemCreateFragment); title = "Create Item"; break;
                    default: setFragment(itemListFragment); title = "Item List";
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
