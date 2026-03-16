public class InvestmentRecommendationService {

    public void recommend(User user){

        FinancialHealthService financialHealthService = new FinancialHealthService();

        int healthScore = financialHealthService.calculateHealthScore(user);

        if (user.getMonthlyIncome() <= 0){
            System.out.println("Please set your income first.");
            return;
        }
        if (healthScore < 50){
            System.out.println("\nYour financial health is weak.");
            System.out.println("Focus on stabilizing income and building emergency fund first");
            return;
        }

        System.out.println("\nSelect You Risk Appetite:");
        System.out.println("1. Low Risk");
        System.out.println("2. Medium Risk");
        System.out.println("3. High Risk");
        System.out.print("Enter choice: ");

        int riskChoice = InputUtil.scanner.nextInt();
        InputUtil.scanner.nextLine();

        System.out.println("\nINVESTMENT RECOMMENDATION");

        switch (riskChoice){
            case 1:
                recommendLowRisk(user,healthScore);
                break;

            case 2:
                recommendMidRisk(user,healthScore);
                break;

            case 3:
                recommendHighRisk(user,healthScore);
                break;

            default:
                System.out.println("Invalid risk choice.");
        }
    }
    private void recommendLowRisk(User user,int healthScore){

        System.out.println("🟢Low Risk Strategy:");

        if (healthScore >= 70){
            System.out.println("- 40% Fixed Deposits");
            System.out.println("- 30% Government Bonds");
            System.out.println("- 20% Gold ETF");
            System.out.println("- 10% Liquid Mutual Fund");
        }else {
            System.out.println("- 60% Fixed Deposits");
            System.out.println("- 30% Gold");
            System.out.println("- 10% Savings Account");
        }
        System.out.println("focus on capital protection and stable return.");
    }
    private void recommendMidRisk(User user,int healthScore){

        System.out.println("🟡Medium risk Strategy:");

        if(healthScore >= 75){
            System.out.println("- 50% Index Fund (NIFTY/Sensex)");
            System.out.println("- 20% Gold ETF");
            System.out.println("- 20% Debt Funds");
            System.out.println("- 10% REITs");
        }else {
            System.out.println("- 40% Hybrid Mutual Funds");
            System.out.println("- 30% Gold");
            System.out.println("- 30% Fixed Income Instruments");
        }
        System.out.println("Balance growth with moderate volatility");
    }
    private void recommendHighRisk(User user,int healthScore) {

        System.out.println("🔴High risk Strategy:");

        if (healthScore >= 80) {
            System.out.println("- 60% Equity Stocks");
            System.out.println("- 20% Small Cap Funds");
            System.out.println("- 10% International ETFs.");
            System.out.println("- 10% Crypto / High-growth assets");
        } else {
            System.out.println("- 50% Large Cap Stocks");
            System.out.println("- 20% Index Funds");
            System.out.println("- 20% Gold");
            System.out.println("- 10% Cash Buffer");
        }
        System.out.println("Balance growth with moderate volatility");
    }
}
