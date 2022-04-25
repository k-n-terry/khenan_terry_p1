package dev.terry.entities;

public class Employee{
    // fields
    private String empId;
    private String firstname;
    private String lastname;
    private String passphrase;

    // no args constructor
    public Employee(){
    }

    // constructor
    public Employee(String empId, String firstname, String lastname, String passphrase){
        this.empId = empId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.passphrase = passphrase;
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
    public String getPassphrase() {
        return this.passphrase;
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
    public void setPassphrase(String passphrase) {
        this.passphrase = passphrase;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId:" + this.empId +","+
                "firstname:" + this.firstname +","+
                "lastname:" + this.lastname +","+
                "passphrase:" + this.passphrase +
                '}';
    }
}
