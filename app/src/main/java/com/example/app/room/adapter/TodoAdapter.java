package com.example.app.room.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app.R;
import com.example.app.adapter.base.ModelAdapter;
import com.example.app.room.model.Todo;

import java.util.Collections;
import java.util.List;

public class TodoAdapter extends ModelAdapter<TodoAdapter.TodoViewHolder> {

    public TodoAdapter(Context context, List<Todo> items) {
        super(context, items);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item, parent, false);
        return new TodoViewHolder(itemView);
    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Todo todo = (Todo) items.get(position);
        TodoViewHolder mHolder = (TodoViewHolder) holder;
        mHolder.text.setText(todo.getText() + " = " + todo.getColor());

        if(todo.getColor() != null){
            try {
                mHolder.color.setColorFilter(Color.parseColor(todo.getColor()));
            }catch (Exception e){
                e.printStackTrace();
                mHolder.color.setColorFilter(0);
            }
        }else{
            mHolder.color.setColorFilter(0);
        }

        if(onItemClickListener != null) {
            mHolder.color.setOnClickListener(v -> onItemClickListener.onClick(v, position));
            mHolder.text.setOnClickListener(v -> onItemClickListener.onClick(v, position));
            mHolder.delete.setOnClickListener(v -> onItemClickListener.onClick(v, position));
        }
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(items, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        //items.remove(position);
        //notifyItemRemoved(position);
    }

    static class TodoViewHolder extends RecyclerView.ViewHolder {

        public ImageView color;
        public TextView text;
        public ImageView delete;

        public TodoViewHolder(View itemView) {
            super(itemView);
            this.color = itemView.findViewById(R.id.item_color);
            this.text = itemView.findViewById(R.id.item_text);
            this.delete = itemView.findViewById(R.id.item_delete);
        }
    }


}
