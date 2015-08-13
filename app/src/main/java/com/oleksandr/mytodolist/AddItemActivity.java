package com.oleksandr.mytodolist;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.activeandroid.ActiveAndroid;
import com.oleksandr.mytodolist.model.State;
import com.oleksandr.mytodolist.model.ToDoItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Oleksandr on 8/12/2015.
 */
public class AddItemActivity extends AppCompatActivity {
    private TextView titleView;
    private TextView descriptionView;
    private CheckBox urgentView;
    private TextView dateView;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private int dueYear;
    private int dueMonth;
    private int dueDay;
    private int dueHours;
    private int dueMinutes;
    private Calendar dueDate;

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            dueYear = year;
            dueMonth = monthOfYear;
            dueDay = dayOfMonth;
            Calendar calendar = Calendar.getInstance();
            timePickerDialog = new TimePickerDialog(AddItemActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
            timePickerDialog.show();
        }
    };

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dueHours = hourOfDay;
            dueMinutes = minute;
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy HH:mm", Locale.getDefault());
            dueDate = Calendar.getInstance();
            dueDate.set(dueYear, dueMonth, dueDay, dueHours, dueMinutes);
            dateView.setText(sdf.format(dueDate.getTime()));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_item_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        titleView = (EditText) findViewById(R.id.title);
        descriptionView = (EditText) findViewById(R.id.description);
        urgentView = (CheckBox) findViewById(R.id.urgent);
        dateView = (TextView) findViewById(R.id.due_date);
        Button saveBtn = (Button) findViewById(R.id.save_btn);
        View.OnClickListener dateSelectListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                datePickerDialog = new DatePickerDialog(AddItemActivity.this, dateSetListener, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
                datePickerDialog.show();
            }
        };
        dateView.setOnClickListener(dateSelectListener);
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
        if (isTitleValid()) {
            ActiveAndroid.beginTransaction();
            try {
                ToDoItem.Builder toDoItemBuilder = new ToDoItem.Builder();
                toDoItemBuilder.title(titleView.getText().toString()).
                        description(descriptionView.getText().toString()).
                        urgent(urgentView.isChecked()).dueDate(dateView.getText().toString()).
                        state(State.UNDONE.getStatus()).build().save();
                Intent intent = new Intent(this, OverDueItemReceiver.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
                AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC_WAKEUP, dueDate.getTimeInMillis(), pendingIntent);
                ActiveAndroid.setTransactionSuccessful();
            } finally {
                ActiveAndroid.endTransaction();
            }
            setResult(RESULT_OK);
            finish();
        }
    }

    private boolean isTitleValid() {
        if (TextUtils.isEmpty(titleView.getText())) {
            new AlertDialog.Builder(
                    this, AlertDialog.THEME_DEVICE_DEFAULT_LIGHT).
                    setTitle(R.string.validation_dialog_title).
                    setMessage(R.string.validation_dialog_text).
                    setNegativeButton(android.R.string.ok, null).
                    setCancelable(true).create().show();
            return false;
        }
        return true;
    }
}
