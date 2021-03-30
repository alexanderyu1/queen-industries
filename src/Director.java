import java.util.ArrayList;

public class Director extends Manager {
    private int tier;
    public double stockShares;

    public Director(double stockShares, double bonus, ArrayList reports, double salary, String name, String department, String title) {
        super(bonus, reports, salary, name, department, title);
        this.stockShares = stockShares;
        this.tier = 3;
    }
}
