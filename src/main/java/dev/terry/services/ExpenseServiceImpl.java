package dev.terry.services;

import dev.terry.data.ExpenseDao;
import dev.terry.entities.Expense;
import dev.terry.utilities.List;

public class ExpenseServiceImpl implements ExpenseService{
	private ExpenseDao expenseDao;

    public ExpenseServiceImpl(ExpenseDao expenseDao){
        this.expenseDao = expenseDao;
    }
    @Override
    public Expense registerExpense(Expense expense){
        return this.expenseDao.createExpense(expense);
    }
    @Override
    public Expense getExpenseById(int expenseId){
        return this.expenseDao.readExpenseById(expenseId);
    }
    @Override
    public List<Expense> expenseRegistry(){
        return this.expenseDao.readAllExpenses();
    }
    @Override
    public List<Expense> expenseRegistryByEmpId(String empId){
        return this.expenseDao.readAllExpensesByEmpId(empId);
    }
    @Override
    public List<Expense> expenseRegistryByStatus(String status){
        return this.expenseDao.readAllExpensesByStatus(status);
    }
    @Override
    public Expense updateExpenseFields(Expense expense){
        return this.expenseDao.updateExpense(expense);
    }
    @Override
    public Expense patchExpenseStatus(Expense expense){
        return this.expenseDao.updateExpenseStatus(expense);
    }
    @Override
    public boolean removeExpenseById(int expensId){
        boolean result = this.expenseDao.deleteExpenseById(expensId);
        return result;
    }
}
