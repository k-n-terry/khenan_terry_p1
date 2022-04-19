package dev.terry.services;

import dev.terry.entities.Employee;
import dev.terry.utilities.List;

public interface EmployeeService{
    Employee registerEmployee(Employee employee);

    Employee getEmployeeById(String empId);

    List<Employee> employeeRegistry();

    boolean removeEmployeeById(String empId);
}
