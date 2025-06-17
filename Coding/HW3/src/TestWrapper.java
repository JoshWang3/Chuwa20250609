class A<T>{
    private T obj;
    public A(T obj){this.obj = obj;}
    public T getObj(){return obj;}
    public void setObj(T obj){this.obj = obj;}
}
public class TestWrapper {
    //A<int> a1; not allowed
    static A<Integer> a1=new A<>(1);
    public static void main(String[] args){
        System.out.println(a1.getObj());
    }
}
