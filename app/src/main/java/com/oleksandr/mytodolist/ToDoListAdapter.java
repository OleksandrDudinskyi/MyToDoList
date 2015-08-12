package com.oleksandr.mytodolist;

import android.databinding.DataBindingUtil;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oleksandr.mytodolist.databinding.ListItemBinding;
import com.oleksandr.mytodolist.model.ToDoItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oleksandr on 8/12/2015.
 */
public class ToDoListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ToDoItem> itemsList = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item, viewGroup, false);
        return new ViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ListItemBinding binding;


        public ViewHolder(final View view, final ListItemBinding binding) {
            super(view);
            this.binding = binding;
        }


        @UiThread
        public void bind(final ToDoItem repo) {
            this.binding.setItem(repo);
        }
    }
}
