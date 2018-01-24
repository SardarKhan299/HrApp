package com.example.dell.hrapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dell.hrapp.Data.Employee;
import com.example.dell.hrapp.Data.EmployeeDBHelper;

public class ShowDetail extends AppCompatActivity {


    private static final String TAG = ShowDetail.class.getName() ;
    TextView tvFirstname , tvLastname,tvGender,tvBirthdate,tvAddress,tvHireDate,tvPosition,tvDepartment,tvSalary;

    String empId;
    EmployeeDBHelper dbHelper ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_detail);
        dbHelper = new EmployeeDBHelper(ShowDetail.this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        empId = getIntent().getStringExtra("emp_id");
        Log.d(TAG, "onCreate: Emp Id is " + empId);

        tvFirstname = (TextView) findViewById(R.id.tv_firstname);
        tvLastname = (TextView) findViewById(R.id.tv_lastname);
        tvGender = (TextView) findViewById(R.id.tv_gender);
        tvBirthdate = (TextView) findViewById(R.id.tv_birthdate);
        tvAddress = (TextView) findViewById(R.id.tv_Address);
        tvHireDate = (TextView) findViewById(R.id.tv_HireDate);
        tvPosition = (TextView) findViewById(R.id.tv_Position);
        tvDepartment = (TextView) findViewById(R.id.tv_Department);
        tvSalary = (TextView) findViewById(R.id.tv_Salary);


        Cursor cursor = dbHelper.getEmployeeDetail(empId);

        Log.d(TAG, "doInBackground: Cursor Size is "+cursor.getCount());
        while(cursor.moveToNext()) {

            tvBirthdate.setText(cursor.getString(1));
            tvFirstname.setText(cursor.getString(2));
            tvLastname.setText(cursor.getString(3));
            tvGender.setText(cursor.getString(4));
            tvAddress.setText(cursor.getString(5));
            tvHireDate.setText(cursor.getString(6));

        }

        cursor.close();

        tvSalary.setText(dbHelper.getEmployeeSalary(empId));
        tvPosition.setText(dbHelper.getEmployeePosition(empId));
        tvDepartment.setText(dbHelper.getEmployeeDepartment(empId));


        // get the department of the Employee....



    }

}
