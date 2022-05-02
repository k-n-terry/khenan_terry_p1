package dev.terry.entities;

public class Expense{
    // fields
    private int expenseId;
    private String empId;
    private String expenseLabel;
    private double expenseAmount;
    private String status;

    // no args constructor
    public Expense(){
    }

    // constructor
    public Expense(int expenseId, String empId, String expenseLabel, double expenseAmount, String status){
        this.expenseId = expenseId;
        this.empId = empId;
        this.expenseLabel = expenseLabel;
        this.expenseAmount = expenseAmount;
        this.status = status;
    }

    // getters
    public int getExpenseId(){
        return this.expenseId;
    }
    public String getEmpId(){
        return this.empId;
    }
    public String getExpenseLabel(){
        return this.expenseLabel;
    }
    public double getExpenseAmount(){
        return this.expenseAmount;
    }
    public String getStatus(){
        return this.status;
    }

    // setters
    public void setExpenseId(int expenseId){
        this.expenseId = expenseId;
    }
    public void setEmpId(String empId){
        this.empId = empId;
    }
    public void setExpenseLabel(String expenseLabel){
        this.expenseLabel = expenseLabel;
    }
    public void setExpenseAmount(double expenseAmount){
        this.expenseAmount = expenseAmount;
    }
    public void setStatus(String status){
        this.status = status;
    }

    @Override
    public String toString() {
        return "Expense{" +
				"expenseId:"+this.expenseId+","+
                "empId:" + this.empId +","+
                "expenseLabel:" + this.expenseLabel +","+
                "expenseAmount:" + this.expenseAmount +","+
                "status:" + this.status +
                '}';
    }
}
