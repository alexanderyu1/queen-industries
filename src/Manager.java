import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {
    public int tier;
    public double bonus;
    public List<Employee> reports;

    public Manager(double salary, double bonus, String name, String department, String title, ArrayList reports, int tier) throws Exception {
        super(salary, name, department, title);
        for(int i = 0; i < reports.size(); i++)
        {
            Employee employee = (Employee) reports.get(i);
            if(employee.tier >= this.tier) throw new Exception("ERROR: cannot supervise an Employee of an equal or greater tier.");
        }
        this.bonus = bonus;
        this.reports = reports;
        this.tier = tier;
    }

    public void hire(Employee employee) throws Exception {
        if (this.tier <= employee.tier) {
            throw new Exception("ERROR: cannot hire an Employee of an equal or greater tier.\n");
        }
        else {
            this.reports.add(employee);
            System.out.println("LOG: new Employee hired (" + employee.name + ", " + employee.department + ", " + employee.title + ")");
        }
    }

    public void fire(Employee employee) throws Exception {
        if (this.tier <= employee.tier) {
            throw new Exception("ERROR: cannot hire an Employee of an equal or greater tier.\n");
        }
        else if (!this.reports.contains(employee)) {
            throw new Exception("ERROR: cannot fire an Employee who is not a direct or indirect report.\n");
        }
        else {
            this.reports.remove(employee);
            System.out.println("LOG: new Employee fired (" + employee.name + ", " + employee.department + ", " + employee.title + ")");
        }
    }

    public void adjustSalary(int raise, Employee employee) throws Exception {
        if (this.reports.contains(employee)) {
            employee.salary += raise;
        }
        else {
            throw new Exception("ERROR: cannot alter salary of an Employee who is not a report.\n");
        }
    }
}
