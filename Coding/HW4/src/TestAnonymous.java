interface Operation{
    int operate(int a, int b);
}
public class TestAnonymous {
    public static void main(String[] args) {
        Operation opadd = new Operation() {
            public int operate(int a, int b) {
                return a + b;
            }
        };
        Operation opsub = new Operation() {
            public int operate(int a, int b) {
                return a - b;
            }
        };
        System.out.println(opadd.operate(1, 2));
        System.out.println(opsub.operate(1, 2));
    }
}
