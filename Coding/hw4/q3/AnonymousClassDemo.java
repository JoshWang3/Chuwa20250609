package hw4.q3;

public class AnonymousClassDemo {
    public static void main(String[] args) {
        Button button = new Button();

        // Register listener using an anonymous class
        button.setClickListener(new ClickListener() {
            @Override
            public void onClick() {
                System.out.println("Button handler: Hello from anonymous class!");
            }
        });

        button.click();

    }
}
