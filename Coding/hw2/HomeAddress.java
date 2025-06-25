package hw2;

public class HomeAddress {
    private String street;
    private String city;
    private String state;
    private String zip;

    public HomeAddress(String street, String city, String state, String zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    @Override
    public String toString() {
        return getStreet() + ", " + getCity() + ", " + getState() + ", " + getZip();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null || obj.getClass() != HomeAddress.class) {
            return false;
        }
        HomeAddress other = (HomeAddress) obj;
        return street.equals(other.street)
                && city.equals(other.city)
                && state.equals(other.state)
                && zip.equals(other.zip);
    }
}
