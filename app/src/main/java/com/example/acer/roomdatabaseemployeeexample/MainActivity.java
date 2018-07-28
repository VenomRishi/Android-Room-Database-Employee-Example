package com.example.acer.roomdatabaseemployeeexample;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EmployeeDatabase employeeDatabase;
    List<Employee> employees;

    private TextView outputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        outputTextView=(TextView) findViewById(R.id.outputTextView);

        employeeDatabase = Room
                .databaseBuilder(this, EmployeeDatabase.class, "emp_db")
                .allowMainThreadQueries()
                .build();

        //insertRecordsFromDb();
        fetchRecordsFromDb();
        //fetchRecordsByName();
        //updateRecordsFromDb();
        //deleteRecordFromDb();
    }

    private void insertRecordsFromDb() {
        Employee emp1=new Employee();
        emp1.setName("rishi");
        emp1.setAge(24);
        emp1.setLocation("mumbai");
        emp1.setCompany("google");

        Employee emp2=new Employee();
        emp2.setName("ameya");
        emp2.setAge(24);
        emp2.setLocation("pune");
        emp2.setCompany("microsoft");
        employeeDatabase.employeeDao().insertEmployee(emp1,emp2);
        Toast.makeText(this, "Data added in Database", Toast.LENGTH_SHORT).show();
    }
    private void fetchRecordsFromDb(){
        employees=employeeDatabase.employeeDao().getEmployee();
        if (employees!=null&&employees.size()>0){
            for (Employee employee:employees){
                outputTextView.setText(parseRecords(employee)+"\n");
                Toast.makeText(this, parseRecords(employee), Toast.LENGTH_LONG).show();
            }

        }
    }
    private void fetchRecordsByName(){
        employees=employeeDatabase.employeeDao().getSpecificEmployee("rishi");
        if (employees!=null&&employees.size()>0){
            for (Employee employee:employees){
                outputTextView.setText(parseRecords(employee)+"\n");
                Toast.makeText(this, parseRecords(employee), Toast.LENGTH_SHORT).show();
            }

        }
    }
    private void updateRecordsFromDb(){
        employees=employeeDatabase.employeeDao().getEmployee();
        if (employees!=null&&employees.size()>0){
            Employee employee=employees.get(0);
            employee.setName("sandip");
            employeeDatabase.employeeDao().updateEmployee(employee);
        }
        Toast.makeText(this, "Update Success", Toast.LENGTH_SHORT).show();

    }
    private void deleteRecordFromDb() {
        employees=employeeDatabase.employeeDao().getEmployee();
        if (employees!=null&&employees.size()>0){
            Employee employee=employees.get(0);
            employeeDatabase.employeeDao().deleteEmployee(employee);
            Toast.makeText(this, "zero'th index employee is deleted", Toast.LENGTH_SHORT).show();
        }
    }

    private String parseRecords(Employee employee){
        return employee.getName()+" : "
                +employee.getAge()+" : "
                +employee.getLocation()+" : "
                +employee.getCompany();
    }
}