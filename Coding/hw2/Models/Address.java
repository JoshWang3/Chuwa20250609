package hw2.Models;

public class Address {
    private String address;
    private String postalCode;

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public Address(String address, String postalCode) {
        this.address = address;
        this.postalCode = postalCode;
    }
}
