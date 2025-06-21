import java.util.*;
import java.util.stream.Collectors;

class Products{
    private String catagory;
    private String name;
    private double price;
    public Products(String catagory, String name, double price){
        this.catagory = catagory;
        this.name = name;
        this.price = price;
    }
    public String getCatagory(){
        return catagory;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    @Override
    public String toString() {
        return String.format("%s:$%.2f", name, price);
    }
}
public class GroupProducts {
    static class Solution{
        public static Map<String,List<Products>> groupProducts(List<Products> products){
            return products.stream()
                    .collect(Collectors.groupingBy(
                            Products::getCatagory,
                            Collectors.collectingAndThen(Collectors.toList(),list->{
                                list.sort(Comparator.comparingDouble(Products::getPrice).reversed());
                                return list;
                            })
                    ));
        }
    }
    public static void main(String[] args) {
        List<Products> store = List.of(
                new Products("Electronics", "PC"      , 2099),
                new Products("Grocery"    , "Bananas",   1.99),
                new Products("Electronics", "Smartphone", 999),
                new Products("Grocery"    , "Egg"  ,  7.50),
                new Products("Grocery"    , "Milk", 3.00)
        );
        Map<String,List<Products>> groupProducts = Solution.groupProducts(store);
        groupProducts.forEach((catogory,list)->{
            System.out.println(catogory+":"+list);
        });
    }
}
