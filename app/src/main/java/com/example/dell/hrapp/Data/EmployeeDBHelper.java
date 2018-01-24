package com.example.dell.hrapp.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by DELL on 4/13/2017.
 */
public class EmployeeDBHelper extends SQLiteOpenHelper {

    public static final String TAG = EmployeeDBHelper.class.getSimpleName();

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 5;
    public static final String DATABASE_NAME = "employee.db";


    private static final String SQL_CREATE_EMPLOYEE_ENTRY =
            "CREATE TABLE " + EmployeesContract.TABLE_NAME_EMPLOYEES + " (" +
                    EmployeesContract.COLUMN_EMPLOYEE_NO + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    EmployeesContract.COLUMN_BIRTH_DATE+ " TEXT NOT NULL, " +
                    EmployeesContract.COLUMN_FIRSTNAME + " TEXT , "  +
                    EmployeesContract.COLUMN_LASTNAME + " TEXT , " +
                    EmployeesContract.COLUMN_GENDER + " TEXT , " +
                    EmployeesContract.COLUMN_ADDRESS + " TEXT , " +
                    EmployeesContract.COLUMN_HIRE_DATE + " TEXT , " +
                    EmployeesContract.COLUMN_BONUS + " TEXT , " +
                    EmployeesContract.COLUMN_IS_DELETED + " TEXT )";

    private static final String SQL_CREATE_SALARY_ENTRY =
            "CREATE TABLE " + EmployeesContract.TABLE_NAME_SALARIES + " (" +
                    EmployeesContract.Salary_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    EmployeesContract.COLUMN_EMPLOYEE_NO + " INTEGER NOT NULL, " +
                    EmployeesContract.COLUMN_SALARY + " TEXT , "  +
                    EmployeesContract.COLUMN_FROM_DATE + " TEXT , " +
                    EmployeesContract.COLUMN_TO_DATE + " TEXT ," +
                    " FOREIGN KEY ("+EmployeesContract.COLUMN_EMPLOYEE_NO+") REFERENCES "+EmployeesContract.TABLE_NAME_EMPLOYEES+"("+EmployeesContract.COLUMN_EMPLOYEE_NO+"))";

    private static final String SQL_CREATE_TITLE_ENTRY =
            "CREATE TABLE " + EmployeesContract.TABLE_NAME_TITLES + " (" +
                    EmployeesContract.Title_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    EmployeesContract.COLUMN_EMPLOYEE_NO + " INTEGER NOT NULL, " +
                    EmployeesContract.COLUMN_TITLE + " TEXT , "  +
                    EmployeesContract.COLUMN_FROM_DATE + " TEXT , " +
                    EmployeesContract.COLUMN_TO_DATE + " TEXT ," +
                    " FOREIGN KEY ("+EmployeesContract.COLUMN_EMPLOYEE_NO+") REFERENCES "+EmployeesContract.TABLE_NAME_EMPLOYEES+"("+EmployeesContract.COLUMN_EMPLOYEE_NO+"))";


    private static final String SQL_CREATE_DEPARTMENT_ENTRY =
            "CREATE TABLE " + EmployeesContract.TABLE_NAME_DEPARTMENTS + " (" +
                    EmployeesContract.Department_NO + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    EmployeesContract.Department_NAME + " TEXT )";


    private static final String SQL_CREATE_DEPT_EMP_ENTRY =
            "CREATE TABLE " + EmployeesContract.TABLE_NAME_DEPT_EMP + " (" +
                    EmployeesContract.DEPT_EMP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    EmployeesContract.COLUMN_EMPLOYEE_NO + " INTEGER NOT NULL, " +
                    EmployeesContract.Department_NO + " INTEGER NOT NULL, " +
                    EmployeesContract.COLUMN_FROM_DATE + " TEXT , " +
                    EmployeesContract.COLUMN_TO_DATE + " TEXT ," +
                    " FOREIGN KEY ("+EmployeesContract.COLUMN_EMPLOYEE_NO+") REFERENCES "+EmployeesContract.TABLE_NAME_EMPLOYEES+"("+EmployeesContract.COLUMN_EMPLOYEE_NO+"),"+
                    " FOREIGN KEY ("+EmployeesContract.Department_NO+") REFERENCES "+EmployeesContract.TABLE_NAME_DEPARTMENTS+"("+EmployeesContract.Department_NO+"))";




    private static final String SQL_DELETE_ENTRY_EMPLOYEE =
            "DROP TABLE IF EXISTS " + EmployeesContract.TABLE_NAME_EMPLOYEES;

    private static final String SQL_DELETE_ENTRY_SALARY =
            "DROP TABLE IF EXISTS " + EmployeesContract.TABLE_NAME_SALARIES;

    private static final String SQL_DELETE_ENTRY_TITLES =
            "DROP TABLE IF EXISTS " + EmployeesContract.TABLE_NAME_TITLES;

    private static final String SQL_DELETE_ENTRY_DEPARTMENT =
            "DROP TABLE IF EXISTS " + EmployeesContract.TABLE_NAME_DEPARTMENTS;

    private static final String SQL_DELETE_ENTRY_DEPT_EMP =
            "DROP TABLE IF EXISTS " + EmployeesContract.TABLE_NAME_DEPT_EMP;


    public EmployeeDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "PetDbHelper: Constructor");

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("PRAGMA foreign_keys = ON;");
        sqLiteDatabase.execSQL(SQL_CREATE_EMPLOYEE_ENTRY);
        sqLiteDatabase.execSQL(SQL_CREATE_SALARY_ENTRY);
        sqLiteDatabase.execSQL(SQL_CREATE_TITLE_ENTRY);
        sqLiteDatabase.execSQL(SQL_CREATE_DEPARTMENT_ENTRY);
        sqLiteDatabase.execSQL(SQL_CREATE_DEPT_EMP_ENTRY);
        Log.d(TAG, "onCreate: Call");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        Log.d(TAG, "onUpgrade: ");
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRY_EMPLOYEE);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRY_SALARY);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRY_TITLES);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRY_DEPARTMENT);
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRY_DEPT_EMP);
        onCreate(sqLiteDatabase);
    }


    public Cursor getAllEmployees() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.query(EmployeesContract.TABLE_NAME_EMPLOYEES, null, EmployeesContract.COLUMN_IS_DELETED + " = '" + 0 + "' ", null, null, null, null);
        return res;
    }

    public Cursor getEmployeeDetail(String empId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.query(EmployeesContract.TABLE_NAME_EMPLOYEES, null, EmployeesContract.COLUMN_EMPLOYEE_NO + " = '" + empId + "' ", null, null, null, null,"1");
        return res;
    }
    public String getEmployeeSalary(String empId) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {EmployeesContract.COLUMN_SALARY};
        Cursor res = db.query(EmployeesContract.TABLE_NAME_SALARIES, columns, EmployeesContract.COLUMN_EMPLOYEE_NO + " = '" + empId + "' ", null, null, null, null, "1");
        String salary ="";
        if(res.getCount()>0)
        if(res.moveToFirst()) {
            salary = res.getString(0);
        }

        return salary;
    }
    public String getEmployeePosition(String empId) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {EmployeesContract.COLUMN_TITLE};
        Cursor res = db.query(EmployeesContract.TABLE_NAME_TITLES, columns, EmployeesContract.COLUMN_EMPLOYEE_NO + " = '" + empId + "' ", null, null, null, null,"1");
        String position ="";
        if(res.getCount()>0)
            if(res.moveToFirst()) {
                position = res.getString(0);
            }

        return position;
    }

    public String getEmployeeDepartment(String empId) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {EmployeesContract.Department_NO};
        Cursor res = db.query(EmployeesContract.TABLE_NAME_DEPT_EMP, columns, EmployeesContract.COLUMN_EMPLOYEE_NO + " = '" + empId + "' ", null, null, null, null,"1");
        String departId ="";
        if(res.getCount()>0)
            if(res.moveToFirst()) {
                departId = res.getString(0);
            }
        Log.d(TAG, "getEmployeeDepartment: Department Id is "+departId);

        String[] columns1 = {EmployeesContract.Department_NAME};
        Cursor res1 = db.query(EmployeesContract.TABLE_NAME_DEPARTMENTS, columns1, EmployeesContract.Department_NO + " = '" + departId + "' ", null, null, null, null,"1");
        String departName ="";
        if(res1.getCount()>0)
            if(res1.moveToFirst()) {
                departName = res1.getString(0);
            }

        Log.d(TAG, "getEmployeeDepartment: Department Name is "+departName);

        return departName;
    }



    public int deleteEmployee(String emp_id)
    {
        Log.d(TAG, "deleteEmployee: " + emp_id);

        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(EmployeesContract.COLUMN_IS_DELETED, 1);

        int deletedId = db.update(EmployeesContract.TABLE_NAME_EMPLOYEES, value,
                EmployeesContract.COLUMN_EMPLOYEE_NO + " = ? ", new String[]{emp_id});


        Log.d(TAG, "Delete Employee: Number of Record Deleted"+deletedId);

        return deletedId;
    }


    public long insertEmployee(String firstname,String lastname,String gender,String birthdate,
                               String address,String hireDate,int bonus)
    {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(EmployeesContract.COLUMN_FIRSTNAME,firstname);
        value.put(EmployeesContract.COLUMN_LASTNAME, lastname);
        value.put(EmployeesContract.COLUMN_GENDER, gender);
        value.put(EmployeesContract.COLUMN_BIRTH_DATE, birthdate);
        value.put(EmployeesContract.COLUMN_ADDRESS, address);
        value.put(EmployeesContract.COLUMN_HIRE_DATE, hireDate);
        value.put(EmployeesContract.COLUMN_BONUS, bonus);
        value.put(EmployeesContract.COLUMN_IS_DELETED, 0);

        long last_inserted_employee_id = db.insert(EmployeesContract.TABLE_NAME_EMPLOYEES, null, value);

        if (last_inserted_employee_id == -1)
            return -1;
        else
            return last_inserted_employee_id;


    }

    public int updateEmployee(String firstname,String lastname,String gender,String birthdate,
                              String address,String hireDate,String emp_id,int bonus)
    {


        Log.d(TAG, "updateEmployee: Hire date is "+hireDate);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(EmployeesContract.COLUMN_FIRSTNAME,firstname);
        value.put(EmployeesContract.COLUMN_LASTNAME, lastname);
        value.put(EmployeesContract.COLUMN_GENDER, gender);
        value.put(EmployeesContract.COLUMN_BIRTH_DATE, birthdate);
        value.put(EmployeesContract.COLUMN_ADDRESS, address);
        value.put(EmployeesContract.COLUMN_HIRE_DATE, hireDate);
        value.put(EmployeesContract.COLUMN_BONUS, bonus);
        value.put(EmployeesContract.COLUMN_IS_DELETED, 0);

         int updatedId = db.update(EmployeesContract.TABLE_NAME_EMPLOYEES, value,
                 EmployeesContract.COLUMN_EMPLOYEE_NO + " = ? ", new String[]{emp_id});

        Log.d(TAG, "updateEmployee: Number of Record Updated"+updatedId);

            return updatedId;


    }

    public void insertSalary(long emp_id,String salary,String hireDate)
    {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(EmployeesContract.COLUMN_EMPLOYEE_NO,emp_id);
        value.put(EmployeesContract.COLUMN_SALARY, salary);
        value.put(EmployeesContract.COLUMN_FROM_DATE, hireDate);
        value.put(EmployeesContract.COLUMN_TO_DATE, hireDate);

        long last_inserted_salary_id = db.insert(EmployeesContract.TABLE_NAME_SALARIES, null, value);

        if (last_inserted_salary_id == -1)
            Log.d(TAG, "insertSalary: Error Occured");
        else
            Log.d(TAG, "insertSalary: Success"+last_inserted_salary_id);
    }


    public int updateSalary(String emp_id,String salary,String hireDate)
    {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(EmployeesContract.COLUMN_EMPLOYEE_NO,emp_id);
        value.put(EmployeesContract.COLUMN_SALARY, salary);
        value.put(EmployeesContract.COLUMN_FROM_DATE, hireDate);
        value.put(EmployeesContract.COLUMN_TO_DATE, hireDate);

        int updatedId = db.update(EmployeesContract.TABLE_NAME_SALARIES, value,
                EmployeesContract.COLUMN_EMPLOYEE_NO + " = ? ", new String[]{emp_id});


        Log.d(TAG, "updateSalary: Number of Record Updated"+updatedId);

        return updatedId;
    }

    public void insertTitle(long emp_id,String title,String hireDate)
    {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(EmployeesContract.COLUMN_EMPLOYEE_NO,emp_id);
        value.put(EmployeesContract.COLUMN_TITLE, title);
        value.put(EmployeesContract.COLUMN_FROM_DATE, hireDate);
        value.put(EmployeesContract.COLUMN_TO_DATE, hireDate);

        long last_inserted_salary_id = db.insert(EmployeesContract.TABLE_NAME_TITLES, null, value);

        if (last_inserted_salary_id == -1)
            Log.d(TAG, "insertTitle: Error Occured");
        else
            Log.d(TAG, "insertTitle: Success"+last_inserted_salary_id);
    }


    public int updateTitle(String emp_id,String title,String hireDate)
    {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(EmployeesContract.COLUMN_EMPLOYEE_NO,emp_id);
        value.put(EmployeesContract.COLUMN_TITLE, title);
        value.put(EmployeesContract.COLUMN_FROM_DATE, hireDate);
        value.put(EmployeesContract.COLUMN_TO_DATE, hireDate);

        int updatedId = db.update(EmployeesContract.TABLE_NAME_TITLES, value,
                EmployeesContract.COLUMN_EMPLOYEE_NO + " = ? ", new String[]{emp_id});
        Log.d(TAG, "updateSalary: Number of Record Updated"+updatedId);

        return updatedId;
    }

    public long insertDepartment(String department)
    {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(EmployeesContract.Department_NAME,department);

        long last_inserted_department_id = db.insert(EmployeesContract.TABLE_NAME_DEPARTMENTS, null, value);

        if (last_inserted_department_id == -1)
           return -1;
        else
            return last_inserted_department_id;
    }


    public String getDepartmentId(String empId) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {EmployeesContract.Department_NO};
        Cursor res = db.query(EmployeesContract.TABLE_NAME_DEPT_EMP, columns, EmployeesContract.COLUMN_EMPLOYEE_NO + " = '" + empId + "' ", null, null, null, null,"1");
        String departId ="";
        if(res.getCount()>0)
            if(res.moveToFirst()) {
                departId = res.getString(0);
            }
        Log.d(TAG, "getEmployeeDepartment: Department Id is "+departId);
        return departId;

    }



    public int updateDepartment(String dep_id,String department)
    {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(EmployeesContract.Department_NAME,department);

        int updatedId = db.update(EmployeesContract.TABLE_NAME_DEPARTMENTS, value,
                EmployeesContract.Department_NO + " = ? ", new String[]{dep_id});

        Log.d(TAG, "updateDepartment: Number of Record Updated"+updatedId);

            return updatedId;
    }


    public void insertDeptEmp(long emp_id,long dept_id,String hireDate)
    {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(EmployeesContract.COLUMN_EMPLOYEE_NO,emp_id);
        value.put(EmployeesContract.Department_NO,dept_id);
        value.put(EmployeesContract.COLUMN_FROM_DATE,hireDate);
        value.put(EmployeesContract.COLUMN_TO_DATE,hireDate);

        long last_inserted_department_id = db.insert(EmployeesContract.TABLE_NAME_DEPT_EMP, null, value);

        if (last_inserted_department_id == -1)
            Log.d(TAG, "insertDeptEmp: Error Occured");
        else
            Log.d(TAG, "insertDeptEmp: Success"+last_inserted_department_id);
    }



    ///////////////////////////// Queries for Report ////////////////////////////////////////////

    public Cursor getEmployeesByDepartments()
    {
        SQLiteDatabase db = getReadableDatabase();

        //// query for distinct columns /////

//        String query = "Select distinct deptname " +
//                " from departments d inner join dept_emp de  on " +
//                " d.deptno = de.deptno" +
//                " order by d.deptno ";
//        Cursor c = db.rawQuery(query,null);
//        Log.d(TAG, "getEmployeesByDepartments: "+c.getCount());
//        while (c.moveToNext())
//        {
//            Log.d(TAG, "getEmployeesByDepartments: "+c.getString(0));
//        }


        String query1 = "Select sum(s.salary) , d.deptname , e.bonus" +
                " from dept_emp de inner join " +
                " departments d  on " +
                " d.deptno = de.deptno " +
                " inner join employees e on " +
                " e.empno = de.empno  " +
                " inner join salaries s on " +
                "  e.empno = s.empno " +
                " group by d.deptname ";


        Cursor c1 = db.rawQuery(query1,null);


        return c1;



    }


    public Cursor getEmpGreaterExp()
    {
        SQLiteDatabase db = getReadableDatabase();

        String query1 = "Select hiredate , firstname" +
                " from employees  where isDeleted = ? ";


        Cursor c1 = db.rawQuery(query1,new String[]{String.valueOf(0)});


        return c1;



    }








}
