package com.andy.part1_basics.concepts.lazy_evaluation;

public class VirtualProxyPatternExample {

    // Image interface
    public interface Image {
        void display();
    }

    // RealImage class
    public static class RealImage implements Image {
        private final String filename;

        public RealImage(String filename) {
            this.filename = filename;
            loadFromDisk();
        }

        private void loadFromDisk() {
            System.out.println("Loading image: " + filename);
        }

        @Override
        public void display() {
            System.out.println("Displaying image: " + filename);
        }
    }

    // ImageProxy class (Virtual Proxy)
    public static class ImageProxy implements Image {
        private RealImage realImage;
        private final String filename;

        public ImageProxy(String filename) {
            this.filename = filename;
        }

        @Override
        public void display() {
            if (realImage == null) {
                realImage = new RealImage(filename);
            }
            realImage.display();
        }
    }

    public static void main(String[] args) {
        Image image1 = new ImageProxy("image1.jpg");
        Image image2 = new ImageProxy("image2.jpg");

        // The real images are loaded only when display() is called
        image1.display();
        image2.display();

        // Subsequent calls to display() use the already loaded real images
        image1.display();
        image2.display();
    }
}
