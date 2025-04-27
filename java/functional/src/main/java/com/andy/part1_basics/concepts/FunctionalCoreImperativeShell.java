package com.andy.part1_basics.concepts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>Illustrates the Functional Core, Imperative Shell (FC/IS) architectural approach in a simplified e-commerce application.</p>
 *
 * <p>The core focuses on discount calculation, which is purely functional and independent of external dependencies.
 * The shell handles order processing, including database interaction and I/O.</p>
 */
public class FunctionalCoreImperativeShell {

    record Product(String name, double price) {
    }

    record DiscountedProduct(Product product, double discountedPrice) {
    }

    static List<DiscountedProduct> calculateDiscounts(List<Product> products) {
        List<DiscountedProduct> discountedProducts = new ArrayList<>();
        for (Product product : products) {
            double discountedPrice = product.price() * 0.9; // 10% discount
            discountedProducts.add(new DiscountedProduct(product, discountedPrice));
        }
        return discountedProducts;
    }


    static void processOrder(List<DiscountedProduct> discountedProducts) {
        // Simulate database interaction (e.g., order storage)
        storeOrderInDatabase(discountedProducts);

        // Generate and print the invoice
        Optional<String> invoice = generateInvoice(discountedProducts);
        invoice.ifPresent(System.out::println);
    }

    static void storeOrderInDatabase(List<DiscountedProduct> discountedProducts) {
        // Simulate storing the order in a database
        System.out.println("Order stored in the database.");
    }

    static Optional<String> generateInvoice(List<DiscountedProduct> discountedProducts) {
        // Simulate invoice generation based on discounted products
        double totalAmount = discountedProducts.stream()
                .mapToDouble(DiscountedProduct::discountedPrice)
                .sum();

        if (!discountedProducts.isEmpty()) {
            StringBuilder invoiceBuilder = new StringBuilder();
            invoiceBuilder.append("Invoice:\n");
            for (DiscountedProduct product : discountedProducts) {
                invoiceBuilder.append(product.product().name())
                        .append(" - $")
                        .append(product.discountedPrice())
                        .append("\n");
            }
            invoiceBuilder.append("Total Amount: $").append(totalAmount);
            return Optional.of(invoiceBuilder.toString());
        } else {
            return Optional.empty();
        }
    }


    public static void main(String[] args) {

        // Create a list of products
        List<Product> products = new ArrayList<>();
        products.add(new Product("ProductA", 100.0));
        products.add(new Product("ProductB", 50.0));

        // Calculate discounts using the functional core
        List<DiscountedProduct> discountedProducts = calculateDiscounts(products);

        // Process the order and display the invoice using the imperative shell
        processOrder(discountedProducts);
    }


}

