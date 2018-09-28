package com.example.dell.hrapp;

import com.example.dell.hrapp.Data.Employee;
import com.example.dell.hrapp.Data.EmplyeeDatabase;

import java.util.List;

/**
 * Created by sardar.khan on 9/28/2018.
 */

class DataRepository {

    private static DataRepository sInstance;

    private final EmplyeeDatabase mDatabase;

    private DataRepository( EmplyeeDatabase database) {
        mDatabase = database;
    }


    public static DataRepository getInstance( EmplyeeDatabase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }


    public List<Employee> getAllEmployee() {
        return mDatabase.employeeDao().getAllEmployees();
    }

    public Employee getEmployeeDetail(final int employeeId) {
        return mDatabase.employeeDao().getEmployeeDetail(employeeId);
    }

    public int deleteEmployee(int empId)
    {
        return mDatabase.employeeDao().deleteEmployee(empId);
    }

    public void inserEmployee(Employee employee)
    {
        mDatabase.employeeDao().insertEmployee(employee);
    }

    public void updateEmployee(Employee employee)
    {
        mDatabase.employeeDao().updateEmployee(employee);
    }


}
