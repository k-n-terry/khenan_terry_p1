package dev.terry.services;

import dev.terry.entities.Expense;
import dev.terry.utilities.List;

public interface ExpenseService{
	Expense registerExpense(Expense expense);

    Expense getExpenseById(int expenseId);

    List<Expense> expenseRegistry();
    List<Expense> expenseRegistryByEmpId(String empId);
    List<Expense> expenseRegistryByStatus(String status);

    Expense updateExpenseFields(Expense expense);

    Expense patchExpenseStatus(Expense expense);

    boolean removeExpenseById(int expensId);
}
