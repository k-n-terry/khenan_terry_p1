package dev.terry.data;

import dev.terry.entities.Employee;
import dev.terry.utilities.List;

public interface EmployeeDao {
    // create
    Employee createEmployee(Employee employee);
    // read
    Employee readEmployeeById(String empId);
    List<Employee> readAllEmployees();
    // update
    Employee updateEmployee(Employee employee);
    // delete
    boolean deleteEmployeeById(String empId);
}
