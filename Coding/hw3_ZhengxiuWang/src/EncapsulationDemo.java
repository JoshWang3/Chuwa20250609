public class EncapsulationDemo {
        /*
    Encapsulation:
    - private data
    - public methods
    - safe access and validation
    */

    public static void main(String[] args) {
        Student1 s = new Student1();
        s.setName("Maggie");
        s.setGrade(90);

        System.out.println("Student: " + s.getName());
        System.out.println("Grade: " + s.getGrade());
    }
}

class Student1 {
    private String name;
    private int grade;

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(int grade) {
        if (grade >= 0 && grade <= 100)
            this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getGrade() {
        return grade;
    }
}
