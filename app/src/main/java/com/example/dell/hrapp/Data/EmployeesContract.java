package com.example.dell.hrapp.Data;

import android.provider.BaseColumns;

/**
 * Created by DELL on 4/11/2017.
 */
public class EmployeesContract implements BaseColumns {



    public static final String TABLE_NAME_EMPLOYEES = "employees";
    public static final String _ID = BaseColumns._ID;
    public static final String COLUMN_EMPLOYEE_NO = "empno";
    public static final String COLUMN_BIRTH_DATE = "birthdate";
    public static final String COLUMN_FIRSTNAME ="firstname";
    public static final String COLUMN_LASTNAME ="lastname";
    public static final String COLUMN_GENDER ="gender";
    public static final String COLUMN_ADDRESS ="address";
    public static final String COLUMN_HIRE_DATE ="hiredate";
    public static final String COLUMN_BONUS ="bonus";
    public static final String COLUMN_IS_DELETED ="isDeleted";


    public static final String TABLE_NAME_SALARIES = "salaries";
    public static final String Salary_ID = BaseColumns._ID;
    public static final String COLUMN_SALARY = "salary";
    public static final String COLUMN_FROM_DATE ="fromdate";
    public static final String COLUMN_TO_DATE ="todate";

    public static final String TABLE_NAME_TITLES = "titles";
    public static final String Title_ID = BaseColumns._ID;
    public static final String COLUMN_TITLE = "title";


    public static final String TABLE_NAME_DEPARTMENTS = "departments";
    public static final String Department_ID = BaseColumns._ID;
    public static final String Department_NO = "deptno";
    public static final String Department_NAME = "deptname";


    public static final String TABLE_NAME_DEPT_EMP = "dept_emp";
    public static final String DEPT_EMP_ID = BaseColumns._ID;



    public static final int GENDER_UNKNOWN = 0;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_FEMALE = 2;

}
