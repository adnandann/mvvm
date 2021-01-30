package com.example.app.adapter.base;

public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);

}
