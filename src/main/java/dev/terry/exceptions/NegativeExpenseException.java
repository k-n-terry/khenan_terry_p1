package dev.terry.exceptions;

public class NegativeExpenseException extends RuntimeException{
    public NegativeExpenseException(){
        super("Expense amounts may not be negative.");
    }
}
