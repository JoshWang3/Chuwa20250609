package Coding;






public class hw3 {

    public static void main(String[] args) {
        // 封装示例
        Student stu = new Student();
        stu.setName("Tom");  // 使用 setter 设置名字
        System.out.println("student name：" + stu.getName());

        // 继承示例
        Employee emp = new Employee("tom", "software engine");
        emp.greet(); // 调用父类方法
        emp.work();  // 调用子类方法

        // 多态示例
        Animal a1 = new Dog();
        Animal a2 = new Cat();
        a1.makeSound(); // 输出：汪汪！
        a2.makeSound(); // 输出：喵喵！
    }
}


