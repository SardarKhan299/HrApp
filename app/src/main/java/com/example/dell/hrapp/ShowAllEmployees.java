package com.example.dell.hrapp;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.dell.hrapp.Data.Employee;
import com.example.dell.hrapp.Data.EmployeeDBHelper;

import java.util.ArrayList;

public class ShowAllEmployees extends AppCompatActivity {


    private static final String TAG = ShowAllEmployees.class.getName() ;
    RecyclerView rvEmployees;
    EmployeeDBHelper dbHelper ;
    EmployeeAdapter adapter;

    int activity = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_employees);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        dbHelper = new EmployeeDBHelper(ShowAllEmployees.this);

        activity = getIntent().getIntExtra("activity",-1);

        rvEmployees = (RecyclerView) findViewById(R.id.rvEmployees);

        // Create adapter passing in the sample user data
       adapter = new EmployeeAdapter(ShowAllEmployees.this,activity);

        // Attach the adapter to the recyclerview to populate items
        rvEmployees.setAdapter(adapter);
        rvEmployees.setHasFixedSize(true);
        // Set layout manager to position the items
        rvEmployees.setLayoutManager(new LinearLayoutManager(ShowAllEmployees.this));



        new LoadEmployeesAsync(ShowAllEmployees.this).execute();

        // report
        dbHelper.getEmployeesByDepartments();

    }



    public class LoadEmployeesAsync extends AsyncTask<Void, Void, ArrayList<Employee>> {

        ArrayList<Employee> employeeList;
        private ShowAllEmployees activity;

        public LoadEmployeesAsync(ShowAllEmployees activity) {
            this.activity = activity;
        }

        @Override
        protected ArrayList<Employee> doInBackground(Void... arg0) {
            employeeList = new ArrayList<Employee>();

            Cursor cursor = dbHelper.getAllEmployees();

            Log.d(TAG, "doInBackground: Cursor Size is "+cursor.getCount());
            while(cursor.moveToNext()) {


                Employee employee = new Employee();
                employee.setEmpNo(cursor.getString(0));
                employee.setBirthdate(cursor.getString(1));
                employee.setFirstname(cursor.getString(2));
                employee.setLastname(cursor.getString(3));
                employee.setGender(cursor.getString(4));
                employee.setAddress(cursor.getString(5));
                employee.setHireDate(cursor.getString(6));



                employeeList.add(employee);

            }

            cursor.close();

            Log.d(TAG, "doInBackground: Songs List Size is " + employeeList.size());

            return employeeList;
        }

        @Override
        protected void onPostExecute(ArrayList<Employee> employeeList) {
            Log.d(TAG, "onPostExecute: "+employeeList.size());
            adapter.setEmployeeList(employeeList);
        }


    }


}
