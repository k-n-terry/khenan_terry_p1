package dev.terry.data_tests;

import dev.terry.data.EmployeeDao;
import dev.terry.data.EmployeeDaoImpl;
import dev.terry.entities.Employee;
import dev.terry.utilities.UniqueIdMD5;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDao_create_and_read {
    private static EmployeeDao employeeDao = new EmployeeDaoImpl();
    private static Employee savedReadMeDao = new Employee();

    // theoretical fields; names are generated randomly
    private Random r = new Random();
    private String firstname = "Intelligent_"+Integer.toString(r.nextInt(999));
    private String lastname = "Jay_"+Integer.toString(r.nextInt(999));
    private String registry = "Listed";
    private String empId = new UniqueIdMD5().makeUniqueId(firstname,lastname);

    @Test
    @Order(1)
    void can_create_employee_row(){
        // Employee obj.
        Employee readMe = new Employee();
        readMe.setEmpId(empId);
        readMe.setFirstname(firstname);
        readMe.setLastname(lastname);
        readMe.setRegistry(registry);

        // call .createEmployee()
        Employee readMeDao = employeeDao.createEmployee(readMe);

        // save for next test
        EmployeeDao_create_and_read.savedReadMeDao = readMeDao;

        // check correct obj. creation
        Assertions.assertEquals(readMe.getEmpId(), readMeDao.getEmpId());
        Assertions.assertEquals(readMe.getFirstname(), readMeDao.getFirstname());
        Assertions.assertEquals(readMe.getLastname(), readMeDao.getLastname());
        Assertions.assertEquals(readMe.getRegistry(), readMeDao.getRegistry());
    }
    @Test
    @Order(2)
    void can_read_employee_row_by_id(){
        // read the id from readEmployee obj.
        Employee readEmployee = employeeDao.readEmployeeById(savedReadMeDao.getEmpId());
        System.out.println(readEmployee.toString());

        Assertions.assertEquals(savedReadMeDao.getEmpId(), readEmployee.getEmpId());
    }
}
