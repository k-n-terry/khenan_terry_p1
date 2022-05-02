package dev.terry.api;

import com.google.gson.Gson;
import dev.terry.data.EmployeeDaoImpl;
import dev.terry.data.ExpenseDaoImpl;
import dev.terry.entities.Employee;
import dev.terry.entities.Expense;
import dev.terry.exceptions.EmployeeIdException;
import dev.terry.exceptions.ResourceNotFound;
import dev.terry.services.EmployeeService;
import dev.terry.services.EmployeeServiceImpl;
import dev.terry.services.ExpenseService;
import dev.terry.services.ExpenseServiceImpl;
import dev.terry.utilities.List;
import dev.terry.utilities.LogLevel;
import dev.terry.utilities.Logger;
import dev.terry.utilities.UniqueIdMD5;
import io.javalin.Javalin;

import java.util.Objects;

public class WebApp{
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDaoImpl());
    public static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDaoImpl());
    public static Gson gson = new Gson();
    public static UniqueIdMD5 uniqueId = new UniqueIdMD5();

    public static void main(String[] args){
        Javalin app = Javalin.create();

        /* POST EMPLOYEES */
        app.post("/employees", context -> {
            System.out.println("Called POST employee route!!");
            String body = context.body();
            Employee employee = gson.fromJson(body, Employee.class);
            employee.setEmpId(uniqueId.makeUniqueId(employee.getFirstname(), employee.getLastname()));
            employeeService.registerEmployee(employee);

            context.status(201);
            String employeeJSON = gson.toJson(employee);
            context.result(employeeJSON);
        });
        /* GET EMPLOYEES */
        // by id
        app.get("/employees/{empId}", context -> {
            System.out.println("Called GET employee by id route!!");
            String empId = context.pathParam("empId");
            try{
                String employeeJSON = gson.toJson(employeeService.getEmployeeById(empId));
                if(employeeJSON.equals("null")){
                    context.status(404);
                    String message = "Employee ID{ "+empId+" } - not found";
                    context.result(message);
                    Logger.log(message, LogLevel.INFO);
                }else{
                    context.result(employeeJSON);
                }
            }catch(ResourceNotFound e){
                context.status(404);
                context.result("Employee ID{ "+empId+" } - not found");
            }
        });
        // all
        app.get("/employees", context -> {
            System.out.println("Called GET all employee route!!");
            try{
                List<Employee> employees = employeeService.employeeRegistry();
                String employeesJSON = gson.toJson(employees);
                context.result(employeesJSON);
            }catch(EmployeeIdException e){
                context.status(404);
                context.result("No employees found");
            }
        });
        /* PUT EMPLOYEES */
        app.put("/employees/{empId}", context -> {
            System.out.println("Called PUT employee route!!");
            String empId = context.pathParam("empId");
            try{
                String employeeJSON = gson.toJson(employeeService.getEmployeeById(empId));
                if(employeeJSON.equals("null")){
                    context.status(404);
                    context.result("Employee ID{ "+empId+" } - not found");
                }else{
                    String body = context.body();

                    Employee employee = gson.fromJson(body, Employee.class);
                    employee.setEmpId(empId);

                    employeeService.updateEmployeeField(employee);

                    context.result("Updated Info. for EmployeeID{"+empId+"}");
                    context.status(200);
                }
            }catch(ResourceNotFound e){
                context.status(404);
                context.result(e.getMessage());
            }
        });
        /* DELETE EMPLOYEES */
        app.delete("/employees/{empId}",context -> {
            System.out.println("Called DELETE employee route!!");
            String empId = context.pathParam("empId");
            boolean result = employeeService.deactivateEmployeeById(empId);
            if(result){
                context.status(204);
            }else{
                context.status(404);
            }
        });

        /* POST EXPENSES */
        // EXPENSE
        app.post("/expenses", context -> {
            System.out.println("Called POST expense route!!");
            String body = context.body();
            Expense expense = gson.fromJson(body, Expense.class);
            Employee check = employeeService.getEmployeeById(expense.getEmpId());
            try{
                if(check.equals(null)){
                    context.status(404);
                    context.result("EmployeeID{"+expense.getEmpId()+"} was not found");
                }else{
                    if(check.getRegistry().equals("Listed")){
                        expenseService.registerExpense(expense);

                        context.status(201);
                        String expenseJSON = gson.toJson(expense);
                        context.result(expenseJSON);
                    }else if(check.getRegistry().equals("Unlisted")){
                        context.status(403);
                        context.result("EmployeeID{" + expense.getEmpId() + "} was found, but expenses may not be submitted at this time.");
                    }
                }
            }catch(NullPointerException e){
                e.printStackTrace();
                context.status(404);
                context.result("EmployeeID{"+expense.getEmpId()+"} was not found");
            }
        });
        // nested REST
        app.post("/employees/{empId}/expenses", context -> {
            System.out.println("Called POST employee expenses route!!");
            String empId = context.pathParam("empId");
            String body = context.body();
            Expense expense = gson.fromJson(body, Expense.class);
            expense.setEmpId(empId);
            Employee check = employeeService.getEmployeeById(expense.getEmpId());
            if(check.getRegistry()=="Listed"){
                expenseService.registerExpense(expense);

                context.status(201);
                String expenseJSON = gson.toJson(expense);
                context.result(expenseJSON);
            }else{
                context.status(404);
                context.result("EmployeeID{"+expense.getEmpId()+"} was found, but expenses may not be submitted at this time.");
            }
        });
        /* GET EXPENSES */
        // by id
        app.get("/expenses/{expenseId}", context -> {
            System.out.println("Called GET expense by id route!!");
            int expenseId = Integer.parseInt(context.pathParam("expenseId"));
            try{
                String expenseJSON = gson.toJson(expenseService.getExpenseById(expenseId));
                if(expenseJSON.equals("null")){
                    context.status(404);
                    context.result("Expense ID{ "+expenseId+" } - not found");
                }else{
                    context.result(expenseJSON);
                }
            }catch(ResourceNotFound e){
                context.status(404);
                context.result("Expense ID{ "+expenseId+" } - not found");
            }
        });
        // all
        app.get("/expenses", context -> {
            System.out.println("Called GET all expenses route!!");
            try{
                List<Expense> expenses = expenseService.expenseRegistry();
                String expensesJSON = gson.toJson(expenses);
                context.result(expensesJSON);
            }catch(ResourceNotFound e){
                context.status(404);
                context.result("No expenses found");
            }
        });
        // nested REST
        app.get("/expenses/{empId}/expenses", context -> {
            System.out.println("Called GET employee expenses route!!");
            String empId = context.pathParam("empId");
            try{
                String expenseJSON = gson.toJson(expenseService.expenseRegistryByEmpId(empId));
                if(expenseJSON.equals("null")){
                    context.status(404);
                    context.result("Expense ID{ "+empId+" } - not found");
                }else{
                    context.result(expenseJSON);
                }
            }catch(ResourceNotFound e){
                context.status(404);
                context.result("Expense ID{ "+empId+" } - not found");
            }
        });
        /* PUT EXPENSES */
        app.put("/expenses/{expenseId}", context -> {
            System.out.println("Called PUT expense route!!");
            try {
                int expenseId = Integer.parseInt(context.pathParam("expenseId"));
                String body = context.body();

                Expense expense = gson.fromJson(body, Expense.class);
                expense.setExpenseId(expenseId);

                Expense check = expenseService.getExpenseById(expenseId);
                if(!Objects.equals(check.getStatus(), "Pending")){
                    context.status(403);
                    context.result("Expense ID{ "+expenseId+" } - may not be edited.");
                }else{
                    expenseService.updateExpenseFields(expense);
                    context.result("Updated Expense Info.");
                    context.status(200);
                }
            }catch(ResourceNotFound e){
                context.status(404);
                context.result(e.getMessage());
            }
        });
        /* PATCH EXPENSES */
        app.patch("/expenses/{expenseId}/{statusImp}", context -> {
            System.out.println("Called PATCH employee route!!");
            String status;
            try {
                int expenseId = Integer.parseInt(context.pathParam("expenseId"));
                String statusImp = context.pathParam("statusImp");
                if(statusImp.equals("approve")){
                    status = "Approved";
                }else{
                    status = "Denied";
                }

                Expense expense = new Expense();
                expense.setExpenseId(expenseId);
                expense.setStatus(status);

                Expense check = expenseService.getExpenseById(expenseId);
                if(!Objects.equals(check.getStatus(), "Pending")){
                    context.status(404);
                    context.result("Expense ID{ "+expenseId+" } - may not be edited.");
                }else{
                    expenseService.patchExpenseStatus(expense);
                    context.result("Updated Expense Info.");
                    context.status(200);
                }
            }catch(EmployeeIdException e){
                context.status(404);
                context.result(e.getMessage());
            }
        });
        /* DELETE EXPENSES */
        app.delete("/expenses/{expenseId}", context -> {
            System.out.println("Called DELETE expense route!!");
            int expenseId = Integer.parseInt(context.pathParam("expenseId"));
            try{
                String expenseJSON = gson.toJson(expenseService.getExpenseById(expenseId));
                Expense check = expenseService.getExpenseById(expenseId);
                if(!Objects.equals(check.getStatus(), "Pending")){
                    context.status(403);
                    context.result("Expense ID{ "+expenseId+" } - is non-pending and may not be deleted.");
                }else{
                    expenseService.removeExpenseById(expenseId);
                    context.result("Deleted Expense ID{"+expenseId+"}");
                    context.status(200);
                }
            }catch(ResourceNotFound e){
                context.status(404);
                context.result("Expense ID{ "+expenseId+" } - not found");
            }
        });

        // start
        app.start(7000);
    }
}
