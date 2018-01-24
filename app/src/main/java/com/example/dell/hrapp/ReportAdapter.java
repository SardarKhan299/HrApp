package com.example.dell.hrapp;

/**
 * Created by DELL on 4/19/2017.
 */

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dell.hrapp.Data.Employee;
import com.example.dell.hrapp.Data.EmployeeDBHelper;

import java.util.ArrayList;
import java.util.List;






/**
 * Created by Nsol on 3/21/2017.
 */

public class ReportAdapter extends
        RecyclerView.Adapter<ReportAdapter.ViewHolder> {

    private static final String TAG = ReportAdapter.class.getSimpleName() ;

    EmployeeDBHelper dbHelper ;
    // ... view holder defined above...

    // Store a member variable for the contacts
    public List<Employee> mEmployees;
    // Store the context for easy access
    private Context mContext;
    int activity =0;
    public MediaPlayer mediaPlayer;

    // Pass in the contact array into the constructor
    public ReportAdapter(Context context, int num) {
        mContext = context;
        activity = num;
        mEmployees = new ArrayList<>();
        dbHelper = new EmployeeDBHelper(mContext);
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.single_item_report, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView,mContext);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // Get the data model based on position
        Employee employee = mEmployees.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.department;
        textView.setText(employee.getDepartmentName());

        TextView textView1 = holder.salary;
        textView1.setText(employee.getSalary());

        if(activity == 3)
        {
            Log.d(TAG, "onBindViewHolder: Data is " + employee.getDepartmentName() + "-" + employee.getSalary() +
                    "-" + employee.getBonus() + "-" + employee.getSalaryPlusBonus());


            TextView textView2 = holder.bonus;
            textView2.setText(employee.getBonus());


            TextView textView3 = holder.total;
            textView3.setText(employee.getSalaryPlusBonus());

        }

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mEmployees.size();

    }
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public  class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView department;
        public TextView salary;
        public TextView bonus;
        public TextView total;
        Context context;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView, Context c) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            context = c;

            if(activity == 3)
            {

                bonus = (TextView) itemView.findViewById(R.id.tvBonus);
                total = (TextView) itemView.findViewById(R.id.tvTotal);
               bonus.setVisibility(View.VISIBLE);
                total.setVisibility(View.VISIBLE);
            }
            department = (TextView) itemView.findViewById(R.id.tvDepartment);
            salary = (TextView) itemView.findViewById(R.id.tvSalary);

        }

    }

    public void setEmployeeList(ArrayList<Employee> employeeList)
    {
        Log.d(TAG, "setEmployeeList: Size is "+employeeList.size());
        this.mEmployees.clear();
        this.mEmployees.addAll(employeeList);
        notifyDataSetChanged();
    }



}

