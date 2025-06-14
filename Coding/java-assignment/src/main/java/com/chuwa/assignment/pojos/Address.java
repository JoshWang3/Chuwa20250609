package com.chuwa.assignment.pojos;
import java.util.*;

public class Address {
    private String street;
    private String city;
    private String state;

    public Address(String street, String city, String state) {
        this.street = street;
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        return street + ", " + city + ", " + state;
    }

    @Override
    public boolean equals(Object o){
        if( this == o) return true;     //same object
        if(!(o instanceof Address)) return false;  // null or wrong type
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(city, address.city) &&
                Objects.equals(state, address.state);
    }
}