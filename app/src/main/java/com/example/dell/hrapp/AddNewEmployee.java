package com.example.dell.hrapp;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.Touch;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dell.hrapp.Data.EmployeeDBHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddNewEmployee extends AppCompatActivity {


    private static final String TAG = AddNewEmployee.class.getSimpleName();
    EditText edtFirstname , edtLastname , edtGender,edtBirthdate,edtAddress,edtHiredate,edtPosition,
            edtDepartment,edtSalary,edtBonus;

    Button insert;
    EmployeeDBHelper dbHelper ;
     Calendar myCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_employee);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        dbHelper = new EmployeeDBHelper(AddNewEmployee.this);
        edtFirstname = (EditText) findViewById(R.id.edt_firstname);
        edtLastname = (EditText) findViewById(R.id.edt_lastname);
        edtGender = (EditText) findViewById(R.id.edt_gender);
        edtBirthdate = (EditText) findViewById(R.id.edt_Birthdate);
        edtAddress = (EditText) findViewById(R.id.edt_Address);
        edtHiredate = (EditText) findViewById(R.id.edt_Hiredate);
        edtPosition = (EditText) findViewById(R.id.edt_Position);
        edtDepartment = (EditText) findViewById(R.id.edt_Department);
        edtSalary = (EditText) findViewById(R.id.edt_Salary);
        edtBonus = (EditText) findViewById(R.id.edt_Bonus);
        insert = (Button) findViewById(R.id.btn_registerart);

        myCalendar = Calendar.getInstance();

        edtHiredate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    new DatePickerDialog(AddNewEmployee.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            myCalendar.set(Calendar.YEAR, year);
                            myCalendar.set(Calendar.MONTH, month);
                            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            updateLabel();
                        }
                    }, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                return true;
            }
        });


        edtBirthdate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    new DatePickerDialog(AddNewEmployee.this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            myCalendar.set(Calendar.YEAR, year);
                            myCalendar.set(Calendar.MONTH, month);
                            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                            updateLabelB();
                        }
                    }, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                return true;
            }
        });


        edtGender.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showGenderDialog();
                }
                return true;
            }
        });


        edtBonus.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    showBonusDialog();
                }
                return true;
            }
        });



        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: Insert");
                insertEmployee();
            }
        });


    }



    public void showBonusDialog()
    {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(AddNewEmployee.this);
        builderSingle.setIcon(R.mipmap.ic_launcher);
        builderSingle.setTitle("Select Bonus Percentage:-");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddNewEmployee.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("10");
        arrayAdapter.add("20");
        arrayAdapter.add("30");

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String strPercentage = arrayAdapter.getItem(which);
                dialog.dismiss();
                edtBonus.setText(strPercentage);
            }
        });
        builderSingle.show();
    }

    public void showGenderDialog()
    {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(AddNewEmployee.this);
        builderSingle.setIcon(R.mipmap.ic_launcher);
        builderSingle.setTitle("Select Gender :-");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(AddNewEmployee.this, android.R.layout.select_dialog_singlechoice);
        arrayAdapter.add("male");
        arrayAdapter.add("female");

        builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String gender = arrayAdapter.getItem(which);
                Log.d(TAG, "onClick: Gender is " + gender);
                edtGender.setText(gender);
                dialog.dismiss();
            }
        });
        builderSingle.show();
    }

    private void updateLabelB() {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        edtBirthdate.setText(sdf.format(myCalendar.getTime()));
    }

    private void updateLabel() {

        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        edtHiredate.setText(sdf.format(myCalendar.getTime()));
    }

    private void insertEmployee() {

        String firstname = edtFirstname.getText().toString().trim();
        String lastname = edtLastname.getText().toString().trim();
        String gender = edtGender.getText().toString().trim();
        String dateOfBirth = edtBirthdate.getText().toString().trim();
        String address = edtAddress.getText().toString().trim();
        String hireDate = edtHiredate.getText().toString().trim();
        String position = edtPosition.getText().toString().trim();
        String department = edtDepartment.getText().toString().trim();
        String salary = edtSalary.getText().toString().trim();
        String bonus = edtBonus.getText().toString().trim();
        if (firstname.length()==0||lastname.length()==0||gender.length()==0||address.length()==0||hireDate.length()==0||
                position.length()==0||department.length()==0||salary.length()==0 || bonus.length()==0)
        {
            Toast.makeText(AddNewEmployee.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
        else {

            // calculate bonus on basis of percentage
            int percentage = Integer.valueOf(bonus);
            int s = Integer.valueOf(salary);
            int b = (int)(s*(percentage/100.0f));

            Log.d(TAG, "insertEmployee: Bonus is "+b);

            long emp_id = dbHelper.insertEmployee(firstname, lastname, gender, dateOfBirth, address, hireDate,b);
            dbHelper.insertSalary(emp_id, salary, hireDate);
            dbHelper.insertTitle(emp_id, position, hireDate);
            long dep_id = dbHelper.insertDepartment(department);
            dbHelper.insertDeptEmp(emp_id, dep_id, hireDate);


            finish();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
