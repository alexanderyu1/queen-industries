public class Employee implements Comparable {
    public int tier;
    public double salary;
    public String name;
    public String department;
    public String title;

    public Employee(double salary, String name, String department, String title) {
        this.tier = 1;
        this.salary = salary;
        this.name = name;
        this.department = department;
        this.title = title;
    }

    @Override public int compareTo(Object o) {
        Employee employee = (Employee) o;
        if (this.tier > employee.tier) {
            return 1;
        }
        else if (this.tier < employee.tier) {
            return -1;
        }
        return 0;
    }
}
