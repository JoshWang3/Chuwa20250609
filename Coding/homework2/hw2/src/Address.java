import java.util.Objects;

public class Address {
    private String street;
    private String city;
    private String zipCode;

    public Address() { }

    public Address(String street, String city, String zipCode) {
        this.street  = street;
        this.city    = city;
        this.zipCode = zipCode;
    }

    public String getStreet()   { return street; }
    public void setStreet(String street)   { this.street = street; }
    public String getCity()     { return city; }
    public void setCity(String city)       { this.city = city; }
    public String getZipCode()  { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    @Override
    public String toString() {
        return "Address" +
                "street='"  + street  + '\'' +
                ", city='"  + city    + '\'' +
                ", zipCode='" + zipCode + '\'' ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address ad = (Address) o;
        return Objects.equals(street,  ad.street) &&
                Objects.equals(city,    ad.city) &&
                Objects.equals(zipCode, ad.zipCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, city, zipCode);
    }
}
