import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {
    public int tier;
    public double bonus;
    public List<Employee> reports;

    public Manager(double salary, double bonus, String name, String department, String title, ArrayList reports, int tier) throws Exception {
        super(salary, name, department, title);
        for (Object report : reports) {
            Employee employee = (Employee) report;
            if (employee.tier >= this.tier)
                throw new Exception("ERROR: cannot supervise an Employee of an equal or greater tier.");
        }
        this.bonus = bonus;
        this.reports = reports;
        this.tier = tier;
    }

    @Override
    public int getTier() {
        return tier;
    }

    public void hire(Employee employee) throws Exception {
        if (this.getTier() <= employee.getTier()) {
            throw new Exception("ERROR: cannot hire an Employee of an equal or greater tier.");
        }
        else {
            this.reports.add(employee);
            System.out.println("LOG: new Employee hired (" + employee.name + ", " + employee.department + ", " + employee.title + ")");
        }
    }

    public void fire(Employee employee) throws Exception {
        if (this.tier <= employee.tier) {
            throw new Exception("ERROR: cannot hire an Employee of an equal or greater tier.");
        }
        else if (!this.reports.contains(employee)) {
            throw new Exception("ERROR: cannot fire an Employee who is not a direct or indirect report.");
        }
        else {
            this.reports.remove(employee);
            System.out.println("LOG: existing Employee fired (" + employee.name + ", " + employee.department + ", " + employee.title + ")");
        }
    }

    public void fire(Manager employee) throws Exception {
        if (this.getTier() <= employee.getTier()) {
            throw new Exception("ERROR: cannot hire an Employee of an equal or greater tier.");
        }
        else if (!employee.department.equals(this.department)) {
            throw new Exception("ERROR: cannot fire an Employee who is not a direct or indirect report.");
        }
        else {
            this.reports.remove(employee);
            System.out.println("LOG: existing Employee fired (" + employee.name + ", " + employee.department + ", " + employee.title + ")");

            List<Employee> noManager = employee.reports;

            String reassignedLog = "";
            boolean reassigned = false;
            for (int i = 0; i < this.reports.size(); i++) {
                if (this.reports.get(i).department.equals(employee.department)) {
                    for (Employee employee2 : noManager) {
                        reassigned = true;
                        Manager reassignedEmployee = new Manager(employee2.salary, 0, employee2.name, employee2.department, employee2.title, new ArrayList<>(), Company.MANAGER);
                        this.reports.add(reassignedEmployee);
                        reassignedLog += employee2.name + ", " + employee2.department + ", " + employee2.title + ", ";
                    }
                }
            }
            if (reassigned) {
                System.out.println("LOG: reports re-assigned [" + reassignedLog.substring(0, reassignedLog.length() - 2) + "]");
            }
        }
    }

    public void adjustSalary(int raise, Employee employee) throws Exception {
        if (this.getTier() <= employee.getTier()) {
            throw new Exception("ERROR: cannot supervise an Employee of an equal or greater tier.");
        }
        boolean isReport = false;
        for (Employee employee1 : reports) {
            if (employee1.equals(employee)) isReport = true;
            if (employee1.getTier() == Company.MANAGER) {
                for (Employee employee2 : ((Manager) employee1).reports) {
                    if (employee2.equals(employee)) isReport = true;
                }
            }
        }
        if (isReport) {
            employee.salary += raise;
        }
        else {
            throw new Exception("ERROR: cannot alter salary of an Employee who is not a report.");
        }
    }
}
