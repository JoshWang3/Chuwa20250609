package hw2;

import java.util.Date;
import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) throws Exception {
        // 实例1
        Address addr1 = new Address("123 中山路", "上海", "上海", "200000");
        Department dept1 = new Department("工程部");
        Employee emp1 = new Employee("张三", new Date(90, 4, 15), dept1, "123-45-6789", addr1);

        // 实例2
        Address addr2 = new Address("456 南京路", "北京", "北京", "100000");
        Department dept2 = new Department("财务部");
        Employee emp2 = new Employee("李四", new Date(85, 7, 20), dept2, "987-65-4321", addr2);

        // 打印对象
        System.out.println("🔹 Employee 1:\n" + emp1);
        System.out.println("🔹 Employee 2:\n" + emp2);

        // 反射查看对象内存结构
        System.out.println("\n🔍 Inspecting Employee 1:");
        inspectViaReflection(emp1);

        System.out.println("\n🔍 Inspecting Employee 2:");
        inspectViaReflection(emp2);
    }

    // 使用 Java 反射打印对象的字段信息和值
    public static void inspectViaReflection(Employee emp) throws Exception {
        Class<?> clazz = emp.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(emp);
            System.out.println("Field: " + field.getName() +
                    ", Type: " + field.getType().getSimpleName() +
                    ", Value: " + value);
        }

        // 模拟内存地址（哈希码）
        System.out.println("Identity hash code: " + System.identityHashCode(emp));
        System.out.println("创建后员工总数：" + Employee.getEmployeeCount());
    }
}