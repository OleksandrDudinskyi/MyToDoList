package com.oleksandr.mytodolist;

import android.database.Cursor;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

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
        View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(rootView);
    }

    public void onBindViewHolderCursor(final ViewHolder holder, final Cursor cursor) {
        if (cursor != null && cursor.getCount() > 0) {
            String title = cursor.getString(cursor.getColumnIndex(ToDoItem.COLUMN_TITLE));
            holder.title.setText(title);
            holder.doneBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
            });
            holder.undoneBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToDoItem.load(ToDoItem.class, cursor.getInt(cursor.getColumnIndex(ToDoItem.COLUMN_ID))).delete();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return super.getItemCount();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private CheckedTextView doneBtn;
        private ImageView undoneBtn;

        public ViewHolder(final View view) {
            super(view);
            doneBtn = (CheckedTextView) view.findViewById(R.id.done_btn);
            undoneBtn = (ImageView) view.findViewById(R.id.undone_btn);
            title = (TextView) view.findViewById(R.id.title);
        }

    }
}
