public class BuilderPatternDemo {
    public static void main(String[] args) {
        // 使用 builder 构建一个房子
        House house = new House.Builder()
                .setDoor("Wooden Door")
                .setWindow("Glass Window")
                .setWall("Concrete Wall")
                .build();

        // 打印房子的配置
        house.showHouse(); // Output: House with: Wooden Door, Glass Window, Concrete Wall
    }
}

// ========== 产品类：房子 ==========
class House {
    private String door;
    private String window;
    private String wall;

    // 私有构造函数，只允许 builder 使用
    private House(Builder builder) {
        this.door = builder.door;
        this.window = builder.window;
        this.wall = builder.wall;
    }

    // 打印房子信息的方法
    public void showHouse() {
        System.out.println("House with: " + door + ", " + window + ", " + wall);
    }

    // ========== Builder 类 ==========
    public static class Builder {
        private String door;
        private String window;
        private String wall;

        public Builder setDoor(String door) {
            this.door = door;
            return this;
        }

        public Builder setWindow(String window) {
            this.window = window;
            return this;
        }

        public Builder setWall(String wall) {
            this.wall = wall;
            return this;
        }

        public House build() {
            return new House(this);
        }
    }
}
