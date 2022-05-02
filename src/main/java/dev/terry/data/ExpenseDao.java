package dev.terry.data;

import dev.terry.entities.Expense;
import dev.terry.utilities.List;

public interface ExpenseDao{
    // Create
    Expense createExpense(Expense expense);
    // Read
    Expense readExpenseById(int expenseId);
    List<Expense> readAllExpenses();
    List<Expense> readAllExpensesByEmpId(String empId);
    // Update
    Expense updateExpense(Expense expense);
    Expense updateExpenseStatus(Expense expense);
    // Delete
    // Expense records may not be deleted in compliance with Project 1 guidelines.
    boolean deleteExpenseById(int expenseId);
}
