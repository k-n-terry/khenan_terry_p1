package dev.terry.data_tests;

import dev.terry.data.EmployeeDao;
import dev.terry.data.EmployeeDaoImpl;
import dev.terry.entities.Employee;
import dev.terry.utilities.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmployeeDao_readAll {
    static EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Test
    void can_read_employee_row_by_id(){
        // read the id from readEmployee obj.
        List<Employee> allEmployees = employeeDao.readAllEmployees();
        System.out.println(allEmployees.toString());

        int allEmployeesCount = allEmployees.size();

        Assertions.assertTrue(allEmployeesCount != 0);
    }
}
