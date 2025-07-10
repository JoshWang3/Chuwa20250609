public class Employee {
    private String name;
    private String dateOfBirth;
    private Deparment department;
    private String SSN;
    private Address address;

    static int EmployeeCount = 0;

    public class Deparment {
        private String departmentName;

        public Deparment(String departmentName) {
            this.departmentName = departmentName;
        }

        public String getDepartmentName() {
            return departmentName;
        }
    }

    public class Address {
        private String street;
        private String city;
        private String state;
        private String zipCode;

        public Address(String street, String city, String state, String zipCode) {
            this.street = street;
            this.city = city;
            this.state = state;
            this.zipCode = zipCode;
        }

        public String getStreet() {
            return street;
        }

        public String getCity() {
            return city;
        }

        public String getState() {
            return state;
        }

        public String getZipCode() {
            return zipCode;
        }
    }

    public Employee(String name, String dateOfBirth, String SSN, String departmentName, String street, String city, String state, String zipCode) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.SSN = SSN;
        this.department = new Deparment(departmentName);
        this.address = new Address(street, city, state, zipCode);
        EmployeeCount++;
    }

    public String toString(){
        return " {" +
                "name='" + name + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", department=" + (department != null ? department.getDepartmentName() : "") +
                ", SSN='" + SSN + '\'' +
                ", address=" + (address != null ? address.getStreet() + ", " + address.getCity() + ", " + address.getState() + " " + address.getZipCode() : "") +
                " }";
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Employee)) return false;
        Employee other = (Employee) obj;
        return name.equals(other.name) &&
               dateOfBirth.equals(other.dateOfBirth) &&
               (department != null ? department.getDepartmentName().equals(other.department.getDepartmentName()) : other.department == null) &&
               SSN.equals(other.SSN) &&
               (address != null ? address.getStreet().equals(other.address.getStreet()) &&
                                 address.getCity().equals(other.address.getCity()) &&
                                 address.getState().equals(other.address.getState()) &&
                                 address.getZipCode().equals(other.address.getZipCode()) : other.address == null);
    }

    public static void main(String[] args) {
        // Create two Employee objects
        System.out.println("Employee Count: " + Employee.EmployeeCount);
        Employee Alice = new Employee("Alice",
                "1990-01-01",
                "123-45-6789",
                "Engineering",
                "123 Main St",
                "Springfield",
                "IL",
                "62701");
        System.out.println("Alice created: " + Alice);
        System.out.println("Employee Count: " + Employee.EmployeeCount);
        Employee Bob = new Employee("Bob",
                "1992-02-02",
                "987-65-4321",
                "Marketing",
                "456 Elm St",
                "Springfield",
                "IL",
                "62702");
        System.out.println("Bob created: " + Bob);
        System.out.println("Employee Count: " + Employee.EmployeeCount);

        // Use Java relection utilities to demostrate how the two objects are allocated to JVM memory

        // HashCode and Memory Address
        System.out.println("Alice HashCode: " + Alice.hashCode());
        System.out.println("Bob HashCode: " + Bob.hashCode());
        System.out.println("Alice Memory Address: " + System.identityHashCode(Alice));
        System.out.println("Bob Memory Address: " + System.identityHashCode(Bob));




    }
}