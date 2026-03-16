import java.io.Serializable;

public class Expense implements Serializable{

    private static final long serialVersionUID = 1L;

    private double amount;
    private String category;
    private String description;

    public Expense(double amount, String category, String description){
        this.amount = amount;
        this.category = category;
        this.description = description;
    }
    public double getAmount(){
        return amount;
    }
    public String getCategory(){
        return category;
    }
    public String getDescription(){
        return description;
    }
}
