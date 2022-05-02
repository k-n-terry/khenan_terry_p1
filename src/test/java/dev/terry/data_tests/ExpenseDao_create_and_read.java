package dev.terry.data_tests;

import dev.terry.data.ExpenseDao;
import dev.terry.data.ExpenseDaoImpl;
import dev.terry.entities.Expense;
import dev.terry.utilities.UniqueIdMD5;
import org.junit.jupiter.api.*;

import java.util.Random;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ExpenseDao_create_and_read {
    private static ExpenseDao expenseDao = new ExpenseDaoImpl();
    private static Expense savedSpendMeDao = new Expense();

    // theoretical fields
    String expnsId = "default";
    String empId = "928F3A6C9A6E5BABDF5B5CFFEA476868";
    String expnsLabel = "Lightsaber Replica";
    double expnsAmount = 299.99;
    String status = "Pending";

    @Test
    @Order(1)
    void can_create_expense_row_for_employee(){
        // Expense obj.
        Expense spendMe = new Expense();
        spendMe.setExpenseId(expnsId);
        spendMe.setEmpId(empId);
        spendMe.setLastname(ln);
        spendMe.setRegistry(ry);

        // call .createEmployee()
        Employee intelliJayDao = employeeDao.createEmployee(intelliJay);

        // save johnSmithDao
        ExpenseDao_create_and_read.savedIntelliJayDao = intelliJayDao;

        // set theoretical ID value
        UniqueIdMD5 uniqueId = new UniqueIdMD5(intelliJay);
        intelliJay.setEmpId(uniqueId.makeUniqueId());

        System.out.println(intelliJay.getEmpId());

        // check correct obj. creation
        Assertions.assertEquals(intelliJay.getEmpId(), intelliJayDao.getEmpId());
        Assertions.assertEquals(intelliJay.getFirstname(), intelliJayDao.getFirstname());
        Assertions.assertEquals(intelliJay.getLastname(), intelliJayDao.getLastname());
        Assertions.assertEquals(intelliJay.getRegistry(), intelliJayDao.getRegistry());
    }
    @Test
    @Order(2)
    void can_read_expense_row_by_id(){
        // read the id from readEmployee obj.
        Employee readEmployee = employeeDao.readEmployeeById(savedIntelliJayDao.getEmpId());
        System.out.println(readEmployee.toString());

        Assertions.assertEquals(savedIntelliJayDao.getEmpId(), readEmployee.getEmpId());
    }
}
