package com.andy.part3_immutability.records;

public class RecordValidationExample {

    public record NeedsValidation(int x, int y) {
        public NeedsValidation {
            if (x < y) {
                throw new IllegalArgumentException("x must be equal or greater than y");
            }
        }
    }

    public record Time(int minutes, int seconds) {
        public Time {
            if (seconds >= 60) {
                int additionalMinutes = seconds / 60;
                minutes += additionalMinutes;
                seconds -= additionalMinutes * 60;
            }
        }
    }

    public static void main(String[] args) {
        try {
            NeedsValidation validationExample = new NeedsValidation(5, 10);
            System.out.println(validationExample); // This won't be reached due to the exception
        } catch (IllegalArgumentException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        Time time = new Time(12, 67);
        System.out.println(time);
    }
}
