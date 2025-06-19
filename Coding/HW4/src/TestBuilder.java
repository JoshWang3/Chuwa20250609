class Student{
    private String name;
    private double gpa;
    public Student(Builder builder){
        this.name=builder.name;
        this.gpa=builder.gpa;
    }
    public String getName(){
        return name;
    }
    public double getGpa(){
        return gpa;
    }

    public static class Builder{
        private String name;
        private double gpa;
        public Builder name(String name){
            this.name = name;
            return this;
        }
        public Builder gpa(double gpa){
            this.gpa = gpa;
            return this;
        }
        public Student build(){
            return new Student(this);
        }
    }

}
public class TestBuilder {
    public static void main(String[] args) {
        Student s1 = new Student.Builder()
                .name("Alice")
                .gpa(4.0)
                .build();
        System.out.println(s1.getName());
        System.out.println(s1.getGpa());
    }
}
