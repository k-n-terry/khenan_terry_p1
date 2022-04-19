package dev.terry.services;

import dev.terry.data.EmployeeDao;
import dev.terry.entities.Employee;
import dev.terry.utilities.List;

public class EmployeeServiceImpl implements EmployeeService{
    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao){
        this.employeeDao = employeeDao;
    }
    @Override
    public Employee registerEmployee(Employee employee){
        return this.employeeDao.createEmployee(employee);
    }

    @Override
    public Employee getEmployeeById(String empId){
        return this.employeeDao.readEmployeeById(empId);
    }

    @Override
    public List<Employee> employeeRegistry(){
        return this.employeeDao.readAllEmployees();
    }

    @Override
    public boolean removeEmployeeById(String empId){
        boolean output = this.employeeDao.deleteEmployeeById(empId);
        return output;
    }
}
