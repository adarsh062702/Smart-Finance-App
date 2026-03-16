public class FinancialHealthService {

    public int calculateHealthScore(User user){

        double income = user.getMonthlyIncome();
        double expenses = 0;

        if(income <= 0){
            return 0;
        }

        double savingsRate = (income - expenses) / income;
        double expenseRatio = expenses / income;

        int score = 0;

        //Saving rate (30 points)
        if(savingsRate >= 0.30){
            score += 30;
        } else if (savingsRate >= 0.20) {
            score += 20;
        } else if (savingsRate >= 0.10) {
            score += 10;
        }

        //budget Discipline (20 point)
        double budget = user.getMonthlyBudget();

        if (budget > 0 && expenses <= budget){
            score += 20;
        }

        //income stability(20 points)
        if(income > 80000){
            score += 20;
        }else if(income > 40000){
            score += 15;
        }else if(income > 20000){
            score += 10;
        }

        //expense ratio stability(30 points)

        if (expenseRatio <= 0.50){
            score += 30;
        } else if (expenseRatio <= 0.70) {
            score += 20;
        } else if (expenseRatio <= 0.90) {
            score += 10;
        }
            return score;
    }

    public void printHealthReport(User user){

        double income = user.getMonthlyIncome();

        if (income <= 0){
            System.out.println("Please set your monthly income first");
            return;
        }

        int score = calculateHealthScore(user);

        System.out.println("\n FINANCIAL HEALTH REPORT");
        System.out.println("Income: ₹" + format(user.getMonthlyIncome()));
        System.out.println("Total Expenses: ₹" + format(getTotalExpenses(user)));
        System.out.println("Saving Rate: " + String.format("%.1f%%", getSvaingsRate(user) * 100));
        System.out.println("---------------------------------------------");
        System.out.println("Your Financial Health Score: " + score + " / 100");

        if (score >= 80){
            System.out.println("🟢 Excellent financial health. you are investment ready.");
        } else if (score >= 60) {
            System.out.println("🟡 Moderate health. Improve savings before high-risk investing.");
        }else {
            System.out.println("🔴 Weak financial health. focus on stabilizing financial first.");
        }
    }

    // Emergency fund

    public void printEmergencyFundStatus(User user){

        double income = user.getMonthlyIncome();
        double expenses = getTotalExpenses(user);

        if(income <= 0){
            System.out.println("\n Please set your income first.");
            return;
        }
        if (expenses <= 0){
            System.out.println("\n No expenses recorded yet.");
            return;
        }
        double requiredFund = expenses * 6;
        double monthlySavings = income - expenses;

        System.out.println("\n EMERGENCY FUND STATUS");
        System.out.println("Monthly Expenses: ₹" + format(expenses));
        System.out.println("Required 6-Month fund: ₹" + format(requiredFund));

        if (monthlySavings <= 0){
            System.out.println("❌ You are not saving enough to build an emergency fund.");
            return;
        }

        double monthsToBuild = requiredFund / monthlySavings;

        System.out.println("Monthly Saving: ₹" + format(monthlySavings));
        System.out.println("Estimated Months to build fund: " + String.format("%.1f", monthsToBuild));

        if (monthsToBuild <= 12){
            System.out.println("🟢 Excellent Trajectory.");
        } else if (monthsToBuild <= 24) {
            System.out.println("🟡 Moderate. Try increasing savings.");
        }else {
            System.out.println("🔴 Slow progress. reduce expenses or increase income.");
        }

    }
    private double getTotalExpenses(User user){
        double total = 0;
        for(Expense e : user.getExpenses()){
            total += e.getAmount();
        }
        return total;
    }
    private double getSvaingsRate(User user){
        double income = user.getMonthlyIncome();
        if(income <= 0) return  0;
        return (income - getTotalExpenses(user)) / income;
    }
    private String format(double value){
        return String.format("%.2f", value);
    }
}
