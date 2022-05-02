package dev.terry.data_tests;

import dev.terry.data.ExpenseDao;
import dev.terry.data.ExpenseDaoImpl;
import dev.terry.entities.Expense;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDao_update_and_read {
    static EmployeeDao employeeDao = new EmployeeDaoImpl();
    static Employee savedSomeOneDao = new Employee();

    // constant emp_id, but other fields change randomly
    Random r = new Random();
    String ei = "CDC996828540D64AD3E008E081ABF988";
    String fn = "Update_"+Integer.toString(r.nextInt(999));
    String ln = "From IntelliJ_"+Integer.toString(r.nextInt(999));
    String ry = "Listed";

    @Test
    @Order(1)
    void can_update_employee_row(){
        // Employee obj.
        Employee someOne = new Employee();
        someOne.setEmpId(ei);
        someOne.setFirstname(fn);
        someOne.setLastname(ln);
        someOne.setRegistry(ry);

        // call .updateEmployee method
        Employee someOneDao = employeeDao.updateEmployee(someOne);

        // save object
        EmployeeDao_update_and_read.savedSomeOneDao = someOneDao;

        // check correct obj. update
        Assertions.assertEquals(someOne.getEmpId(), someOneDao.getEmpId());
        Assertions.assertEquals(someOne.getFirstname(), someOneDao.getFirstname());
        Assertions.assertEquals(someOne.getLastname(), someOneDao.getLastname());
        Assertions.assertEquals(someOne.getRegistry(), someOneDao.getRegistry());
    }
    @Test
    @Order(2)
    void can_read_employee_row_by_id(){
        // read the id from readEmployee obj.
        Employee readEmployee = employeeDao.readEmployeeById(savedSomeOneDao.getEmpId());
        System.out.println(readEmployee.toString());

        Assertions.assertEquals(savedSomeOneDao.getEmpId(), readEmployee.getEmpId());
    }
}
