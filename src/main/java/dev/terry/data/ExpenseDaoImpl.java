package dev.terry.data;

import dev.terry.entities.Expense;
import dev.terry.exceptions.NegativeExpenseException;
import dev.terry.utilities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExpenseDaoImpl implements ExpenseDao{
	// CREATE
    @Override
    public Expense createExpense(Expense expense) throws NegativeExpenseException{
        try {
            // connect to database: "khenan_terry_p1"
            Connection conn = ConnectUtil.createConnect();

            // SQL insertion into table: "expense"
            String sql = "insert into expense values(default,?,?,?,?);";
            PreparedStatement ps = conn.prepareStatement(sql);

            // string parameters
            ps.setString(1, expense.getEmpId());
            ps.setString(2, expense.getExpenseLabel());
            ps.setDouble(3, expense.getExpenseAmount());
            ps.setString(4, expense.getStatus());

            // throws exception when expenseAmount is negative
            if(expense.getExpenseAmount()>=0) {
                ps.execute();
                return expense;
            }else{
                throw new NegativeExpenseException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }
    // READ
    @Override
    public Expense readExpenseById(int expenseId){
        try{
            // connect to database: "khenan_terry_p1"
            Connection conn = ConnectUtil.createConnect();

            // SQL selection from table: "expense"
            String sql = "select * from expense where expenseId = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,expenseId);

            ResultSet rs = ps.executeQuery();

            rs.next();

            // assign rs-results to fields in employee object
            Expense expense = new Expense();
            // set fields
            expense.setExpenseId(rs.getInt("expenseId"));
            expense.setEmpId(rs.getString("empId"));
            expense.setExpenseLabel(rs.getString("expenseLabel"));
            expense.setExpenseAmount(rs.getDouble("expenseAmount"));
            expense.setStatus(rs.getString("status"));

            return expense;
        }catch(SQLException e){
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }
    @Override
    public List<Expense> readAllExpenses(){
        try{
            // connect to database: "khenan_terry_p1"
            Connection conn = ConnectUtil.createConnect();

            // SQL selection from table: "employee"
            String sql = "select * from expense;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            List<Expense> expenses = new ArrayList();

            // while the result-set has a new line:
            while(rs.next()){
                // assign rs-results to fields in employee object
                Expense expense = new Expense();
                // set fields
                expense.setExpenseId(rs.getInt("expenseId"));
                expense.setEmpId(rs.getString("empId"));
                expense.setExpenseLabel(rs.getString("expenseLabel"));
                expense.setExpenseAmount(rs.getDouble("expenseAmount"));
                expense.setStatus(rs.getString("status"));
                expenses.add(expense);
            }
        return expenses;
        }catch(SQLException e){
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }
    @Override
    public List<Expense> readAllExpensesByEmpId(String empId){
        try{
            // connect to database: "khenan_terry_p1"
            Connection conn = ConnectUtil.createConnect();

            // SQL selection from table: "employee"
            String sql = "select * from expense where empId=?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,empId);

            ResultSet rs = ps.executeQuery();

            List<Expense> expenses = new ArrayList();

            // while the result-set has a new line:
            while(rs.next()){
                // assign rs-results to fields in employee object
                Expense expense = new Expense();
                // set fields
                expense.setExpenseId(rs.getInt("expenseId"));
                expense.setEmpId(rs.getString("empId"));
                expense.setExpenseLabel(rs.getString("expenseLabel"));
                expense.setExpenseAmount(rs.getDouble("expenseAmount"));
                expense.setStatus(rs.getString("status"));
                expenses.add(expense);
            }
        return expenses;
        }catch(SQLException e){
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }
    // UPDATE
    @Override
    public Expense updateExpense(Expense expense){
        try{
            // connect to database: "khenan_terry_p1"
            Connection conn = ConnectUtil.createConnect();

            // SQL insertion into table: "employee"
            String sql = "update expense set expenseLabel=?, expenseAmount=?, status=? where expenseId=?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            // string parameters
            ps.setString(1, expense.getExpenseLabel());
            ps.setDouble(2, expense.getExpenseAmount());
            ps.setString(3, expense.getStatus());
            ps.setInt(4, expense.getExpenseId());

            ps.execute();

            return expense;
        }catch(SQLException e){
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }
    @Override
    public Expense updateExpenseStatus(Expense expense){
        try{
            // connect to database: "khenan_terry_p1"
            Connection conn = ConnectUtil.createConnect();

            // SQL insertion into table: "employee"
            String sql = "update expense set status=? where expenseId=?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            // string parameters
            ps.setString(1, expense.getStatus());
            ps.setInt(2, expense.getExpenseId());

            ps.execute();

            return expense;
        }catch(SQLException e){
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return null;
        }
    }
    @Override
    public boolean deleteExpenseById(int expenseId) {
        try{
            // connect to database: "khenan_terry_p1"
            Connection conn = ConnectUtil.createConnect();

            // SQL
            String sql = "delete from expense where expenseId=?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            // string parameters
            ps.setInt(1, expenseId);

            ps.execute();

            return true;
        }catch(SQLException e){
            e.printStackTrace();
            Logger.log(e.getMessage(), LogLevel.ERROR);
            return false;
        }
    }
}
