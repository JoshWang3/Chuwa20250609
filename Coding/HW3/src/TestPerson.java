class Person{
    private static final String language="English";
    public static final String getLanguage(){return language;}
    protected static int counter=0;
    public static class InnerClass{
        public static final String s="Inner Class";
    }
    private String name;
    public Person(String name){
        setName(name);
        counter++;
    }
    public Person(char[] name){
        setName(new String(name));
        counter++;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void describe(){
        System.out.printf("Person[name=%s]%n", getName());
    }
    public final void finalMethod(){
        System.out.println("Final method");
    }
}
final class Employee extends Person{
    private double salary;
    public Employee(char[] name,double salary){
        super(name);
        setSalary(salary);
    }
    public Employee(String name,double salary){
        super(name);
        setSalary(salary);
    }
    public void setSalary(double salary){
        this.salary = salary;
    }
    public double getSalary(){
        return salary;
    }
    @Override
    public void describe(){
        System.out.printf("Employee[name=%s,salary=%f]%n", getName(),getSalary());
    }
    public static int getCounter(){
        return counter;
    }
    //@Override
    //public final void finalMethod() not allowed
}
//class subclass extends Employee; not allowed
public class TestPerson {
    public static void main(String[] args){
        Person p1=new Employee("John",10000.0);
        Person p2=new Person("Amy");
        p1.describe();
        p2.describe();

        System.out.println(Employee.getCounter());
        System.out.println(Person.counter);
        //System.out.println(Employee.language); not allowed

    }
}
