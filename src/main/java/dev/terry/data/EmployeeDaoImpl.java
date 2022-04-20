package dev.terry.data;

import dev.terry.entities.Employee;
import dev.terry.utilities.ArrayList;
import dev.terry.utilities.ConnectUtil;
import dev.terry.utilities.List;
import dev.terry.utilities.UniqueIdMD5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDaoImpl implements EmployeeDao{
    @Override
    public Employee createEmployee(Employee employee){
        try{
            // connect to database: "khenan_terry_p1"
            Connection conn = ConnectUtil.createConnect();

            // SQL insertion into table: "employee"
            String sql = "insert into employee values(?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            // unique empId for employee
            UniqueIdMD5 uniqueId = new UniqueIdMD5(employee);
            employee.setEmpId(uniqueId.makeUniqueId());

            // string parameters
            ps.setString(1, employee.getEmpId());
            ps.setString(2, employee.getFirstname());
            ps.setString(3, employee.getLastname());
            ps.setString(4, employee.getPassphrase());

            ps.execute();

            return employee;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Employee readEmployeeById(String empId){
        try{
            // connect to database: "khenan_terry_p1"
            Connection conn = ConnectUtil.createConnect();

            // SQL selection from table: "employee"
            String sql = "select * from employee where emp_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,empId);

            ResultSet rs = ps.executeQuery();

            rs.next();

            // assign rs-results to fields in employee object
            Employee employee = new Employee();
            // set fields
            employee.setEmpId(rs.getString("emp_id"));
            employee.setFirstname(rs.getString("firstname"));
            employee.setLastname(rs.getString("lastname"));
            employee.setPassphrase(rs.getString("passphrase"));

            return employee;
        }catch(SQLException e){
            e.printStackTrace();
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
                employee.setEmpId(rs.getString("emp_id"));
                employee.setFirstname(rs.getString("firstname"));
                employee.setLastname(rs.getString("lastname"));
                employee.setPassphrase(rs.getString("passphrase"));
                employees.add(employee);
            }
        return employees;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public Employee updateEmployee(Employee employee){
        try{
            // connect to database: "khenan_terry_p1"
            Connection conn = ConnectUtil.createConnect();

            // SQL insertion into table: "employee"
            String sql = "update employee set firstname=?, lastname=?, passphrase=?  where emp_id=?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            // string parameters
            ps.setString(1, employee.getFirstname());
            ps.setString(2, employee.getLastname());
            ps.setString(3, employee.getPassphrase());
            ps.setString(4, employee.getEmpId());

            ps.execute();

            return employee;
        }catch(SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteEmployeeById(String empId){
        try{
            // connect to database: "khenan_terry_p1"
            Connection conn = ConnectUtil.createConnect();

            // SQL deletion from table: "employee"
            String sql = "delete from employee where emp_id=?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            // string parameters
            ps.setString(1, empId);

            ps.execute();

            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
