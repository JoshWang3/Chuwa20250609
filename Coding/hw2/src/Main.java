import employee.Address;
import employee.Department;
import employee.Employee;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Department department1 = new Department("Engineering");
        Address address1 = new Address("123 Main St", "Springfield", "IL", "62704");
        Date dob1 = new Date(90, 5, 15);

        Employee employee1 = new Employee(
                "Thomas",
                "Anderson",
                dob1,
                department1,
                "123-45-6789",
                address1
        );

        Department department2 = new Department("Sales");
        Address address2 = new Address("123 Main St", "Springfield", "IL", "62704");
        Date dob2 = new Date(90, 5, 15);

        Employee employee2 = new Employee(
                "Alice",
                "Anderson",
                dob2,
                department2,
                "123-45-6789",
                address2
        );

        inspectObject(employee1);
        inspectObject(employee2);

        Employee.addEmployee();
        Employee.addEmployee();

        // global
        System.out.println(Global.power(3)); // 8.0
        System.out.println(GlobalReassign.resetGlobal());
        System.out.println(Global.power(3));  // 0.0

        // pass-by-value
        int y = 5;
        System.out.println(y);
        passByValue(y);
        System.out.println(y);
    }

    public static void inspectObject(Object obj) throws IllegalAccessException {
        Class<?> clazz = obj.getClass();
        System.out.println("\nInspecting object of class: " + clazz.getName());
        System.out.println("Object Identity Hash Code: " + System.identityHashCode(obj));

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(obj);
            System.out.println("  Field: " + field.getName() + " = " + value);
        }
    }

    public static void passByValue(int a) {
        a = 4;
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<Integer>[] freq = new List[nums.length + 1];
        for (int i = 0; i < freq.length; i++) {
            freq[i] = new ArrayList<>();
        }

        for (int key: map.keySet()) {
            freq[map.get(key)].add(key);
        }

        int[] res = new int[k];
        int j = 0;
        for (int i = freq.length - 1; i >= 0; i --) {
            for (int item: freq[i]) {
                res[j++] = item;
                if (j == k) {
                    return res;
                }
            }

        }

        return res;
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }
}