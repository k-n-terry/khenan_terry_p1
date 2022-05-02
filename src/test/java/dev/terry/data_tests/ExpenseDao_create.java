package dev.terry.data_tests;

import dev.terry.data.ExpenseDao;
import dev.terry.data.ExpenseDaoImpl;
import dev.terry.entities.Expense;
import org.junit.jupiter.api.*;

public class ExpenseDao_create{
    private static ExpenseDao expenseDao = new ExpenseDaoImpl();

    // theoretical fields
    String empId = "928F3A6C9A6E5BABDF5B5CFFEA476868";
    String expnsLabel = "Lightsaber Replica";
    double expnsAmount = 299.99;
    String status = "Pending";

    @Test
    void can_create_expense_row_for_employee(){
        // Expense obj.
        Expense spendMe = new Expense();
        spendMe.setEmpId(empId);
        spendMe.setExpenseLabel(expnsLabel);
        spendMe.setExpenseAmount(expnsAmount);
        spendMe.setStatus(status);

        // call .createExpense()
        Expense spendMeDao = expenseDao.createExpense(spendMe);

        // check correct obj. creation
        Assertions.assertEquals(spendMe.getExpenseId(), spendMeDao.getExpenseId());
        Assertions.assertEquals(spendMe.getEmpId(), spendMeDao.getEmpId());
        Assertions.assertEquals(spendMe.getExpenseLabel(), spendMeDao.getExpenseLabel());
        Assertions.assertEquals(spendMe.getExpenseAmount(), spendMeDao.getExpenseAmount());
        Assertions.assertEquals(spendMe.getStatus(), spendMeDao.getStatus());
    }
}
