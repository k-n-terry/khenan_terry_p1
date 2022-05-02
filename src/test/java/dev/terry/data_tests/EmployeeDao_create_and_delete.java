package dev.terry.data_tests;

import dev.terry.data.EmployeeDao;
import dev.terry.data.EmployeeDaoImpl;
import dev.terry.entities.Employee;
import dev.terry.utilities.UniqueIdMD5;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDao_create_and_delete{
    private static EmployeeDao employeeDao = new EmployeeDaoImpl();
    private static Employee savedDeleteMeDao = new Employee();

    // theoretical fields; names are generated randomly
    private Random r = new Random();
    private String firstname = "Deleterius_"+Integer.toString(r.nextInt(999));
    private String lastname = "Maximus_"+Integer.toString(r.nextInt(999));
    private String registry = "Listed";
    private String empId = new UniqueIdMD5().makeUniqueId(firstname,lastname);

    @Test
    @Order(1)
    void can_create_employee_row(){
        // Employee obj.
        Employee deleteMe = new Employee();
        deleteMe.setEmpId(empId);
        deleteMe.setFirstname(firstname);
        deleteMe.setLastname(lastname);
        deleteMe.setRegistry(registry);

        // call .createEmployee()
        Employee deleteMeDao = employeeDao.createEmployee(deleteMe);

        // save for next test
        EmployeeDao_create_and_delete.savedDeleteMeDao = deleteMeDao;

        // check correct obj. creation
        Assertions.assertEquals(deleteMe.getEmpId(), deleteMeDao.getEmpId());
        Assertions.assertEquals(deleteMe.getFirstname(), deleteMeDao.getFirstname());
        Assertions.assertEquals(deleteMe.getLastname(), deleteMeDao.getLastname());
        Assertions.assertEquals(deleteMe.getRegistry(), deleteMeDao.getRegistry());
    }
    @Test
    @Order(2)
    void can_unlist_employee_by_id(){
        System.out.println("Unlisted EmployeeID{ "+savedDeleteMeDao.getEmpId()+" }");

        boolean bool = employeeDao.unlistEmployeeById(savedDeleteMeDao.getEmpId());
        Assertions.assertTrue(bool);
    }
}
