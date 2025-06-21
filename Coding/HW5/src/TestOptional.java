import java.util.List;
import java.util.Optional;

public class TestOptional {
    public static void main(String[] args) {
        List<Products> store = List.of(
                new Products("Electronics", "PC"      , 2099),
                new Products("Grocery"    , "B",   1.99),
                new Products("Electronics", "S", 999),
                new Products("Grocery"    , "E"  ,  7.50),
                new Products("Grocery"    , "M", 3.00),
                new Products("Special",null,100)
        );
        //Find sum of product name length
        int res=0;
        for (Products p : store) {
            int length= Optional.ofNullable(p.getName())
                    .orElse("").length();
            res+=length;
        }
        System.out.println(res);
    }
}
