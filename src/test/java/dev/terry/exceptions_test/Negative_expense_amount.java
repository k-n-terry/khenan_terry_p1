package dev.terry.exceptions_test;

import dev.terry.data.ExpenseDao;
import dev.terry.data.ExpenseDaoImpl;
import dev.terry.entities.Expense;
import dev.terry.exceptions.NegativeExpenseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Negative_expense_amount{
    private static ExpenseDao expenseDao = new ExpenseDaoImpl();
    String empId = "928F3A6C9A6E5BABDF5B5CFFEA476868";
    String expnsLabel = "Lightsaber Replica";
    double expnsAmount = -1;
    String status = "Pending";

    @Test
    public void throws_exception_on_condition(){
        Expense negTest = new Expense();
        negTest.setEmpId(empId);
        negTest.setExpenseLabel(expnsLabel);
        negTest.setExpenseAmount(expnsAmount);
        negTest.setStatus(status);

        Assertions.assertThrows(NegativeExpenseException.class, ()->{
            expenseDao.createExpense(negTest);
        });
    }
}