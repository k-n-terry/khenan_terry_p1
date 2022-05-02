package dev.terry.exceptions;

public class ExpensePersistenceException extends RuntimeException{
    public ExpensePersistenceException(){
        super("Expenses may not be deleted - or edited after approval/denial.");
    }
}
