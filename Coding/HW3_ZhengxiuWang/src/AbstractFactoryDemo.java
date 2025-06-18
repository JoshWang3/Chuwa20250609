// ======= 产品接口 1：Phone =======
interface Phone {
    void call();
}

// 实现类：iPhone
class IPhone implements Phone {
    public void call() {
        System.out.println("Calling with iPhone...");
    }
}

// 实现类：Samsung
class Samsung implements Phone {
    public void call() {
        System.out.println("Calling with Samsung...");
    }
}

// ======= 产品接口 2：Mask =======
interface Mask {
    void wear();
}

// 实现类：N95
class N95 implements Mask {
    public void wear() {
        System.out.println("Wearing N95 mask...");
    }
}

// 实现类：KN95
class KN95 implements Mask {
    public void wear() {
        System.out.println("Wearing KN95 mask...");
    }
}

// ======= 抽象工厂接口 =======
interface AbstractFactory {
    Phone createPhone();
    Mask createMask();
}

// ======= 具体工厂 1：Apple 工厂 =======
class AppleFactory implements AbstractFactory {
    public Phone createPhone() {
        return new IPhone();
    }

    public Mask createMask() {
        return new N95();
    }
}

// ======= 具体工厂 2：Samsung 工厂 =======
class SamsungFactory implements AbstractFactory {
    public Phone createPhone() {
        return new Samsung();
    }

    public Mask createMask() {
        return new KN95();
    }
}

// ======= 客户端测试 =======
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        // 创建 Apple 工厂
        AbstractFactory appleFactory = new AppleFactory();
        Phone applePhone = appleFactory.createPhone();
        Mask appleMask = appleFactory.createMask();

        applePhone.call();      // Output: Calling with iPhone...
        appleMask.wear();       // Output: Wearing N95 mask...

        // 创建 Samsung 工厂
        AbstractFactory samsungFactory = new SamsungFactory();
        Phone samsungPhone = samsungFactory.createPhone();
        Mask samsungMask = samsungFactory.createMask();

        samsungPhone.call();    // Output: Calling with Samsung...
        samsungMask.wear();     // Output: Wearing KN95 mask...
    }
}
