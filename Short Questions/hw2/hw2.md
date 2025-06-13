2. Employee objects are stored on heap. That is to say, objects that are created by new Employee() are stored on heap. However, the employee1 and employee2 vairables are stored on stack. They are only reference to the actual object on heap. The Employee.class is stored in method area.
3. Static objects are created when class is loaded by jvm, normal objects are created when class objects are created
4. Global variables can be modified from anywhere, making it hard to track who changed what and when. By encapsulation and using getter and setter they are be better managed
5. For memory effiency reasons, string contents are stored in a shared pool. There are also security and thread-safety reasons
6. 
7.
public class Test {
public static void change(int x) { x = 10; }

public static void main(String[] args) {
    int a = 5;
    change(a);
    System.out.println(a);
}

5 will be printed instead of 10, which means the change function modifies the copy, not the original number

