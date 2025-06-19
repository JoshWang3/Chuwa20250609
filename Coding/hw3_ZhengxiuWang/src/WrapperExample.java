import java.util.Scanner;
/*
- Integer is a wrapper class
- Use it to convert String to number
- We can call methods like compareTo()
*/
public class WrapperExample {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your age: ");
        String ageStr = input.nextLine();

        // Convert String to int using wrapper class
        Integer age = Integer.parseInt(ageStr);

        // Use wrapper method
        if (age.compareTo(18) >= 0) {
            System.out.println("You are an adult.");
        } else {
            System.out.println("You are underage.");
        }
    }
}
