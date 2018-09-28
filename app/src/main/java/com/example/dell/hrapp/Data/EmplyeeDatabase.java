package com.example.dell.hrapp.Data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

/**
 * Created by sardar.khan on 9/28/2018.
 */
@Database(entities = {Employee.class}, version = 1)
public abstract class EmplyeeDatabase extends RoomDatabase{

    private static EmplyeeDatabase INSTANCE;

    public abstract EmployeeDao employeeDao();

    public static EmplyeeDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(), EmplyeeDatabase.class, "employee-db")
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}
