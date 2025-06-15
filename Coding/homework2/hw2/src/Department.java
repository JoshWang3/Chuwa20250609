import java.util.Objects;

public class Department {
    private String name;

    public Department() {}

    public Department(String name) {
        this.name = name;
    }

    public String getName()           { return name; }
    public void   setName(String n)   { this.name = n; }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Department)) return false;
        Department d = (Department) o;
        return Objects.equals(name, d.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
