import models.Address;
import models.Department;
import models.Employee;

import java.lang.reflect.*;
import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Employee staff1  = new Employee();
        Employee staff2  = new Employee();
        Employee staff3  = new Employee();

        staff3.setId(0);

        Address address1 = new Address();
        Address address2 = new Address();

        Department department1 = new Department();
        Department department2 = new Department();

        department1.setDirector(staff1);
        department2.setDirector(staff2);

        department1.setName("accountant");
        department2.setName("human resources");
        department1.setSize(18);
        department2.setSize(16);

        address1.setCity("Los Angeles");
        address2.setCity("New York");
        address1.setStreet("1234 abc ave");
        address2.setStreet("5678 def ave");
        address1.setZip("12345");
        address2.setZip("13678");
        address1.setCountry("UK");
        address2.setCountry("USA");
        address1.setState("CA");
        address2.setState("NY");

        staff1.setId(0);
        staff2.setId(22);
        staff1.setFirstname("Joe");
        staff1.setLastname("Smith");
        staff1.setAddress(address1);
        staff2.setFirstname("Jane");
        staff2.setLastname("Doe");
        staff2.setAddress(address2);
        staff1.setDateOfBirth("02/03/1988");
        staff2.setDateOfBirth("02/03/1998");
        staff1.setDepartment(department1);
        staff2.setDepartment(department2);

        /*
        #2
         * object allocation
         * Here’s what happens in JVM memory:

            1. Heap:
            The Employee object is allocated on the heap.

            Its instance fields (id, name) are stored inside the object.

            2. Stack:
            The reference variable emp is stored on the stack (within the current method's frame).

            It points to the Employee object in the heap.

            3. String Pool:
            The string "John Doe" may be stored in the string pool (part of method area).
        */
       System.out.println("staff1: " + staff1.getClass() + "in memory:" + System.identityHashCode(staff1));
       System.out.println("staff2: " + staff2.getClass()  + "in memory:" + System.identityHashCode(staff1));

        System.out.println("address1: " + address1.getClass() + "in memory:" + System.identityHashCode(staff1));
       System.out.println("address2: " + address2.getClass() + "in memory:" + System.identityHashCode(staff1));
       System.out.println("department1: " + department1.getClass() + "in memory:" + System.identityHashCode(staff1));
       System.out.println("department2: " + department2.getClass() + "in memory:" + System.identityHashCode(staff1));

       System.out.println(staff1 == staff2 );
       System.out.println(staff1.equals(staff3));

        Field[] fields = staff1.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // allow access to private fields
            System.out.println(System.identityHashCode(field.getName()));
        }
       // #3 static demo : static can be called without instantiation
        System.out.println("top rate: " + Employee.MAX_SALARY);

        /* #4
        global variable violates high cohesion and low coupling rule like one change interfere with others unexpectingly
        make codes are hard to test, also violates encapsulation OOP principle. not thread-safe.
        * */

        /*
        #5
        Strings in Java are immutable so they can be safely stored in the string constant pool.
        Since multiple variables can point to the same interned string,
        immutability ensures that one variable can't change the value for others — making string reuse safe and efficient.
        * */

        /* #6
        final make variable cannot be modified. when apply on method it cannot override, when apply on class
         cannot be inheritance.
        * */
        final double pi = 3.14;

        /*
        #7
        * */
        int a = 10;
        changeNumber(a);
        System.out.println("number: " + a);

        changeEmployee(staff1);
        System.out.println("employee: " + staff1.getFirstname());

        // #8 overload allow method have same name with different signature
        changeNumber(a, 12);
        System.out.println("number: " + a);

        //#10
        //top k frequent elements
        int[] test1 = {1,1,1,2,2,3};
        int[] test2 = {1};
        int[] ans1 = Leetcode.topKFrequent(test1, 2);
        int[] ans2 = Leetcode.topKFrequent(test2, 1);
        System.out.println("ans1: " + Arrays.toString(ans1));
        System.out.println("ans2: " + Arrays.toString(ans2));
        //two sum
        int[] test3 = {2, 7, 11, 15};
        int[] test4 = {3, 2, 4};
        int[] ans3 = Leetcode.twoSum(test3, 9);
        int[] ans4 = Leetcode.twoSum(test4, 6);
        System.out.println("ans1: " + Arrays.toString(ans3));
        System.out.println("ans2: " + Arrays.toString(ans4));













    }
    //pass a primitive data type
    private static void changeNumber(int number){
        number = 100;
    }
    private static void changeNumber(int oldNumber, int newNumber){
        oldNumber = newNumber;
    }
    //pass a object
    private static void changeEmployee(Employee employee){
        employee.setFirstname("changed");
        employee = new Employee();
        employee.setFirstname("jack"); //it does not affect staff in main
    }




}