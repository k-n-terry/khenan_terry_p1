package dev.terry.exceptions;

public class ExpenseIdException extends RuntimeException{
    public ExpenseIdException(int expenseId){
        super("ExpenseID{"+expenseId+"} was not found in database.");
    }
}
