package Stream;

public class Department {
    private String name;
    private int avgSalary;

    public Department(String name, int avgSalary) {
        this.name = name;
        this.avgSalary = avgSalary;
    }

    public String getName() {
        return name;
    }

    public int getAvgSalary() {
        return avgSalary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvgSalary(int avgSalary) {
        this.avgSalary = avgSalary;
    }
}
