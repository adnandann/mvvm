package com.example.app.simple.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.app.simple.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemViewModel extends AndroidViewModel {

    private final List<Item> itemList;

    private final MutableLiveData<List<Item>> items;
    private final MutableLiveData<Item> item;
    private final MutableLiveData<Integer> fragment;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        itemList = new ArrayList<>();
        items = new MutableLiveData<>();
        item = new MutableLiveData<>();
        fragment = new MutableLiveData<>();
    }

    public MutableLiveData<List<Item>> getItems() {
        return items;
    }

    public void insertItem(Item data) {
        itemList.add(data);
        items.setValue(itemList);
    }
    public void updateItem(Item data) {
        item.setValue(null);
        items.setValue(itemList);
    }
    public void deleteItem(Item data) {
        itemList.remove(data);
        items.setValue(itemList);
        item.setValue(null);
    }

    public MutableLiveData<Integer> getFragment() {
        return fragment;
    }

    public void setFragment(int i){
        fragment.setValue(i);
    }

    public void setItem(Item Item) {
        this.item.setValue(Item);
    }

    public MutableLiveData<Item> getItem() {
        return item;
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
            return (T) new ItemViewModel(this.application);
        }
    }

}

