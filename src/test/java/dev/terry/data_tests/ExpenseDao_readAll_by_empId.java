package dev.terry.data_tests;

import dev.terry.data.ExpenseDao;
import dev.terry.data.ExpenseDaoImpl;
import dev.terry.entities.Expense;
import dev.terry.utilities.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpenseDao_readAll_by_empId{
    private static ExpenseDao expenseDao = new ExpenseDaoImpl();
	String empId = "928F3A6C9A6E5BABDF5B5CFFEA476868";
    @Test
    void can_read_expense_row_by_id(){
        // read the id from readEmployee obj.
        List<Expense> allExpensesByEmpId = expenseDao.readAllExpensesByEmpId(empId);
        System.out.println(allExpensesByEmpId.toString());

        int allExpensesCount = allExpensesByEmpId.size();

        Assertions.assertTrue(allExpensesCount != 0);
    }
}
