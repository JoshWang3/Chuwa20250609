class Shape{
    public void printName(){
        System.out.println("I am Shape");
    }
}
class Rectangle extends Shape{
    public void printName(){
        System.out.println("I am Rectangle");
    }
}
class Circle extends Shape{
    public void printName(){
        System.out.println("I am Circle");
    }
}

interface BasicFactory{
    Shape getShape();
}
class RectangleFactory implements BasicFactory{
    public Shape getShape(){
        return new Rectangle();
    }
}
class CircleFactory implements BasicFactory{
    public Shape getShape(){
        return new Circle();
    }
}

class Factory{
    private BasicFactory bf;
    public Factory(BasicFactory bf){
        this.bf = bf;
    }
    public Shape getShape(){
        return bf.getShape();
    }
    public void setFactory(BasicFactory bf){
        this.bf = bf;
    }
}
public class TestAbstractFactory {
    public static void main(String[] args) {
        Factory f=new Factory(new RectangleFactory());
        Shape s=f.getShape();
        s.printName();

        f.setFactory(new CircleFactory());
        Shape s2 = f.getShape();
        s2.printName();
    }
}
