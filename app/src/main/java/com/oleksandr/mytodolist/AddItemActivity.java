package com.oleksandr.mytodolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;
import com.oleksandr.mytodolist.model.ToDoItem;

/**
 * Created by Oleksandr on 8/12/2015.
 */
public class AddItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button saveBtn = (Button) findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDate();
            }
        });
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void saveDate() {
        ActiveAndroid.beginTransaction();
        try {
            ToDoItem.Builder toDoItemBuilder = new ToDoItem.Builder();
            toDoItemBuilder.title(((TextView) findViewById(R.id.title)).getText().toString()).
                    description(((TextView) findViewById(R.id.description)).getText().toString()).
                    urgent(((CheckBox) findViewById(R.id.urgent)).isChecked()).build().save();
            ActiveAndroid.setTransactionSuccessful();
        } finally {
            ActiveAndroid.endTransaction();
        }
    }
}
