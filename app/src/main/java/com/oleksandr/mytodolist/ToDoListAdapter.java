package com.oleksandr.mytodolist;

import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oleksandr.mytodolist.databinding.ListItemBinding;
import com.oleksandr.mytodolist.model.ToDoItem;

/**
 * Created by Oleksandr on 8/12/2015.
 */
public class ToDoListAdapter extends CursorRecyclerViewAdapter<ToDoListAdapter.ViewHolder> {
    public ToDoListAdapter(Cursor cursor) {
        super(cursor);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item, viewGroup, false);
        return new ViewHolder(binding.getRoot(), binding);
    }

    public void onBindViewHolderCursor(ViewHolder holder, Cursor cursor) {
        if (cursor != null && cursor.getCount() > 0) {
            String title = cursor.getString(cursor.getColumnIndex(ToDoItem.COLUMN_TITLE));
            ToDoItem.Builder builder = new ToDoItem.Builder();
            holder.bind(builder.title(title).build());
        }

    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
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
