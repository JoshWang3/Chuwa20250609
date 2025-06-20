
public class Demo {
    public static void main(String[] args) {
        Global.count = 1;
        add();
        resetTo10();
        System.out.println("Global Varible's count = " +  Global.count);
    }
    public static void add() {
        Global.count += 10;
    }

    public static void resetTo10() {
        Global.count = 10;
    }
}

// 由于 Global.count 是全局变量，任何地方都能修改，导致数据不可控、不安全，容易出 bug。
// 代码如上所示，reset() 可以随时清零，全局变量变得不可预测。