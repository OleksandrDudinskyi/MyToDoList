package com.oleksandr.mytodolist;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by Oleksandr on 8/12/2015.
 */
public class ToDOApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
