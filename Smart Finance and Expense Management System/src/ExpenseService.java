import java.util.List;

public class ExpenseService {

    public void addExpense(User user, double amount, String category, String description) {
        Expense expense = new Expense(amount, category, description);
        user.addExpense(expense);
        System.out.println("✅ Expense added successfully!");
    }

    public void viewExpenses(User user) {

        List<Expense> expenses = user.getExpenses();

        if (expenses.isEmpty()) {
            System.out.println("No expense added yet");
            return;
        }
        int index = 1;
        for (Expense e : expenses) {
            System.out.printf("%d. ₹%.2f | %s | %s%n",
                    index++,
                    e.getAmount(),
                    e.getCategory(),
                    e.getDescription());
        }
    }
    public Expense binarySearchByAmount(User user, double target){

        List<Expense> expenses = user.getExpenses();
        expenses.sort((a,b) -> Double.compare(a.getAmount(), b.getAmount()));

        int left = 0;
        int right = expenses.size() - 1;

        while (left <= right){
            int mid = (left + right) / 2;
            double midValue = expenses.get(mid).getAmount();

            if (midValue == target)
                return expenses.get(mid);
            else if (midValue < target)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return null;
    }
    public void updateExpense(User user, int index, double amount, String category, String description){
        user.getExpenses().set(index, new Expense(amount,category,description));
        System.out.println("✅Expense successfully updated.");
    }
    public void deleteExpense(User user,int index){
        user.getExpenses().remove(index);
        System.out.println("🗑️Expense deleted.");
    }
}
