package com.andy.part6_design_patterns;

public class StrategyFP {

    interface ShippingStrategy {
        void ship(Parcel parcel);
    }

    record Parcel(String contents) {
    }

    static final class ShippingStrategies {

        public static ShippingStrategy standardShipping() {
            return parcel
                    -> System.out.println("Shipping the parcel with standard shipping: " + parcel.contents());
        }

        public static ShippingStrategy expedited(boolean signatureRequired) {
            return parcel -> {
                String shippingMethod = signatureRequired ? "expedited shipping with signature" : "expedited shipping";
                System.out.println("Shipping the parcel with " + shippingMethod + ": " + parcel.contents());
            };
        }
    }

    static class ShippingService {
        void ship(Parcel parcel, ShippingStrategy strategy) {
            strategy.ship(parcel);
        }
    }

    public static void main(String[] args) {
        // Create a parcel
        Parcel parcel = new Parcel("Product123");

        // Create a shipping service
        ShippingService shippingService = new ShippingService();

        // Use shipping strategies
        shippingService.ship(parcel, ShippingStrategies.standardShipping());
        shippingService.ship(parcel, ShippingStrategies.expedited(true));
    }
}
