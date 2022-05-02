package dev.terry.entities_tests;

import dev.terry.entities.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Employee_toString{
    @Test
    public void employee_to_string(){
        // theoretical fields
        String ei = "F4388D30B20EA0F2CEC8A934A0BB44FB"; // MD5 id
        String fn = "Intelli_01";
        String ln = "J_01";
        String ry = "Listed";

        // Employee obj.
        Employee johnSmith = new Employee();
        johnSmith.setEmpId(ei);
        johnSmith.setFirstname(fn);
        johnSmith.setLastname(ln);
        johnSmith.setRegistry(ry);

        System.out.println(johnSmith);
        Assertions.assertEquals(johnSmith.getEmpId(), ei);
        Assertions.assertEquals(johnSmith.getFirstname(), fn);
        Assertions.assertEquals(johnSmith.getLastname(), ln);
        Assertions.assertEquals(johnSmith.getRegistry(), ry);
    }
}
