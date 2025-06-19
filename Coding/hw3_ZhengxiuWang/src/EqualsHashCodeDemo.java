import java.util.HashSet;

public class EqualsHashCodeDemo {
        /*
    equals() → compare content
    hashCode() → used in HashSet/Map
    must override both
    */

    public static void main(String[] args) {
        Student s1 = new Student("Alice", 1001);
        Student s2 = new Student("Alice", 1001);

        System.out.println("s1 == s2: " + (s1 == s2));             // false
        System.out.println("s1.equals(s2): " + s1.equals(s2));     // true
        System.out.println("s1.hashCode(): " + s1.hashCode());
        System.out.println("s2.hashCode(): " + s2.hashCode());

        HashSet<Student> set = new HashSet<>();
        set.add(s1);
        set.add(s2);  // won't be added again if equals & hashCode match

        System.out.println("Set size: " + set.size()); // should be 1
    }
}

// real class with override
class Student {
    String name;
    int id;

    Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    // override equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj instanceof Student) {
            Student other = (Student) obj;
            return this.name.equals(other.name) && this.id == other.id;
        }

        return false;
    }

    // override hashCode
    @Override
    public int hashCode() {
        return name.hashCode() + id;
    }
}
