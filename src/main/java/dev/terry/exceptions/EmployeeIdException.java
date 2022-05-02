package dev.terry.exceptions;

public class EmployeeIdException extends RuntimeException{
    public EmployeeIdException(String empId){
        super("EmployeeID{"+empId+"} was not found in database. ID must be 32 characters.");
    }
}
