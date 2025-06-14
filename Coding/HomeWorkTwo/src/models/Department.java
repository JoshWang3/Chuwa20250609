package models;

public class Department {
    private String name;
    private Employee director;
    private int size;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getDirector() {
        return director;
    }

    public void setDirector(Employee director) {
        this.director = director;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", director='" + director + '\'' +
                ", size=" + size +
                '}';
    }
}
