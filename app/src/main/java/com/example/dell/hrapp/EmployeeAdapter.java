package com.example.dell.hrapp;

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

public class EmployeeAdapter extends
        RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private static final String TAG = EmployeeAdapter.class.getSimpleName() ;

    EmployeeDBHelper dbHelper ;
    // ... view holder defined above...

    // Store a member variable for the contacts
    public List<Employee> mEmployees;
    // Store the context for easy access
    private Context mContext;
    int activity =0;
    public MediaPlayer mediaPlayer;

    // Pass in the contact array into the constructor
    public EmployeeAdapter(Context context, int num) {
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
        View contactView = inflater.inflate(R.layout.single_item_employee, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView,mContext);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // Get the data model based on position
        Employee employee = mEmployees.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(employee.getFirstname());
        if(activity == 1)
            holder.showDetails.setText("Edit");
        else if(activity == 2)
            holder.showDetails.setText("Delete");

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mEmployees.size();

    }
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public Button showDetails;
        Context context;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView, Context c) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            context = c;

            nameTextView = (TextView) itemView.findViewById(R.id.tvEmployeeName);
            showDetails = (Button) itemView.findViewById(R.id.btnShowDetails);
            showDetails.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            switch (view.getId())
            {
                case R.id.btnShowDetails:
                    Log.d(TAG, "onClick: Show Details"+mEmployees.get(getAdapterPosition()).getEmpNo());
                    if(TextUtils.equals(showDetails.getText().toString(),"Edit"))
                    {
                        Log.d(TAG, "onClick: Edit Employee");
                        Intent i = new Intent(getContext(),EditEmployee.class);
                        i.putExtra("emp_id", mEmployees.get(getAdapterPosition()).getEmpNo());
                        getContext().startActivity(i);
                        break;
                    }
                    if(TextUtils.equals(showDetails.getText().toString(),"Delete"))
                    {
                        Log.d(TAG, "onClick: Delete Employee"+ mEmployees.get(getAdapterPosition()).getEmpNo());
                        dbHelper.deleteEmployee( mEmployees.get(getAdapterPosition()).getEmpNo());
                        mEmployees.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                        break;
                    }

                    Log.d(TAG, "onClick: Show Detail");
                        Intent i = new Intent(getContext(), ShowDetail.class);
                        i.putExtra("emp_id", mEmployees.get(getAdapterPosition()).getEmpNo());
                        getContext().startActivity(i);
                        break;

            }
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
