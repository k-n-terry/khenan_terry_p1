package dev.terry.api;

import com.google.gson.Gson;
import dev.terry.data.EmployeeDaoImpl;
import dev.terry.entities.Employee;
import dev.terry.exceptions.ResourceNotFound;
import dev.terry.services.EmployeeService;
import dev.terry.services.EmployeeServiceImpl;
import dev.terry.utilities.List;
import io.javalin.Javalin;

public class WebApp {
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDaoImpl());
    public static Gson gson = new Gson();

    public static void main(String[] args){
        Javalin app = Javalin.create();

        /* create */
        app.post("/employees", context -> {
            String body = context.body();
            Employee employee = gson.fromJson(body, Employee.class);
            employeeService.registerEmployee(employee);

            context.status(201);
            String employeeJSON = gson.toJson(employee);
            context.result(employeeJSON);
        });

        /* read */
        // get employee JSON by id
        app.get("/employees/{empId}", context -> {
            String empId = context.pathParam("empId");
            try{
                String employeeJSON = gson.toJson(employeeService.getEmployeeById(empId));
                context.result(employeeJSON);
            }catch(ResourceNotFound e){
                context.status(404);
                context.result("Employee ID{ "+empId+" } - not found");
            }
        });

        // get all employees by id JSON
        app.get("/employees", context -> {
            try{
                List<Employee> employees = employeeService.employeeRegistry();
                String employeesJSON = gson.toJson(employees);
                context.result(employeesJSON);
            }catch(ResourceNotFound e){
                context.status(404);
                context.result("No employees found");
            }
        });

        /* update */

        /* delete */

        // start
        app.start(7000);
    }
}
