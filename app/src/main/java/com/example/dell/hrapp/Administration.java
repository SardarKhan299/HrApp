package com.example.dell.hrapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Administration extends AppCompatActivity {


    Button addEmployee,editEmployee,deleteEmployee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addEmployee = (Button) findViewById(R.id.btn_add_emp);
        editEmployee = (Button) findViewById(R.id.btn_edit_emp);
        deleteEmployee = (Button) findViewById(R.id.btn_delete_emp);



        addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Administration.this,AddNewEmployee.class));
            }
        });

        editEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Administration.this,ShowAllEmployees.class);
                i.putExtra("activity",1);
                startActivity(i);
            }
        });

        deleteEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Administration.this,ShowAllEmployees.class);
                i.putExtra("activity",2);
                startActivity(i);
            }
        });



    }

}
