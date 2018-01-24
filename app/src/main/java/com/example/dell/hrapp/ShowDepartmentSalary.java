package com.example.dell.hrapp;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.dell.hrapp.Data.Employee;
import com.example.dell.hrapp.Data.EmployeeDBHelper;

import java.util.ArrayList;
import java.util.Calendar;

public class ShowDepartmentSalary extends AppCompatActivity {

    private static final String TAG = ShowDepartmentSalary.class.getSimpleName() ;
    RecyclerView rvEmployees;
    EmployeeDBHelper dbHelper ;
    ReportAdapter adapter;

    int reportNum =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_department_salary);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        reportNum = getIntent().getIntExtra("report",-1);



        dbHelper = new EmployeeDBHelper(ShowDepartmentSalary.this);


        rvEmployees = (RecyclerView) findViewById(R.id.rv);

        // Create adapter passing in the sample user data
        adapter = new ReportAdapter(ShowDepartmentSalary.this,reportNum);

        // Attach the adapter to the recyclerview to populate items
        rvEmployees.setAdapter(adapter);
        rvEmployees.setHasFixedSize(true);
        // Set layout manager to position the items
        rvEmployees.setLayoutManager(new LinearLayoutManager(ShowDepartmentSalary.this));

        new LoadEmployeesAsync(ShowDepartmentSalary.this).execute();




    }



    public class LoadEmployeesAsync extends AsyncTask<Void, Void, ArrayList<Employee>> {

        ArrayList<Employee> employeeList;
        private ShowDepartmentSalary activity;

        public LoadEmployeesAsync(ShowDepartmentSalary activity) {
            this.activity = activity;
        }

        @Override
        protected ArrayList<Employee> doInBackground(Void... arg0) {
            employeeList = new ArrayList<Employee>();


            if(reportNum == 1) {
                Cursor cursor = dbHelper.getEmployeesByDepartments();

                Log.d(TAG, "getEmployeesByDepartments: " + cursor.getCount());


                Log.d(TAG, "doInBackground: Cursor Size is " + cursor.getCount());
                while (cursor.moveToNext()) {

                    Employee employee = new Employee();
                    employee.setSalary(cursor.getString(0));
                    employee.setDepartmentName(cursor.getString(1));


                    employeeList.add(employee);

                }

                cursor.close();

                Log.d(TAG, "doInBackground: Songs List Size is " + employeeList.size());

                return employeeList;

            }
            else if(reportNum == 2)
            {
                Cursor cursor = dbHelper.getEmployeesByDepartments();

                Log.d(TAG, "getEmployeesByDepartments: " + cursor.getCount());


                Log.d(TAG, "doInBackground: Cursor Size is " + cursor.getCount());
                while (cursor.moveToNext()) {

                    Employee employee = new Employee();
                    int salary = cursor.getInt(0);
                    Log.d(TAG, "doInBackground: Salary is "+salary);
                    int tax = (int)(salary*(19/100.0f));
                    Log.d(TAG, "doInBackground: Tax is " + tax);
                    int sumSalary = salary + tax;
                    employee.setSalary(String.valueOf(sumSalary));
                    employee.setDepartmentName(cursor.getString(1));


                    employeeList.add(employee);

                }

                cursor.close();

                Log.d(TAG, "doInBackground: Songs List Size is " + employeeList.size());

                return employeeList;
            }

            else if(reportNum == 3)
            {





                Cursor cursor = dbHelper.getEmployeesByDepartments();

                Log.d(TAG, "getEmployeesByDepartments: " + cursor.getCount());

                Log.d(TAG, "doInBackground: Cursor Size is " + cursor.getCount());
                while (cursor.moveToNext()) {

                    Employee employee = new Employee();
                    int salary = cursor.getInt(0);
                    Log.d(TAG, "doInBackground: Salary is "+salary);
                    // if bonus is 10 percent

//                    int bonus = (int)(salary*(percentage/100.0f));
//

                    int bonus = cursor.getInt(2);
                    Log.d(TAG, "doInBackground: Bonus is " + bonus);

                    int sumSalary = salary + bonus;

                    employee.setSalary(String.valueOf(salary));
                    employee.setBonus(String.valueOf(bonus));
                    employee.setDepartmentName(cursor.getString(1));
                    employee.setSalaryPlusBonus(String.valueOf(sumSalary));


                    employeeList.add(employee);

                }

                cursor.close();

                Log.d(TAG, "doInBackground: Songs List Size is " + employeeList.size());

                return employeeList;
            }

            else if(reportNum == 4)
            {

                Log.d(TAG, "doInBackground: Expereince grater than 2");
                Cursor cursor = dbHelper.getEmpGreaterExp();

                Log.d(TAG, "doInBackground: Cursor Size is " + cursor.getCount());
                while (cursor.moveToNext()) {

                    Employee employee = new Employee();
                    String date = cursor.getString(0);
                    Log.d(TAG, "doInBackground: HireDate  is " + date);
                    if (date.length() == 4) {

                    } else if (date.length() > 4) {
                        String year = date.substring(date.length() - 4);
                        Log.d(TAG, "doInBackground: Hire Year is " + year);
                        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                        Log.d(TAG, "doInBackground: Current Year is"+currentYear);
                        int y = Integer.valueOf(year);
                        if((currentYear-y)>2) {
                            employee.setSalary("");
                            employee.setDepartmentName(cursor.getString(1));
                            employeeList.add(employee);
                            Log.d(TAG, "doInBackground: Employee Grater than 2 year exp"+cursor.getString(1));
                        }
                    } else {
                        // whatever is appropriate in this case
                    }


                }

                cursor.close();

                Log.d(TAG, "doInBackground: Songs List Size is " + employeeList.size());

                return employeeList;
            }

            else if(reportNum == 5)
            {

                Log.d(TAG, "doInBackground: Expereince less than 2");
                Cursor cursor = dbHelper.getEmpGreaterExp();

                Log.d(TAG, "doInBackground: Cursor Size is " + cursor.getCount());
                while (cursor.moveToNext()) {

                    Employee employee = new Employee();
                    String date = cursor.getString(0);
                    Log.d(TAG, "doInBackground: HireDate  is " + date);
                    if (date.length() == 4) {

                    } else if (date.length() > 4) {
                        String year = date.substring(date.length() - 4);
                        Log.d(TAG, "doInBackground: Hire Year is " + year);
                        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
                        Log.d(TAG, "doInBackground: Current Year is"+currentYear);
                        int y = Integer.valueOf(year);
                        if((currentYear-y)< 2) {
                            employee.setSalary("");
                            employee.setDepartmentName(cursor.getString(1));
                            employeeList.add(employee);
                            Log.d(TAG, "doInBackground: Employee less than 2 year exp"+cursor.getString(1));
                        }
                    } else {
                        // whatever is appropriate in this case
                    }


                }

                cursor.close();

                Log.d(TAG, "doInBackground: Songs List Size is " + employeeList.size());

                return employeeList;
            }




            return employeeList;

        }

        @Override
        protected void onPostExecute(ArrayList<Employee> employeeList) {
            Log.d(TAG, "onPostExecute: "+employeeList.size());
            adapter.setEmployeeList(employeeList);
        }


    }




}
