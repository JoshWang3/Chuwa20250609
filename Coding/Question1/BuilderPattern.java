package Question1;

public class BuilderPattern {
    public static void main(String[] args) {
        Burger myBurger = new Burger.BurgerBuilder("ButterBun", 2).lettuce(true).build();
        System.out.println(myBurger.display());
    }


}

class Burger{
    private String bunType;
    private int pattyCount;

    private int cheese;
    private boolean lettuce;
    private boolean tomato;

    private Burger(BurgerBuilder builder){
        this.bunType = builder.bunType;
        this.pattyCount = builder.pattyCount;
        this.cheese = builder.cheese;
        this.lettuce = builder.lettuce;
        this.tomato = builder.tomato;
    }

    public String display(){
        return ("bun:" + bunType +"\npattyCount:" + pattyCount + "\ncheese:" + cheese + "\nlettuce:" + lettuce + "\ntomato:" + tomato);
    }

    public static class BurgerBuilder{
        private String bunType;
        private int pattyCount;
        private int cheese;
        private boolean lettuce;
        private boolean tomato;

        public BurgerBuilder(String bunType, int pattyCount){
            this.bunType = bunType;
            this.pattyCount = pattyCount;
        }

        public BurgerBuilder cheese(int cheese){
            this.cheese = cheese;
            return this;
        }

        public BurgerBuilder lettuce(boolean lettuce){
            this.lettuce = lettuce;
            return this;
        }

        public BurgerBuilder tomato(boolean tomato){
            this.tomato = tomato;
            return this;
        }

        public Burger build() {
            return new Burger(this);
        }
    }
}

