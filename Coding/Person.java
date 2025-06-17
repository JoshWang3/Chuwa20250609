package Coding;

class Person {
    protected String name;

    public Person(String name) {
        this.name = name;
    }

    public void greet() {
        System.out.println("hello, Iam " + name);
    }
}

class Employee extends Person {
    private String jobTitle;

    public Employee(String name, String jobTitle) {
        super(name); // 调用父类构造方法
        this.jobTitle = jobTitle;
    }

    public void work() {
        System.out.println(name + " my job title is " + jobTitle);
    }
}