import java.util.ArrayList;
import java.util.List;

public class Director extends Manager {
    public int tier;
    public double stockShares;

    public Director(double salary, double stockShares, double bonus, String name, String department, String title, ArrayList reports, int tier) throws Exception {
        super(salary, bonus, name, department, title, reports, tier);
        this.stockShares = stockShares;
        this.tier = tier;
    }

    @Override
    public int getTier() {
        return tier;
    }
}
