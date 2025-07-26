public class Question12 {
    public static void main(String[] args){
        Person p1 = new Person("John", 23);
        Person p2 = new Person("John", 23);
        Person p3 = p1;


        System.out.println("p1.equals(p2): "+ p1.equals(p2));
        System.out.println("p1.equals(p3): "+ p1.equals(p3));
        System.out.println("p1.hashcode(): "+ p1.hashCode()+"\n" +
                           "p2.hashcode(): "+ p2.hashCode()+"\n" +
                           "p3.hashcode(): "+ p3.hashCode());
    }
}

class Person{
    private String name;
    private int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;                      // same reference
        if (obj == null || getClass() != obj.getClass()) return false;
        Person other = (Person) obj;
        return age == other.age &&                         // compare age
                (name != null ? name.equals(other.name) : other.name == null); // compare name
    }
}