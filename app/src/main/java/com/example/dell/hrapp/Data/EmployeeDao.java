package com.example.dell.hrapp.Data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by sardar.khan on 9/28/2018.
 */
@Dao
public interface EmployeeDao {

    @Query("SELECT * from employees where isDeleted == 0")
    List<Employee> getAllEmployees();

    @Query("SELECT * from employees where empno = :empNo")
    Employee getEmployeeDetail(int empNo);

    @Query("Delete from employees where empno = :empNo")
    int deleteEmployee(int empNo);

    @Insert(onConflict = REPLACE)
    void insertEmployee(Employee employee);

    @Update(onConflict = REPLACE)
    void updateEmployee(Employee employee);


}
