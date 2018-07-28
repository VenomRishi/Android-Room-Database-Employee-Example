package com.example.acer.roomdatabaseemployeeexample;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface EmployeeDao {

    @Query("select * from employee")
    List<Employee> getEmployee();

    @Query("select * from employee where name=:name")
    List<Employee> getSpecificEmployee(String name);

    @Insert
    void insertEmployee(Employee... employees);

    @Update
    void updateEmployee(Employee employee);

    @Delete
    void deleteEmployee(Employee employee);
}
