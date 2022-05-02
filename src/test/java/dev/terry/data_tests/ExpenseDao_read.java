package dev.terry.data_tests;

import dev.terry.data.ExpenseDao;
import dev.terry.data.ExpenseDaoImpl;
import dev.terry.entities.Expense;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpenseDao_read{
    private static ExpenseDao expenseDao = new ExpenseDaoImpl();

    @Test
    void can_read_expense_row_by_id(){
        // read the id from readExpense obj.
        Expense readExpense = expenseDao.readExpenseById(1);
        System.out.println(readExpense.toString());

        Assertions.assertEquals(1, readExpense.getExpenseId());
    }
}
