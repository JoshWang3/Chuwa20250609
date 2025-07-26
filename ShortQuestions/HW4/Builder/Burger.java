package Builder;

public class Burger {

    // Required parameters
    private final String bunType;
    private final String pattyType;

    // Optional parameters
    private final boolean hasCheese;
    private final boolean hasLettuce;
    private final boolean hasTomato;
    private final boolean hasOnions;
    private final boolean hasBacon;

    // private constructor only accessible by Builder
    private Burger(BurgerBuilder builder) {
        this.bunType = builder.bunType;
        this.pattyType = builder.pattyType;
        this.hasCheese = builder.hasCheese;
        this.hasLettuce = builder.hasLettuce;
        this.hasTomato = builder.hasTomato;
        this.hasOnions = builder.hasOnions;
        this.hasBacon = builder.hasBacon;
    }

    @Override
    public String toString() {
        return "Burger [" +
                "bunType='" + bunType + '\'' +
                ", pattyType='" + pattyType + '\'' +
                ", cheese=" + hasCheese +
                ", lettuce=" + hasLettuce +
                ", tomato=" + hasTomato +
                ", onions=" + hasOnions +
                ", bacon=" + hasBacon +
                ']';
    }


    // Builder Inner Class
    public static class BurgerBuilder {

        // required
        private final String bunType;
        private final String pattyType;

        // Optional
        private boolean hasCheese = false;
        private boolean hasLettuce = false;
        private boolean hasTomato = false;
        private boolean hasOnions = false;
        private boolean hasBacon = false;

        public BurgerBuilder(String bunType, String pattyType) {
            this.bunType = bunType;
            this.pattyType = pattyType;
        }

        public BurgerBuilder cheese(boolean value) {
            this.hasCheese = value;
            return this;
        }

        public BurgerBuilder lettuce(boolean value) {
            this.hasLettuce = value;
            return this;
        }

        public BurgerBuilder tomato(boolean value) {
            this.hasTomato = value;
            return this;
        }

        public BurgerBuilder onions(boolean value) {
            this.hasOnions = value;
            return this;
        }

        public BurgerBuilder bacon(boolean value) {
            this.hasBacon = value;
            return this;
        }

        public Burger build() {
            return new Burger(this);
        }
    }
}
