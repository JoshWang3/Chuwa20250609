interface Button {
    void click();
}

interface TextField {
    void type();
}

class WindowsButton implements Button {
    public void click() {
        System.out.println("Windows Button Clicked");
    }
}

class MacButton implements Button {
    public void click() {
        System.out.println("Mac Button Clicked");
    }
}

class WindowsTextField implements TextField {
    public void type() {
        System.out.println("Typing in Windows Text Field");
    }
}

class MacTextField implements TextField {
    public void type() {
        System.out.println("Typing in Mac Text Field");
    }
}

interface UIFactory {
    Button createButton();
    TextField createTextField();
}

class WindowsUIFactory implements UIFactory {
    public Button createButton() {
        return new WindowsButton();
    }

    public TextField createTextField() {
        return new WindowsTextField();
    }
}

class MacUIFactory implements UIFactory {
    public Button createButton() {
        return new MacButton();
    }

    public TextField createTextField() {
        return new MacTextField();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        UIFactory windowsFactory = new WindowsUIFactory();
        Button winBtn = windowsFactory.createButton();
        TextField winField = windowsFactory.createTextField();
        winBtn.click();
        winField.type();

        UIFactory macFactory = new MacUIFactory();
        Button macBtn = macFactory.createButton();
        TextField macField = macFactory.createTextField();
        macBtn.click();
        macField.type();
    }
}