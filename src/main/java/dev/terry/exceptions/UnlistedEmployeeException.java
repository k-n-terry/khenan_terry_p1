package dev.terry.exceptions;

public class UnlistedEmployeeException extends RuntimeException{
    public UnlistedEmployeeException(String empId){
        super("EmployeeID{"+empId+"} was found, but expenses may not be submitted at this time.");
    }
}
