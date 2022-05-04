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
import org.eclipse.jetty.util.log.Log;

import java.util.Objects;

public class WebApp{
    public static EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDaoImpl());
    public static ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDaoImpl());
    public static Gson gson = new Gson();
    public static UniqueIdMD5 uniqueId = new UniqueIdMD5();

    public static void main(String[] args){
        Javalin app = Javalin.create();

        /* Welcome */
        app.get("/", context -> {
            context.result("WELCOME to Khenan Terry's Project 1: REST API!!!");
        });

        /* POST EMPLOYEES */
        app.post("/employees", context -> {
            String body = context.body();
            Employee employee = gson.fromJson(body, Employee.class);
            employee.setEmpId(uniqueId.makeUniqueId(employee.getFirstname(), employee.getLastname()));
            employeeService.registerEmployee(employee);

            context.status(201);
            String employeeJSON = gson.toJson(employee);
            context.result(employeeJSON);
            Logger.log("Registered EmployeeID{ "+employee.getEmpId()+" }!!!", LogLevel.INFO);
        });
        /* GET EMPLOYEES */
        // by id
        app.get("/employees/{empId}", context -> {
            String empId = context.pathParam("empId");
            try{
                String employeeJSON = gson.toJson(employeeService.getEmployeeById(empId));
                if(employeeJSON.equals("null")){
                    context.status(404);
                    String message = "EmployeeID{ "+empId+" } - not found!!!";
                    context.result(message);
                    Logger.log(message, LogLevel.INFO);
                }else{
                    context.result(employeeJSON);
                }
            }catch(ResourceNotFound e){
                context.status(404);
                String message = "Employee ID{ "+empId+" } - not found!!!";
                context.result(message);
                Logger.log(message, e.getMessage(), LogLevel.ERROR);
            }
        });
        // all
        app.get("/employees", context -> {
            try{
                List<Employee> employees = employeeService.employeeRegistry();
                String employeesJSON = gson.toJson(employees);
                context.result(employeesJSON);
                Logger.log("Called GET all employee route!!",LogLevel.INFO);
            }catch(EmployeeIdException e){
                context.status(404);
                String message = "No employees found!!!";
                context.result(message);
                Logger.log(message, e.getMessage(), LogLevel.ERROR);
            }
        });
        /* PUT EMPLOYEES */
        app.put("/employees/{empId}", context -> {
            String empId = context.pathParam("empId");
            try{
                String employeeJSON = gson.toJson(employeeService.getEmployeeById(empId));
                if(employeeJSON.equals("null")){
                    context.status(404);
                    String message = "Employee ID{ "+empId+" } - not found!!!";
                    context.result(message);
                    Logger.log(message, LogLevel.INFO);
                }else{
                    String body = context.body();

                    Employee employee = gson.fromJson(body, Employee.class);
                    employee.setEmpId(empId);

                    employeeService.updateEmployeeField(employee);

                    String message = "Updated Info. for EmployeeID{"+empId+"}!!!";
                    context.result(message);
                    Logger.log(message, LogLevel.INFO);
                    context.status(200);
                }
            }catch(ResourceNotFound e){
                context.status(404);
                context.result(e.getMessage());
                Logger.log(e.getMessage(), LogLevel.ERROR);
            }
        });
        /* DELETE EMPLOYEES */
        app.delete("/employees/{empId}",context -> {
            System.out.println("Called DELETE employee route!!");
            String empId = context.pathParam("empId");
            boolean result = employeeService.deactivateEmployeeById(empId);
            if(result){
                context.status(200);
            }else{
                context.status(404);
            }
        });

        /* POST EXPENSES */
        // EXPENSE
        app.post("/expenses", context -> {
            String body = context.body();
            Expense expense = gson.fromJson(body, Expense.class);
            Employee check = employeeService.getEmployeeById(expense.getEmpId());
            try{
                if(check.toString().equals(null)){
                    context.status(404);
                    String message = "EmployeeID{"+expense.getEmpId()+"} was not found!!!";
                    context.result(message);
                    Logger.log(message, LogLevel.INFO);
                }else{
                    if(check.getRegistry().equals("Listed")){
                        expenseService.registerExpense(expense);

                        context.status(201);
                        String expenseJSON = gson.toJson(expense);
                        context.result(expenseJSON);
                        Logger.log("Called POST expense route!!",LogLevel.INFO);
                    }else if(check.getRegistry().equals("Unlisted")){
                        context.status(403);
                        String message = "EmployeeID{" + expense.getEmpId() + "} was found, but expenses may not be submitted at this time!!!";
                        context.result(message);
                        Logger.log(message, LogLevel.INFO);
                    }
                }
            }catch(NullPointerException e){
                e.printStackTrace();
                context.status(404);
                String message = "EmployeeID{"+expense.getEmpId()+"} was not found!!!";
                context.result(message);
                Logger.log(message, LogLevel.INFO);
                Logger.log(e.getMessage(), LogLevel.ERROR);
            }
        });
        // nested REST
        app.post("/employees/{empId}/expenses", context -> {
            String empId = context.pathParam("empId");
            String body = context.body();
            Expense expense = gson.fromJson(body, Expense.class);
            expense.setEmpId(empId);
            Employee check = employeeService.getEmployeeById(empId);
            try{
                if(check.toString().equals(null)){
                    context.status(404);
                    String message = "EmployeeID{"+empId+"} was not found!!!";
                    context.result(message);
                    Logger.log(message, LogLevel.INFO);
                }else{
                    if(check.getRegistry().equals("Listed")){
                        expenseService.registerExpense(expense);

                        context.status(201);
                        String expenseJSON = gson.toJson(expense);
                        context.result(expenseJSON);
                        Logger.log("Expense created for EmployeeID{"+empId+"}!!!", LogLevel.INFO);
                    }else if(check.getRegistry().equals("Unlisted")){
                        context.status(403);
                        String message = "EmployeeID{" + expense.getEmpId() + "} was found, but expenses may not be submitted at this time!!!";
                        context.result(message);
                        Logger.log(message, LogLevel.INFO);
                    }
                }
            }catch(NullPointerException e){
                e.printStackTrace();
                context.status(404);
                String message = "EmployeeID{"+expense.getEmpId()+"} was not found!!!";
                context.result(message);
                Logger.log(message, LogLevel.INFO);
                Logger.log(e.getMessage(), LogLevel.ERROR);
            }
        });
        /* GET EXPENSES */
        // by id
        app.get("/expenses/{expenseId}", context -> {
            System.out.println();
            int expenseId = Integer.parseInt(context.pathParam("expenseId"));
            try{
                String expenseJSON = gson.toJson(expenseService.getExpenseById(expenseId));
                if(expenseJSON.equals("null")){
                    context.status(404);
                    String message = "Expense ID{ "+expenseId+" } - not found!!!";
                    context.result(message);
                    Logger.log(message, LogLevel.INFO);
                }else{
                    context.result(expenseJSON);
                    Logger.log("Called GET expense by id route!!!",LogLevel.INFO);
                }
            }catch(ResourceNotFound e){
                context.status(404);
                String message = "Expense ID{ "+expenseId+" } - not found!!!";
                context.result(message);
                Logger.log(message, LogLevel.INFO);
                Logger.log(e.getMessage(), LogLevel.ERROR);
            }
        });
        // all
        app.get("/expenses", context -> {
            String status = context.queryParam("status");
            try{
                if(status == null) {
                    List<Expense> expenses = expenseService.expenseRegistry();
                    String expensesJSON = gson.toJson(expenses);
                    context.result(expensesJSON);
                    Logger.log("Called GET all expenses route!!!", LogLevel.INFO);
                }else{
                    List<Expense> expenses = expenseService.expenseRegistryByStatus(status);
                    String expensesJSON = gson.toJson(expenses);
                    context.result(expensesJSON);
                    Logger.log("Called GET all expenses route!!!", LogLevel.INFO);
                }
            }catch(ResourceNotFound e){
                context.status(404);
                String message = "No expenses found!!!";
                context.result(message);
                Logger.log(message, LogLevel.INFO);
                Logger.log(e.getMessage(), LogLevel.ERROR);
            }
        });
        // nested REST
        app.get("/employees/{empId}/expenses", context -> {
            String empId = context.pathParam("empId");
            try{
                List<Expense> expenses = expenseService.expenseRegistryByEmpId(empId);
                String expensesJSON = gson.toJson(expenses);
                context.result(expensesJSON);
                Logger.log("Expenses request for EmployeeID{ "+empId+" }!!!",LogLevel.INFO);
            }catch(ResourceNotFound e){
                context.status(404);
                String message = "No expenses found!!!";
                context.result(message);
                Logger.log(message, LogLevel.INFO);
                Logger.log(e.getMessage(), LogLevel.ERROR);
            }
        });
        /* PUT EXPENSES */
        app.put("/expenses/{expenseId}", context -> {
            try {
                int expenseId = Integer.parseInt(context.pathParam("expenseId"));
                String body = context.body();

                Expense expense = gson.fromJson(body, Expense.class);
                expense.setExpenseId(expenseId);

                Expense check = expenseService.getExpenseById(expenseId);
                if(!Objects.equals(check.getStatus(), "Pending")){
                    context.status(403);
                    String message = "Expense ID{ "+expenseId+" } - may not be edited!!!";
                    context.result(message);
                    Logger.log(message, LogLevel.INFO);
                }else{
                    expenseService.updateExpenseFields(expense);
                    String message = "Updated Expense Info!!!";
                    context.result(message);
                    Logger.log(message, LogLevel.INFO);
                    context.status(200);
                }
            }catch(ResourceNotFound e){
                context.status(404);
                context.result(e.getMessage());
                Logger.log(e.getMessage(), LogLevel.ERROR);
            }
        });
        /* PATCH EXPENSES */
        app.patch("/expenses/{expenseId}/{statusImp}", context -> {
            System.out.println();
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
                    String message = "Expense ID{ "+expenseId+" } - may not be edited!!!";
                    context.result(message);
                    Logger.log(message, LogLevel.INFO);
                }else{
                    expenseService.patchExpenseStatus(expense);
                    context.result("Updated Expense Info!!!");
                    context.status(200);
                    Logger.log("Called PATCH employee route!!", LogLevel.INFO);
                }
            }catch(EmployeeIdException e){
                context.status(404);
                context.result(e.getMessage());
                Logger.log(e.getMessage(), LogLevel.ERROR);
            }
        });
        /* DELETE EXPENSES */
        app.delete("/expenses/{expenseId}", context -> {
            int expenseId = Integer.parseInt(context.pathParam("expenseId"));
            try{
                String expenseJSON = gson.toJson(expenseService.getExpenseById(expenseId));
                Expense check = expenseService.getExpenseById(expenseId);
                if(!Objects.equals(check.getStatus(), "Pending")){
                    context.status(403);
                    String message = "Expense ID{ "+expenseId+" } - is non-pending and may not be deleted!!!";
                    context.result(message);
                    Logger.log(message, LogLevel.INFO);
                }else{
                    expenseService.removeExpenseById(expenseId);
                    String message = "Deleted Expense ID{"+expenseId+"}!!!";
                    context.result(message);
                    Logger.log(message, LogLevel.INFO);
                    context.status(200);
                    Logger.log("Called DELETE expense route!!",LogLevel.INFO);
                }
            }catch(ResourceNotFound e){
                context.status(404);
                String message = "Expense ID{ "+expenseId+" } - not found!!!";
                context.result(message);
                Logger.log(message, LogLevel.INFO);
                Logger.log(e.getMessage(), LogLevel.ERROR);
            }
        });

        // start
        app.start(5000);
    }
}
