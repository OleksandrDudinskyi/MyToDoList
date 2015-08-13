package com.oleksandr.mytodolist;

import android.database.Cursor;
import android.graphics.Paint;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.oleksandr.mytodolist.model.State;
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
            int urgent = cursor.getInt(cursor.getColumnIndex(ToDoItem.COLUMN_IS_URGENT));
            if (urgent == 1) {
                holder.cardView.setBackgroundResource(R.color.card_view_urgent);
            } else {
                holder.cardView.setBackgroundResource(R.color.card_view_main);
            }
            holder.title.setText(title);
            long columnId = cursor.getLong(cursor.getColumnIndex(ToDoItem.COLUMN_ID));
            holder.doneBtn.setTag(columnId);
            holder.doneBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToDoItem item = ToDoItem.load(ToDoItem.class, (long) v.getTag());
                    item.setState(State.DONE.getStatus());
                    item.setUrgent(-1);
                    item.save();
                    holder.title.setPaintFlags(holder.title.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }
            });
            holder.undoneBtn.setTag(columnId);
            holder.undoneBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToDoItem item = ToDoItem.load(ToDoItem.class, (long) v.getTag());
                    item.setState(State.UNDONE.getStatus());
                    item.save();
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
        private CardView cardView;

        public ViewHolder(final View view) {
            super(view);
            doneBtn = (CheckedTextView) view.findViewById(R.id.done_btn);
            undoneBtn = (ImageView) view.findViewById(R.id.undone_btn);
            title = (TextView) view.findViewById(R.id.title);
            cardView = (CardView) view.findViewById(R.id.card_view);
        }

    }
}
