public class encapsulationSample {
    public static void main(String[] args) {
        Student student = new Student("Alice", 20, "S12345");
        student.setAge(21); // Update age using setter
        System.out.println(student);
    }
}
