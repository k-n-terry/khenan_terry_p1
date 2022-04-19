//package dev.terry.exceptions_test;
//
//import dev.terry.data.EmployeeDao;
//import dev.terry.data.EmployeeDaoImpl;
//import dev.terry.entities.Employee;
//import dev.terry.exceptions.ResourceNotFound;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//
//public class ResourceNotFound_throws{
//    static EmployeeDao johnSmithDao = new EmployeeDaoImpl();
//    String good_id = "c577e4c72920997edbb6653e23d382b7"; // valid MD5 id
//    String bad_id = "bad_id";
//
//    @Test
//    public void mismatch_throws_exception(){
//        // try to find emp_id "bad_id" in database
//        Assertions.assertThrows(ResourceNotFound.class, ()->{
//            johnSmithDao.readEmployeeById(bad_id);
//        });
//    }
//}
