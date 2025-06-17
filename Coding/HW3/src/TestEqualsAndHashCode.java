import java.util.Objects;

class Point{
    int x;
    int y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
public class TestEqualsAndHashCode {
    public static void main(String[] args) {
        Point p1 = new Point(1, 2);
        Point p2 = new Point(1, 2);
        Point p3 = new Point(3, 4);
        System.out.println(p1.equals(p2));//true
        System.out.println(p1.equals(p3));//false
        System.out.println(p1.hashCode()==p2.hashCode());//true
        System.out.println(p1.hashCode()==p3.hashCode());//false
        System.out.println(p1==p2); //false
    }
}
