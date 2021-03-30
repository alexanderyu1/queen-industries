import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {
    private int tier;
    public double bonus;
    public List<Employee> reports;

    public Manager(double bonus, ArrayList reports, double salary, String name, String department, String title) {
        super(salary, name, department, title);
        this.bonus = bonus;
        this.reports = reports;
        this.tier = 2;
    }

}
