import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class Employee {
    private String name;
    private String dob;
    private String department;
    private String ssn;
    private String addr;

    public Employee(String name, String dob, String department, String ssn, String addr) {
        this.name = name;
        this.dob = dob;
        this.department = department;
        this.ssn = ssn;
        this.addr = addr;
    }

    @Override
    public String toString() {
        return "Employee {" +
                "name='" + name + '\'' +
                ", dob='" + dob + '\'' +
                ", department='" + department + '\'' +
                ", ssn='" + ssn + '\'' +
                ", addr='" + addr + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        Employee e = (Employee) obj;
        return
                name.equals(e.name) &&
                dob.equals(e.dob) &&
                department.equals(e.department) &&
                ssn.equals(e.ssn) &&
                addr.equals(e.addr);
    }

    public static int calculateAge(String birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
        LocalDate dob = LocalDate.parse(birthDate, formatter);
        LocalDate today = LocalDate.now();
        return Period.between(dob, today).getYears();
    }

}
