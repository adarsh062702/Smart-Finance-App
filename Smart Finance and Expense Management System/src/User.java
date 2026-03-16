import java.util.ArrayList;
import java.io.Serializable;

public class User implements Serializable{

    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private ArrayList<Expense> expenses;
    private double monthlyBudget;
    private double monthlyIncome;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        this.expenses = new ArrayList<>();
        this.monthlyBudget = 0;
        this.monthlyIncome = 0;
    }
    public void addExpense(Expense expense){
        expenses.add(expense);
    }
    public ArrayList<Expense>getExpenses(){
        return expenses;
    }
    public void setMonthlyBudget(double monthlyBudget){
        this.monthlyBudget = monthlyBudget;
    }
    public double getMonthlyBudget(){
        return monthlyBudget;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public double getMonthlyIncome(){
        return monthlyIncome;
    }
    public void setMonthlyIncome(double monthlyIncome){
        this.monthlyIncome = monthlyIncome;
    }
}
