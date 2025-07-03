public interface Student {
    void newStudent();
}

public interface Teacher {
    void newTeacher();
}

public class StudentA implements Student {
    @java.lang.Override
    public void newStudent() {
        System.out.println("Student A created");
    }
}

public class TeacherA implements Teacher {
    @java.lang.Override
    public void newTeacher() {
        System.out.println("Teacher A created");
    }
}

public interface School {
    Student addStudent();
    Teacher addTeacher();
}

public class Harvard implements School {
    @java.lang.Override
    public Student addStudent() {
        return new StudentA();
    }

    @java.lang.Override
    public Teacher addTeacher() {
        return new TeacherA();
    }
}

public class AbstractFactoryMethod {
    public static void main(String[] args) {
        School school = new Harvard();
        Teacher teacher = school.addTeacher();
        Student student = school.addStudent();

        teacher.newTeacher(); // Teacher A created
        student.newStudent(); // Student A created
    }
}