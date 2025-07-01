package hw5;

import java.util.Optional;

class Address {
    private String city;
    private String state;

    public Address(String city, String state) {
        this.city = city;
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

class Student {
    private String name;
    private Address address; // might be null
    public Student(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }
    // Return Optional wrapper to avoid null checks
    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }
}

public class OptionalWithStudentDemo {
    public static void main(String[] args) {
        Student s1 = new Student("Helen", new Address("Seattle", "WA"));
        Student s2 = new Student("Brian", null);

        // Safely access address and print city
        s1.getAddress()
                .map(Address::getCity)
                .ifPresent(city -> System.out.println(s1.getName() + "'s city: " + city));
        // Helen's city: Seattle

        // No crash, nothing is printed for s2
        s2.getAddress()
                .map(Address::getCity)
                .ifPresent(city -> System.out.println(s1.getName() + "'s city: " + city));


        // Provide fallback value if address or city is missing
        String city = s2.getAddress()
                .map(Address::getCity)
                .orElse("City is not available.");
        System.out.println(s2.getName() + "'s city: " + city);
        // Brian's city: City is not available.
    }


}
