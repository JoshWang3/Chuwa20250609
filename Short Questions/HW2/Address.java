public class Address {
    private String street;
    private String district;
    private String city;
    private String postcode;

    public Address(String street, String district, String city, String postcode) {
        this.street = street;
        this.district = district;
        this.city = city;
        this.postcode = postcode;
    }

    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }
    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return street + ", " + district + ", " + city + ", " + postcode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Address address = (Address) obj;
        return street.equals(address.street) &&
                district.equals(address.district) &&
                city.equals(address.city) &&
                postcode.equals(address.postcode);
    }
}
