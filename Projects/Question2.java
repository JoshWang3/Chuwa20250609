import java.util.*;
import java.util.stream.Collectors;

public class Question2 {
    public static void main(String[] args) {
        List<Blog>  blogs = Arrays.asList(
                null,
                new Blog("A", Arrays.asList("b", "a", "c")),
                new Blog("B", Arrays.asList("a", "b", "c", "d", "e")),
                new Blog("C", Arrays.asList("a", "b")),
                new Blog("D", Arrays.asList("a", "b", "h")),
                new Blog("E", Arrays.asList("f", "g", "c")),
                new Blog("F", Arrays.asList("i", "j", "k")),
                new Blog("G", Arrays.asList("a", "b", "l")),
                new Blog("K", null),
                new Blog("M", Arrays.asList("a", null, "l"))
        );


        List<String> result = blogs
                .stream()
                .filter(ob -> ob != null)
                .flatMap(blog ->
                        Optional.ofNullable(blog.getTags())
                                .orElse(Collections.emptyList())
                                .stream()
                                .filter(ob -> ob != null)
                )
                .distinct()
                .sorted()
                .toList();

        System.out.println(result);







        List<Products> products = Arrays.asList(
                new Products("iPhone 15", "Electronics", 999.99),
                new Products("Galaxy S24", "Electronics", 899.99),
                new Products("MacBook Pro", "Electronics", 1999.00),
                new Products("Banana", "Grocery", 0.99),
                new Products("Milk", "Grocery", 2.49),
                new Products("Eggs", "Grocery", 3.99),
                new Products("Desk", "Furniture", 120.0),
                new Products("Chair", "Furniture", 85.5),
                new Products("Bookshelf", "Furniture", 150.75),
                null,
                new Products("something", null, 150.75)

        );


        Map<String, List<Products>> result2 = products.stream().filter(ob -> ob != null)
                .collect(Collectors.groupingBy(category ->
                                Optional.ofNullable(category.getCategory())
                                        .orElse("UnknownType"),
                        Collectors.collectingAndThen( //downstream, stream for categories
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted((a,b) -> Double.compare(b.getPrice(), a.getPrice()))
                                        .toList()
                        )));

        System.out.println((result2));
    }
}
