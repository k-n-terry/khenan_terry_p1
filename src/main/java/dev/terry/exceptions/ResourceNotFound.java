package dev.terry.exceptions;

public class ResourceNotFound extends RuntimeException{
    public ResourceNotFound(String empId){
        super("Entry for emp_id{ "+empId+" } was not found.");
    }
}
