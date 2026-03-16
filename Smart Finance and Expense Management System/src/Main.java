public class Main{

    public static void main(String [] args){

        storageService storageService = new storageService();
        UserService userService = new UserService(storageService.loadUsers());
        boolean running = true;

        while(running){
            System.out.println("Smart Finance App");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("choose option: ");

            int choice = InputUtil.scanner.nextInt();
            InputUtil.scanner.nextLine();

            switch (choice){
                case 1:
                    System.out.print("Enter username: ");
                    String regUser = InputUtil.scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPass = InputUtil.scanner.nextLine();

                    if (userService.register(regUser, regPass)){
                        storageService.saveUsers(userService.getUsers());
                        System.out.println("✅Registration successful!");
                    }else{
                        System.out.println("❌ Username already exists");
                    }
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    String logUser = InputUtil.scanner.nextLine();
                    System.out.print("Enter password: ");
                    String logPass = InputUtil.scanner.nextLine();

                    User loggedInUser = userService.login(logUser,logPass);

                    if (loggedInUser != null){
                        System.out.println("✅ Login successful!");
                        runDashboard(loggedInUser, userService, storageService);
                    }else {
                        System.out.println("❌Invalid credentials");
                    }
                    break;

                case 3:
                    storageService.saveUsers(userService.getUsers());
                    running = false;
                    System.out.println("Exiting app...");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
    // Dashboard
    private static void runDashboard(
            User loggedInUser,
            UserService userService,
            storageService storageService){


        ExpenseService expenseService = new ExpenseService();
        AnalyticsService analyticsService = new AnalyticsService();
        FinancialHealthService financialHealthService = new FinancialHealthService();
        InvestmentRecommendationService investmentRecommendationService = new InvestmentRecommendationService();
        boolean loggedIn = true;

        while(loggedIn){
            System.out.println("\nDashboard");
            System.out.println("01. Add Expanse");
            System.out.println("02. View Expenses");
            System.out.println("03. Set / Update Monthly budget");
            System.out.println("04. Set / Update Monthly Income");
            System.out.println("05. View Category Summary");
            System.out.println("06. Check Budget Status");
            System.out.println("07. Financial Health Report");
            System.out.println("08. Emergency fund Status");
            System.out.println("09. Delete Expense");
            System.out.println("10. Update Expense");
            System.out.println("11. Search Expense by Amount (Binary Search)");
            System.out.println("12. Show Top 3 Expenses");
            System.out.println("13. Investment Recommendation");
            System.out.println("14. Delete My Account");
            System.out.println("15. Logout");
            System.out.print("choose option:");

            int expChoice = InputUtil.scanner.nextInt();
            InputUtil.scanner.nextLine();

            switch (expChoice){

                case 1:
                    System.out.print("Enter amount: ");
                    double amount = InputUtil.scanner.nextDouble();
                    InputUtil.scanner.nextLine();

                    System.out.print("Enter category: ");
                    String category = InputUtil.scanner.nextLine();

                    System.out.print("Enter description: ");
                    String description = InputUtil.scanner.nextLine();

                    expenseService.addExpense(
                            loggedInUser,
                            amount,
                            category,
                            description
                    );
                    storageService.saveUsers(userService.getUsers());

                    break;

                case 2:
                    expenseService.viewExpenses(loggedInUser);
                    break;

                case 3:
                    System.out.println("Current Budget: ₹" +
                            String.format("%.2f" ,loggedInUser.getMonthlyBudget()));

                    System.out.print("Enter Monthly Budget: ");
                    double budget = InputUtil.scanner.nextDouble();
                    InputUtil.scanner.nextLine();

                    if(budget < 0){
                        System.out.println("❌Budget cannot be negative.");
                        break;
                    }

                    loggedInUser.setMonthlyBudget(budget);
                    storageService.saveUsers(userService.getUsers());

                    System.out.println("✅ Budget set!");

                    if(loggedInUser.getMonthlyIncome() > 0 && budget > loggedInUser.getMonthlyIncome()){
                        System.out.println("Warning your budget exceeds your income.");
                    }
                    break;

                case 4:
                    System.out.println("current Income: ₹" + loggedInUser.getMonthlyIncome());
                    System.out.print("Enter monthly income: ");
                    double income = InputUtil.scanner.nextDouble();
                    InputUtil.scanner.nextLine();

                    if(income < 0){
                        System.out.println("❌ Income cannot be negative.");
                        break;
                    }
                    loggedInUser.setMonthlyIncome(income);
                    storageService.saveUsers(userService.getUsers());

                    System.out.println("✅Income update successfully!");
                    break;

                case 5:
                    analyticsService.printCategorySummary(loggedInUser);
                    break;

                case 6:
                    analyticsService.checkBudget(loggedInUser);
                    break;

                case 7:
                    financialHealthService.printHealthReport(loggedInUser);
                    break;

                case 8:
                    financialHealthService.printEmergencyFundStatus(loggedInUser);
                    break;

                case 9:
                    expenseService.viewExpenses(loggedInUser);
                    System.out.print("Enter expense number to delete");
                    int deleteIndex = InputUtil.scanner.nextInt();
                    InputUtil.scanner.nextLine();

                    expenseService.deleteExpense(loggedInUser,deleteIndex - 1);
                    storageService.saveUsers(userService.getUsers());
                    break;

                case 10:
                    expenseService.viewExpenses(loggedInUser);
                    System.out.print("Enter expense number to update: ");
                    int updateIndex = InputUtil.scanner.nextInt();
                    InputUtil.scanner.nextLine();

                    System.out.print("Enter new amount: ");
                    double newAmount = InputUtil.scanner.nextDouble();
                    InputUtil.scanner.nextLine();

                    System.out.print("Enter new category: ");
                    String newCategory = InputUtil.scanner.nextLine();

                    System.out.print("Enter new description: ");
                    String newDescription = InputUtil.scanner.nextLine();

                    expenseService.updateExpense(
                            loggedInUser,
                            updateIndex -1,
                            newAmount,
                            newCategory,
                            newDescription
                    );
                    storageService.saveUsers(userService.getUsers());
                    break;

                case 11:
                    System.out.print("Enter amount to search: ");
                    double searchAmount = InputUtil.scanner.nextDouble();
                    InputUtil.scanner.nextLine();

                    Expense result =
                            expenseService.binarySearchByAmount(loggedInUser,searchAmount);

                    if (result != null){
                        System.out.printf("Found: ₹%.2f | %s | %s%n",
                                result.getAmount(),
                                result.getCategory(),
                                result.getDescription());
                    }else {
                        System.out.println("No expense found.");
                    }
                    break;

                case 12:
                    analyticsService.printTopExpenses(loggedInUser);
                    break;

                case 13:
                    investmentRecommendationService.recommend(loggedInUser);
                    break;

                case 14:
                    System.out.print("Are you sure? (yes/no): ");
                    String confirm = InputUtil.scanner.nextLine();

                    if (confirm.equalsIgnoreCase("yes")){
                        userService.deleteUser(loggedInUser.getUsername());
                        storageService.saveUsers(userService.getUsers());
                        loggedIn = false;
                        System.out.println("🗑️Account deleted successfully.");
                    }
                    break;

                case 15:
                    storageService.saveUsers(userService.getUsers());
                    loggedIn = false;
                    System.out.println("🔓 Logged out successfully");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}