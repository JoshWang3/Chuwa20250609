
public class Department {
    private int id;
    private String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Override toString method, so that when an Employee object is being printed, the print out looks meaningful and readble.
    @Override
    public String toString() {
        return "[id=" + id + ", name=" + name + "]";
    }

    //Override equals method, so that only when two Employees have identical information, we consider they are the same employee.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Department department = (Department) obj;
        return id == department.id && name.equals(department.name);
    }
}
