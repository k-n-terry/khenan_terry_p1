package dev.terry.entities_tests;

import dev.terry.entities.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Employee_toString{
    @Test
    public void employee_to_string(){
        // theoretical fields
        String ei = "c577e4c72920997edbb6653e23d382b7"; // MD5 id
        String fn = "John";
        String ln = "Smith";
        String kw = "HiFromIntelliJ";

        // Employee obj.
        Employee johnSmith = new Employee();
        johnSmith.setEmpId(ei.toUpperCase());
        johnSmith.setFirstname(fn);
        johnSmith.setLastname(ln);
        johnSmith.setPassphrase(kw);

        System.out.println(johnSmith.toString());
        Assertions.assertEquals(johnSmith.getEmpId(), ei.toUpperCase());
        Assertions.assertEquals(johnSmith.getFirstname(), fn);
        Assertions.assertEquals(johnSmith.getLastname(), ln);
        Assertions.assertEquals(johnSmith.getPassphrase(), kw);
    }
}
