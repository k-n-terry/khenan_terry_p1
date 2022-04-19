package dev.terry.data_tests;

import dev.terry.data.EmployeeDao;
import dev.terry.data.EmployeeDaoImpl;
import dev.terry.entities.Employee;
import dev.terry.utilities.UniqueIdMD5;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDao_create_and_read {
    static EmployeeDao employeeDao = new EmployeeDaoImpl();
    static Employee savedJohnSmithDao = new Employee();

    // theoretical fields; names are generated randomly
    Random r = new Random();
    String fn = "John"+Integer.toString(r.nextInt(99999)+1);
    String ln = "Smith"+Integer.toString(r.nextInt(99999)+1);
    String kw = "HiFromIntelliJ";

    @Test
    @Order(1)
    void can_create_employee_row(){
        // Employee obj.
        Employee johnSmith = new Employee();
        johnSmith.setFirstname(fn);
        johnSmith.setLastname(ln);
        johnSmith.setKeyword(kw);

        // call employeeDao.createEmployee()
        Employee johnSmithDao = employeeDao.createEmployee(johnSmith);

        // save johnSmithDao
        EmployeeDao_create_and_read.savedJohnSmithDao = johnSmithDao;

        // set theoretical ID value
        UniqueIdMD5 uniqueId = new UniqueIdMD5(johnSmith);
        johnSmith.setEmpId(uniqueId.makeUniqueId());

        System.out.println(johnSmith.getEmpId());

        // check correct obj. creation
        Assertions.assertEquals(johnSmith.getEmpId(), johnSmithDao.getEmpId());
        Assertions.assertEquals(johnSmith.getFirstname(), johnSmithDao.getFirstname());
        Assertions.assertEquals(johnSmith.getLastname(), johnSmithDao.getLastname());
        Assertions.assertEquals(johnSmith.getKeyword(), johnSmithDao.getKeyword());
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