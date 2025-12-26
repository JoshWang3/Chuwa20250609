package Designpatterns.Builder;

public class Bike {
    private String brand;
    private int year;

    private Bike(Builder builder) {
        this.brand = builder.brand;
        this.year = builder.year;
    }

    public String getInfo() {
        return "brand " + brand + " year " + year;
    }

    public static class Builder {
        private String brand;
        private int year;

        public Builder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Bike build() {
            return new Bike(this);
        }
    }
}
