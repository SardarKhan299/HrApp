package com.example.dell.hrapp.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by DELL on 4/13/2017.
 */
@Entity (tableName = "employees")
public class Employee {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "empno")
    String empNo;
    @ColumnInfo(name = "firstname")
    String firstname;
    @ColumnInfo(name = "lastname")
    String lastname;
    @ColumnInfo(name = "gender")
    String gender;
    @ColumnInfo(name = "birthdate")
    String birthdate;
    @ColumnInfo(name = "address")
    String address;
    @ColumnInfo(name = "hiredate")
    String hireDate;
    @ColumnInfo(name = "bonus")
    String bonus;
    @ColumnInfo(name = "isDeleted")
    String isDeleted;
    @Ignore
    String salaryPlusBonus;
    @Ignore
    String departmentName;
    @Ignore
    String salary;


    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getSalaryPlusBonus() {
        return salaryPlusBonus;
    }

    public void setSalaryPlusBonus(String salaryPlusBonus) {
        this.salaryPlusBonus = salaryPlusBonus;
    }



    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }



    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }




    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }





}
