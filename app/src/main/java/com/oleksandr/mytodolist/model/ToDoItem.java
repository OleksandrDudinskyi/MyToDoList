package com.oleksandr.mytodolist.model;

import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by Oleksandr on 8/12/2015.
 */
public class ToDoItem {
    private String title;
    private String description;
    private boolean isUrgent;
    private Date dueDate;
    private State state;

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

    public boolean isUrgent() {
        return isUrgent;
    }

    public void setUrgent(boolean isUrgent) {
        this.isUrgent = isUrgent;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
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

        public Builder urgent(boolean value) {
            toBuild.isUrgent = value;
            return this;
        }

        public Builder dueDate(Date value) {
            toBuild.dueDate = value;
            return this;
        }
    }
}
