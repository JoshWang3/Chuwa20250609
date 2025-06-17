public class Address {
    private String street;
    private String city;
    private String state;

    public Address(String street, String city, String state) {
        this.street = street;
        this.city = city;
        this.state = state;
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getStreet() {
        return this.street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return this.city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return this.street + " " + this.city + " " + this.state;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Address d)) {return false;}
        return this.street.equals(d.street) && this.city.equals(d.city) && this.state.equals(d.state);
    }
}
