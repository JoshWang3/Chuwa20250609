public class App {
    public static void main(String[] args) {
        //Department and Address objects in Heap
        //dept and addr are reference variables in Stack
        Department dept = new Department("Engineering", 01);
        Address addr = new Address("123 ABC", "DEF Rd", "CA", "95050");
        
        //Employeee object in Heap
        //e1 and e2 are reference variables in Stack
        Employee e1 = new Employee("Amy Bill", "03-04-1990", dept, 123456789L, addr);
        Employee e2 = new Employee("Bill Carlson", "04-05-1990", dept, 234567891L, addr);

        //Print Employee info by using static method
        // System.out.println(e1);
        // System.out.println(e2);
        Employee.printEmployeeInfo(e1);
        Employee.printEmployeeInfo(e2);

        //Print Employee count number by using static variable EmployeeCount
        System.out.println("Total number of employees: " + Employee.getEmployeeCount());
        
    }
    
}
