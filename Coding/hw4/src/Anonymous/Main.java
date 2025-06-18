package Anonymous;

import interfaces.Circle;
import interfaces.Shape;

public class Main {
    public static void main(String[] args) {
       Shape shape = new Shape() {
           @Override
           public void draw() {
               System.out.println("Draw");
           }
       };
       shape.draw();
    }
}