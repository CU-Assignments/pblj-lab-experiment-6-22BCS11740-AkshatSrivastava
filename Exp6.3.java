// To develop a Java program that processes a large dataset of products using Streams class to:
//   - Group products by category
//   - Find the most expensive product in each category
//   - Calculate the average price of all products


// Instruction
// Step 1: Create the Product Class
// - Define a Product class with attributes:
//     name (String)
//     category (String)
//     price (double)
  
// or (Reads product data from a CSV file)
// For Example: "Laptop", "Electronics", 1200
//              "Phone", "Electronics", 800


// Step 2: Create the ProductProcessor Class
// - Create a list of products with multiple categories and prices.
// - Use Streams API to:
//     Group products by category using Collectors.groupingBy().
//     Find the most expensive product in each category using Collectors.maxBy().
//     Calculate the average price of all products using Collectors.averagingDouble().
// - Display the results.


  
//     Test Cases
//     Test Case	                                     Input Data	                                                                           Expected Output
//     Case 1: Normal Case             	     Sample dataset with Electronics, Clothing, and Footwear	                      Grouped products, Most Expensive per category, Average price
//     Case 2: Single Category Only           All products in "Electronics"	                                                One category, Most Expensive in Electronics, Average of all
//     Case 3: Same Price in a Category	     Two products with the same highest price in "Footwear"	                        Any of the most expensive ones is displayed
//     Case 4: Only One Product	             One product "Laptop" in "Electronics"	                                        Laptop as the most expensive, Laptop as the only average
//     Case 5: Empty List	                   No products	                                                                  No grouping, No most expensive product, Average price = 0


import java.util.*;
import java.util.stream.Collectors;

class Product {
    String name, category;
    double price;

    Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    String getCategory() { return category; }
    double getPrice() { return price; }
}

public class ProductProcessor63 {
    public static void main(String[] args) {
        testCase1();
        testCase2();
        testCase3();
        testCase4();
        testCase5();
    }

    static void processAndDisplay(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        Map<String, List<Product>> groupedByCategory = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory));

        Map<String, Optional<Product>> mostExpensiveByCategory = products.stream()
            .collect(Collectors.groupingBy(Product::getCategory, 
                Collectors.maxBy(Comparator.comparingDouble(Product::getPrice))));

        double avgPrice = products.stream()
            .collect(Collectors.averagingDouble(Product::getPrice));

        groupedByCategory.forEach((category, list) -> {
            System.out.println("Category: " + category);
            list.forEach(p -> System.out.println(" - " + p.name + " ($" + p.price + ")"));
        });

        System.out.println("\nMost Expensive Product per Category:");
        mostExpensiveByCategory.forEach((category, product) -> 
            System.out.println(category + ": " + product.get().name + " ($" + product.get().price + ")"));

        System.out.println("\nAverage Price of All Products: $" + avgPrice);
        System.out.println("---------------------");
    }

    static void testCase1() {
        System.out.println("Test Case 1: Normal Case");
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 1200),
            new Product("Phone", "Electronics", 800),
            new Product("Shirt", "Clothing", 50),
            new Product("Shoes", "Footwear", 100)
        );
        processAndDisplay(products);
    }

    static void testCase2() {
        System.out.println("Test Case 2: Single Category Only");
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 1200),
            new Product("Phone", "Electronics", 800),
            new Product("Tablet", "Electronics", 900)
        );
        processAndDisplay(products);
    }

    static void testCase3() {
        System.out.println("Test Case 3: Same Price in a Category");
        List<Product> products = Arrays.asList(
            new Product("Sneakers", "Footwear", 100),
            new Product("Boots", "Footwear", 100)
        );
        processAndDisplay(products);
    }

    static void testCase4() {
        System.out.println("Test Case 4: Only One Product");
        List<Product> products = Collections.singletonList(
            new Product("Laptop", "Electronics", 1200)
        );
        processAndDisplay(products);
    }

    static void testCase5() {
        System.out.println("Test Case 5: Empty List");
        List<Product> products = new ArrayList<>();
        processAndDisplay(products);
    }
}
