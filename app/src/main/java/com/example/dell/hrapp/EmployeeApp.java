package com.example.dell.hrapp;

import android.app.Application;
import android.util.Log;

import com.example.dell.hrapp.Data.EmplyeeDatabase;

/**
 * Created by sardar.khan on 9/28/2018.
 */

public class EmployeeApp extends Application {

    private static final String TAG =EmployeeApp.class.getSimpleName() ;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    public EmplyeeDatabase getDatabase() {
        return EmplyeeDatabase.getAppDatabase(this);
    }

    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase());
    }

}
