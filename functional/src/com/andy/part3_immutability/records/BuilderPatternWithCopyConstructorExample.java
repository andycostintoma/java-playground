package com.andy.part3_immutability.records;

public class BuilderPatternWithCopyConstructorExample {

    public record Point(int x, int y) {

        public static final class Builder {

            private int x;
            private int y;

            public Builder(Point point) {
                this.x = point.x();
                this.y = point.y();
            }

            public Builder x(int x) {
                this.x = x;
                return this;
            }

            public Builder y(int y) {
                this.y = y;
                return this;
            }

            public Point build() {
                return new Point(this.x, this.y);
            }
        }
    }

    public static void main(String[] args) {
        Point original = new Point(23, 42);
        System.out.println("Original point: " + original);

        Point updated = new Point.Builder(original)
                            .x(5)
                            .build();
        System.out.println("Updated point: " + updated);
    }
}
