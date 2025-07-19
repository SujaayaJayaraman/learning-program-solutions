import java.util.*;
public class financialforecasting {
	public static double calculateFutureValue(double currentValue, double growthRate, int years) {
        if (years == 0) {
            return currentValue;
        }
        return calculateFutureValue(currentValue, growthRate, years - 1) * (1 + growthRate);
    }
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        System.out.print("Enter current investment amount: ");
        double currentValue = sc.nextDouble();
        System.out.print("Enter annual growth rate: ");
        double annualGrowthRatePercent = sc.nextDouble();
        double growthRate = annualGrowthRatePercent / 100.0;
        System.out.print("Enter number of years: ");
        int years = sc.nextInt();
        double futureValue = calculateFutureValue(currentValue, growthRate, years);
        System.out.printf("\nProjected future value after %d years: Rs.%.2f\n", years, futureValue);
    }
}
