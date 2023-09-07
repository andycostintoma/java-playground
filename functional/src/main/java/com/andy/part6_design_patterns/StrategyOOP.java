package com.andy.part6_design_patterns;

public class StrategyOOP {

    interface ShippingStrategy {
        void ship(Parcel parcel);
    }

    record Parcel(String contents) {
    }

    static class StandardShipping implements ShippingStrategy {
        @Override
        public void ship(Parcel parcel) {
            System.out.println("Shipping the parcel with standard shipping: " + parcel.contents());
        }
    }

    static class ExpeditedShipping implements ShippingStrategy {
        private final boolean signatureRequired;

        ExpeditedShipping(boolean signatureRequired) {
            this.signatureRequired = signatureRequired;
        }

        @Override
        public void ship(Parcel parcel) {
            String shippingMethod = signatureRequired ? "expedited shipping with signature" : "expedited shipping";
            System.out.println("Shipping the parcel with " + shippingMethod + ": " + parcel.contents());
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

        // Create shipping strategies
        ShippingStrategy standardShipping = new StandardShipping();
        ShippingStrategy expeditedShippingWithSignature = new ExpeditedShipping(true);
        ShippingStrategy expeditedShippingWithoutSignature = new ExpeditedShipping(false);

        // Create a shipping service
        ShippingService shippingService = new ShippingService();

        // Ship the parcel using different strategies
        shippingService.ship(parcel, standardShipping);
        shippingService.ship(parcel, expeditedShippingWithSignature);
        shippingService.ship(parcel, expeditedShippingWithoutSignature);
    }
}
