package com.example.dell.hrapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

public class Report extends AppCompatActivity {


    private static final String TAG =Report.class.getName() ;
    Button money_dept;
    Button money_tax_dept;
    Button money_bonus_dept;
    Button exp_grater_2;
    Button exp_less_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        money_dept = (Button) findViewById(R.id.btn_money_dept);
        money_tax_dept = (Button) findViewById(R.id.btn_money_tax_dept);
        money_bonus_dept = (Button) findViewById(R.id.btn_money_bonus_dept);
        exp_less_2 = (Button) findViewById(R.id.btn_employee_exp_less);
        exp_grater_2 = (Button) findViewById(R.id.btn_employee_exp);


        money_dept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: ");
                Intent i = new Intent(Report.this,ShowDepartmentSalary.class);
                i.putExtra("report",1);
                startActivity(i);
            }
        });

        money_tax_dept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Report.this,ShowDepartmentSalary.class);
                i.putExtra("report",2);
                startActivity(i);
            }
        });

        money_bonus_dept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Report.this,ShowDepartmentSalary.class);
                i.putExtra("report",3);
                startActivity(i);
            }
        });

        exp_grater_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Report.this,ShowDepartmentSalary.class);
                i.putExtra("report",4);
                startActivity(i);
            }
        });

        exp_less_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Report.this,ShowDepartmentSalary.class);
                i.putExtra("report",5);
                startActivity(i);
            }
        });









    }


    public void showBonusDialog()
    {
        AlertDialog.Builder builderSingle = new AlertDialog.Builder(Report.this);
        builderSingle.setIcon(R.mipmap.ic_launcher);
        builderSingle.setTitle("Select Bonus Percentage:-");

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Report.this, android.R.layout.select_dialog_singlechoice);
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
               int percent = Integer.parseInt(strPercentage);
                Log.d(TAG, "onClick: Percentage"+percent);
                dialog.dismiss();
                Intent i = new Intent(Report.this,ShowDepartmentSalary.class);
                i.putExtra("report",3);
                i.putExtra("percentage",percent);
                startActivity(i);
            }
        });
        builderSingle.show();
    }

}
