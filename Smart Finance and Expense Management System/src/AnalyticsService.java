import java.util.*;

public class AnalyticsService {

    public Map<String, Double> getCategoryTotals(User user) {
        Map<String, Double> totals = new HashMap<>();

        for (Expense e : user.getExpenses()) {
            totals.put(
                    e.getCategory(),
                    totals.getOrDefault(e.getCategory(), 0.0) + e.getAmount()
            );
        }
        return totals;
    }

    public double getTotalSpending(User user) {
        double total = 0;
        for (Expense e : user.getExpenses()) {
            total += e.getAmount();
        }
        return total;
    }
    public void printTopExpenses(User user){

        if (user.getExpenses().isEmpty()){
            System.out.println("No expenses found.");
            return;
        }

        PriorityQueue<Expense> maxHeap = new PriorityQueue<>((a, b) -> Double.compare(b.getAmount(),a.getAmount()));

        maxHeap.addAll(user.getExpenses());

        System.out.println("\nTOP 3 HIGHEST EXPENSES");

        for (int i = 0;i < 3 && !maxHeap.isEmpty();i++){
            Expense e = maxHeap.poll();
            System.out.printf("₹%.2f | %s | %s%n",
                    e.getAmount(),
                    e.getCategory(),
                    e.getDescription());
        }
    }

    public void checkBudget(User user) {
        double budget = user.getMonthlyBudget();

        if (budget <= 0) {
            System.out.println("ℹ No budget set.");
            return;
        }

        double spent = getTotalSpending(user);
        double percent = (spent / budget) * 100;

        if (percent >= 100) {
            System.out.println("🚨 Budget exceeded!");
        } else if (percent >= 80) {
            System.out.println("⚠ Warning: Used " + (int) percent + "% of budget");
        } else {
            System.out.println("✅ Budget OK: Used " + (int) percent + "%");
        }
    }

    // Aligned + Sorted Category Summary
    public void printCategorySummary(User user) {
        Map<String, Double> totals = getCategoryTotals(user);

        if (totals.isEmpty()) {
            System.out.println("No expenses yet.");
            return;
        }

        List<Map.Entry<String, Double>> entries =
                new ArrayList<>(totals.entrySet());

        // Sort by total amount (DESC)
        Collections.sort(entries, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(
                    Map.Entry<String, Double> e1,
                    Map.Entry<String, Double> e2) {
                return Double.compare(e2.getValue(), e1.getValue());
            }
        });

        System.out.println("\n-------------------- CATEGORY SUMMARY (SORTED) --------------------");
        System.out.printf("%-20s | %-12s%n", "CATEGORY", "TOTAL AMOUNT");
        System.out.println("------------------------------------------------------------------");

        for (Map.Entry<String, Double> entry : entries) {
            System.out.printf(
                    "%-20s | ₹%-10.2f%n",
                    entry.getKey(),
                    entry.getValue()
            );
        }
    }
}
