package com.oleksandr.mytodolist.model;

import android.provider.BaseColumns;
import android.support.annotation.NonNull;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Oleksandr on 8/12/2015.
 */
@Table(name = "ToDoItem", id = BaseColumns._ID)
public class ToDoItem extends Model {

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IS_URGENT = "is_urgent";
    public static final String COLUMN_DUE_DATE = "due_date";
    public static final String COLUMN_STATE = "state";

    @Column(name = COLUMN_TITLE)
    private String title;
    @Column(name = COLUMN_DESCRIPTION)
    private String description;
    @Column(name = COLUMN_IS_URGENT)
    private int isUrgent;
    @Column(name = COLUMN_DUE_DATE)
    private String dueDate;
    @Column(name = COLUMN_STATE)
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String mTitle) {
        this.title = mTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int isUrgent() {
        return isUrgent;
    }

    public void setUrgent(int isUrgent) {
        this.isUrgent = isUrgent;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public static class Builder {
        private ToDoItem toBuild = new ToDoItem();

        public Builder() {
        }

        public ToDoItem build() {
            return toBuild;
        }

        public Builder title(@NonNull String value) {
            toBuild.title = value;
            return this;
        }

        public Builder description(String value) {
            toBuild.description = value;
            return this;
        }

        public Builder urgent(int value) {
            toBuild.isUrgent = value;
            return this;
        }

        public Builder dueDate(String value) {
            toBuild.dueDate = value;
            return this;
        }

        public Builder state(String value) {
            toBuild.state = value;
            return this;
        }
    }
}
