package dev.terry.data_tests;

import dev.terry.data.ExpenseDao;
import dev.terry.data.ExpenseDaoImpl;
import dev.terry.entities.Expense;
import dev.terry.utilities.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpenseDao_readAll{
    private static ExpenseDao expenseDao = new ExpenseDaoImpl();

    @Test
    void can_read_expense_row_by_id(){
        // read the id from readEmployee obj.
        List<Expense> allExpenses = expenseDao.readAllExpenses();
        System.out.println(allExpenses.toString());

        int allExpensesCount = allExpenses.size();

        Assertions.assertTrue(allExpensesCount != 0);
    }
}
