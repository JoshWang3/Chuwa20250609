import java.util.ArrayList;

public class TestParameterAndOverloading {
    static void change(int x){
        x=x+1;
    }
    static void change(ArrayList<Integer> x){
        x=new ArrayList<>();
        x.add(2);
    }
    static void change(int[] x){
        x[0]=x[0]+1;
    }
    public static void main(String[] args) {
        //Example of pass by value
        int x=1;
        change(x);
        System.out.println(x); //1

        ArrayList<Integer> list =new ArrayList<>();
        list.add(1);
        change(list);
        System.out.println(list.get(0)); //1

        //Seems like pass by reference, but actually not. int[] is reference type
        //The value of a didn't change, only change the content of memory it points to.
        int[] a={1,2,3,4,5};
        change(a);
        System.out.println(a[0]); //2
    }
}
