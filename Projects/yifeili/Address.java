package yifeili;
import java.util.*;

public class Address{

    private String street;
    private String city;
    private String state;
    private int zip;

    public Address(String street, String city, String state, int zip){
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;

    }

    public String getAddress(){
        return street+","+city+","+state+","+zip;
    }

    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public int getZip() { return zip; }

    public void setAddress(String street, String city, String state, int zip){
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Address that)) return false;
        if (!street.equals(that.street)) return false;
        if (!city.equals(that.city)) return false;
        if (!state.equals(that.state)) return false;
        return zip == that.zip; // primitive 类别可以用 == 比较值
    }

    @Override
    public String toString(){
        return street+","+city+","+state+","+zip;
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, state, zip);
    }
}