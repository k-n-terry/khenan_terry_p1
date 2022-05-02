package dev.terry.data_tests;

import dev.terry.data.ExpenseDao;
import dev.terry.data.ExpenseDaoImpl;
import dev.terry.entities.Expense;
import org.junit.jupiter.api.*;

import java.util.Random;

public class ExpenseDao_update {
    private static ExpenseDao expenseDao = new ExpenseDaoImpl();
	
	private Random r = new Random();
    Integer num = r.nextInt(999);
    
    // theoretical fields
    int expnsId = 2;
    String empId = "04B89AEACEB4A43EE5DAB3ACA2B15DD2";
    String expnsLabel = "Updated Expense From IntelliJ";
    Double expnsAmount = r.nextDouble()*100;
    String status;

    @Test
    void can_update_expense_row() {
        // Employee obj.
        Expense updateMe = new Expense();
        updateMe.setExpenseId(expnsId);
        updateMe.setEmpId(empId);
        updateMe.setExpenseLabel(expnsLabel);
        updateMe.setExpenseAmount(expnsAmount);
        if (num % (2) == 0){
            status = "Approved";
        }else{
            status = "Denied";
        }
        System.out.println(status);
        updateMe.setStatus(status);

        // call .updateExpense method
        Expense updateMeDao = expenseDao.updateExpense(updateMe);

        // check correct obj. update
        Assertions.assertEquals(updateMe.getExpenseId(), updateMeDao.getExpenseId());
        Assertions.assertEquals(updateMe.getEmpId(), updateMeDao.getEmpId());
        Assertions.assertEquals(updateMe.getExpenseLabel(), updateMeDao.getExpenseLabel());
        Assertions.assertEquals(updateMe.getExpenseAmount(), updateMeDao.getExpenseAmount());
        Assertions.assertEquals(updateMe.getStatus(), updateMeDao.getStatus());
    }
}
