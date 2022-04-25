package dev.terry.data_tests;

import dev.terry.data.EmployeeDao;
import dev.terry.data.EmployeeDaoImpl;
import dev.terry.entities.Employee;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDao_update_and_read {
    static EmployeeDao employeeDao = new EmployeeDaoImpl();
    static Employee savedJohnSmithDao = new Employee();

    // constant emp_id, but other fields change randomly
    Random r = new Random();
    String ei = "C965C80BC13B5E2529CB057158625AF0";
    String fn = "Some_"+Integer.toString(r.nextInt(99)+1);
    String ln = "One_"+Integer.toString(r.nextInt(99)+1);
    String kw = "HelloFromIntelliJ"+Integer.toString(r.nextInt(99)+1);

    @Test
    @Order(1)
    void can_update_employee_row(){
        // Employee obj.
        Employee johnSmith = new Employee();
        johnSmith.setEmpId(ei);
        johnSmith.setFirstname(fn);
        johnSmith.setLastname(ln);
        johnSmith.setPassphrase(kw);

        // call .updateEmployee method
        Employee johnSmithDao = employeeDao.updateEmployee(johnSmith);

        // save object
        EmployeeDao_update_and_read.savedJohnSmithDao = johnSmithDao;

        // check correct obj. update
        Assertions.assertEquals(johnSmith.getEmpId(), johnSmithDao.getEmpId());
        Assertions.assertEquals(johnSmith.getFirstname(), johnSmithDao.getFirstname());
        Assertions.assertEquals(johnSmith.getLastname(), johnSmithDao.getLastname());
        Assertions.assertEquals(johnSmith.getPassphrase(), johnSmithDao.getPassphrase());
    }
    @Test
    @Order(2)
    void can_read_employee_row_by_id(){
        // read the id from readEmployee obj.
        Employee readEmployee = employeeDao.readEmployeeById(savedJohnSmithDao.getEmpId());
        System.out.println(readEmployee.toString());

        Assertions.assertEquals(savedJohnSmithDao.getEmpId(), readEmployee.getEmpId());
    }
}
