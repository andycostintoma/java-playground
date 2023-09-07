package com.andy.part1_basics.lambdas;

import java.util.List;
import java.util.Locale;
import java.util.function.Function;

/**
 * <p>Designing APIs to align with functional programming principles often involves providing lambda factories to simplify the use of higher-order functions. These factories allow you to create lambda expressions for common operations, making your code more concise and reusable.</p>
 *
 * <p>Consider the example of a {@code ProductCategory} type that has a method to retrieve a localized description based on a given {@code Locale}. Although this method doesn't match a specific functional interface, you can create a lambda factory to make it compatible with higher-order functions.</p>
 */
public class LambdaFactories {


    record ProductCategory(String name) {

        /**
         * <p>Get the localized description of the product category based on the specified {@code Locale}.</p>
         *
         * @param locale The {@code Locale} for which to retrieve the localized description.
         * @return The localized description of the product category.
         */
        public String localizedDescription(Locale locale) {
            return switch (locale.getLanguage()) {
                case "de" -> "Kategorie: " + name;
                case "fr" -> "Catégorie : " + name;
                default -> "Category: " + name;
            };
        }

        /**
         * <p>Creates a lambda factory for mapping a {@code ProductCategory} instance to its localized description based on a specified {@code Locale}.</p>
         *
         * @param locale The {@code Locale} for which to create the mapping lambda.
         * @return A {@code Function} that maps a {@code ProductCategory} to its localized description.
         */
        public static Function<ProductCategory, String> localizedDescriptionMapper(Locale locale) {
            return category -> category.localizedDescription(locale);
        }
    }

    public static void main(String[] args) {

        // Sample ProductCategory instances
        List<ProductCategory> categories = List.of(
                new ProductCategory("Electronics"),
                new ProductCategory("Clothing"),
                new ProductCategory("Books")
        );

        // Define the target Locale
        Locale locale = Locale.GERMAN;

        // Map ProductCategory instances to localized descriptions using a lambda factory
        List<String> localizedDescriptions = categories.stream()
                .map(ProductCategory.localizedDescriptionMapper(locale))
                .toList();

        // Display the localized descriptions
        localizedDescriptions.forEach(System.out::println);
    }


}
