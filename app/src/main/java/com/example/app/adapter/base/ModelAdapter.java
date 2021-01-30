package com.example.app.adapter.base;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amme on 20.2.2018.
 */

abstract public class ModelAdapter<V> extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements ItemTouchHelperAdapter{

    public static final int MODE_NORMAL = 1;
    public static final int MODE_SELECT = 2;

    protected Context context;
    protected List<?> items;
    protected boolean span;
    protected int mode;
    protected int lastPosition = -1;
    protected int delayAnimate = 10;
    protected List<Integer> selected;

    protected OnItemClickListener onItemClickListener;
    protected OnStartDragListener onStartDragListener;

    public ModelAdapter(Context context, List<?> items) {
        this.context = context;
        this.items = items;
        this.mode = MODE_NORMAL;
    }


    @Override
    public int getItemCount() {

        if (this.items != null) {
            return this.items.size();
        }
        return 0;
    }

    public Object getItem(int position) {
        if(this.items == null || position < 0 || position >= this.items.size()) return null;
        return this.items.get(position);
    }


    public void swapData(List<?> newItems) {
        if (newItems != null) {
            this.items = newItems;
            notifyDataSetChanged();
        }
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }

    public boolean isSpan() {
        return span;
    }

    public void setSpan(boolean span) {
        this.span = span;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public boolean isModeSelect() {
        return this.mode == MODE_SELECT;
    }

    public void setOnStartDragListener(OnStartDragListener onStartDragListener) {
        this.onStartDragListener = onStartDragListener;
    }


    public void setSelected(List<Integer> selected) {
        this.selected = selected;
    }

    public List<Integer> getSelected() {
        return selected;
    }

    public void clearSelected() {
        if(this.selected == null) return;
        this.selected.clear();
    }
    public boolean containsSelected(int s) {
        if(this.selected == null) return false;
        return this.selected.contains(s);
    }
    public void addSelected(int s) {
        if(this.selected == null) {
            selected = new ArrayList<>();
        }
        this.selected.add(s);
    }

    protected boolean isSelected(int ident) {
        return selected != null && selected.contains(ident);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        return false;
    }

    @Override
    public void onItemDismiss(int position) {

    }


    public interface OnItemClickListener{
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
