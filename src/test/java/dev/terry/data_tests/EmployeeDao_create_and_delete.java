package dev.terry.data_tests;

import dev.terry.data.EmployeeDao;
import dev.terry.data.EmployeeDaoImpl;
import dev.terry.entities.Employee;
import dev.terry.utilities.UniqueIdMD5;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDao_create_and_delete{
    static EmployeeDao employeeDao = new EmployeeDaoImpl();
    static Employee savedJohnSmithDao = new Employee();

    // theoretical fields; names are generated randomly
    Random r = new Random();
    String fn = "Deleterius_"+Integer.toString(r.nextInt(99999)+1);
    String ln = "Maximus_"+Integer.toString(r.nextInt(99999)+1);
    String kw = "HiFromIntelliJ";

    @Test
    @Order(1)
    void can_create_employee_row(){
        // Employee obj.
        Employee johnSmith = new Employee();
        johnSmith.setFirstname(fn);
        johnSmith.setLastname(ln);
        johnSmith.setPassphrase(kw);

        // call .createEmployee()
        Employee johnSmithDao = employeeDao.createEmployee(johnSmith);

        // save johnSmithDao
        EmployeeDao_create_and_delete.savedJohnSmithDao = johnSmithDao;

        // set theoretical ID value
        UniqueIdMD5 uniqueId = new UniqueIdMD5(johnSmith);
        johnSmith.setEmpId(uniqueId.makeUniqueId());

        System.out.println(johnSmith.getEmpId());

        // check correct obj. creation
        Assertions.assertEquals(johnSmith.getEmpId(), johnSmithDao.getEmpId());
        Assertions.assertEquals(johnSmith.getFirstname(), johnSmithDao.getFirstname());
        Assertions.assertEquals(johnSmith.getLastname(), johnSmithDao.getLastname());
        Assertions.assertEquals(johnSmith.getPassphrase(), johnSmithDao.getPassphrase());
    }
    @Test
    @Order(2)
    void can_delete_employee_row_by_id(){
        System.out.println("Deleted employee{ "+savedJohnSmithDao.getEmpId()+" }");

        boolean bool = employeeDao.deleteEmployeeById(savedJohnSmithDao.getEmpId());
        Assertions.assertTrue(bool);
    }
}
