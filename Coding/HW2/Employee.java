class Employee{
    private String name;
    private String dateOfBirth;
    Department dept;
    private Long ssn;
    private Address address;

    //Static variables
    private static int employeeCount = 0;

    public Employee(){}
    public Employee(String name, String dateOfBirth, Department dept, Long ssn, Address addr){
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.dept = dept;
        this.address = addr;
        this.ssn = ssn;
        employeeCount++;
    }   

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public Department getDept() {
        return dept;
    }

    public String getName() {
        return name;
    }

    public Long getSsn() {
        return ssn;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public void setName(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        // System.out.println("Employer: " + name);
        // System.out.println("Date of Birth: " + dateOfBirth);
        // System.out.println("Department: " + dept.getDeptName());
        // System.out.println("Social Security: " + ssn);
        // System.out.println("Address: " + address);

        String str = "Employer: " + name + "\n" 
                    + "Date of Birth: " + dateOfBirth + "\n"
                    + "Department: " + dept.getDeptName() + "\n"
                    + "Social Security: " + ssn + "\n"
                    + "Address: " + address;
        return str;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        Employee e1 = (Employee) obj;
        if(name.equals(e1.getName()) && 
            dateOfBirth.equals(e1.getDateOfBirth()) && 
            ssn.equals(e1.getSsn())) return true;

        return false;
    }

    public static void printEmployeeInfo(Employee e){
        System.out.println(e);
    }

    public static int getEmployeeCount() {
        return employeeCount;
    }
    
}