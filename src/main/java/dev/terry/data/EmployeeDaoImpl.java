package dev.terry.data;

import dev.terry.entities.Employee;
import dev.terry.exceptions.EmployeeIdException;
import dev.terry.utilities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoImpl implements EmployeeDao{
    // Create
    @Override
    public Employee createEmployee(Employee employee){
        try{
            // connect to database: "khenan_terry_p1"
            Connection conn = ConnectUtil.createConnect();

            // SQL insertion into table: "employee"
            String sql = "insert into employee values(?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            // string parameters
            ps.setString(1, employee.getEmpId());
            ps.setString(2, employee.getFirstname());
            ps.setString(3, employee.getLastname());
            ps.setString(4, employee.getRegistry());

            ps.execute();

            return employee;
        }catch(SQLException e){
            System.out.println("SQLException occured!!!");
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }
    // Read
    @Override
    public Employee readEmployeeById(String empId) throws EmployeeIdException{
        try{
            // connect to database: "khenan_terry_p1"
            Connection conn = ConnectUtil.createConnect();

            // SQL selection from table: "employee"
            String sql = "select * from employee where empId = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,empId);

            ResultSet rs = ps.executeQuery();

            rs.next();

            // assign rs-results to fields in employee object
            Employee employee = new Employee();
            // set fields
            employee.setEmpId(rs.getString("empId"));
            employee.setFirstname(rs.getString("firstname"));
            employee.setLastname(rs.getString("lastname"));
            employee.setRegistry(rs.getString("registry"));

            return employee;
        }catch(SQLException e){
            System.out.println("SQLException occured!!!");
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }
    @Override
    public List<Employee> readAllEmployees(){
        try{
            // connect to database: "khenan_terry_p1"
            Connection conn = ConnectUtil.createConnect();

            // SQL selection from table: "employee"
            String sql = "select * from employee;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            List<Employee> employees = new ArrayList();

            // while the result-set has a new line:
            while(rs.next()){
                // assign rs-results to fields in employee object
                Employee employee = new Employee();
                // set fields
                employee.setEmpId(rs.getString("empId"));
                employee.setFirstname(rs.getString("firstname"));
                employee.setLastname(rs.getString("lastname"));
                employee.setRegistry(rs.getString("registry"));
                employees.add(employee);
            }
        return employees;
        }catch(SQLException e){
            System.out.println("SQLException occured!!!");
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }

    // Update
    @Override
    public Employee updateEmployee(Employee employee){
        try{
            // connect to database: "khenan_terry_p1"
            Connection conn = ConnectUtil.createConnect();

            // SQL insertion into table: "employee"
            String sql = "update employee set firstname=?, lastname=? where empId=?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            // string parameters
            ps.setString(1, employee.getFirstname());
            ps.setString(2, employee.getLastname());
            ps.setString(3, employee.getEmpId());

            ps.execute();

            return employee;
        }catch(SQLException e){
            System.out.println("SQLException occured!!!");
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }
    // Delete (Update as unlisted)
    @Override
    public boolean unlistEmployeeById(String empId){
        try{
            // connect to database: "khenan_terry_p1"
            Connection conn = ConnectUtil.createConnect();

            // SQL insertion into table: "employee"
            String sql = "update employee set registry=? where empId=?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            // string parameters
            ps.setString(1, "Unlisted");
            ps.setString(2, empId);

            ps.execute();

            return true;
        }catch(SQLException e){
            System.out.println("SQLException occured!!!");
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return false;
        }
    }
}
