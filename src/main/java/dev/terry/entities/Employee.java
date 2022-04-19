package dev.terry.entities;

public class Employee{
    // fields
    private String empId;
    private String firstname;
    private String lastname;
    private String keyword;

    // no args constructor
    public Employee(){
    }

    // constructor
    public Employee(String empId, String firstname, String lastname, String keyword){
        this.empId = empId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.keyword = keyword;
    }

    // getters
    public String getEmpId() {
        return this.empId;
    }
    public String getFirstname() {
        return this.firstname;
    }
    public String getLastname() {
        return this.lastname;
    }
    public String getKeyword() {
        return this.keyword;
    }

    // setters
    public void setEmpId(String empId) {
        this.empId = empId;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "Employee{\n" +
                "\temp_id=" + this.empId +"\n"+
                "\tfirstname=" + this.firstname +"\n"+
                "\tlastname=" + this.lastname +"\n"+
                "\tpassword=" + this.keyword +"\n"+
                '}';
    }
}
