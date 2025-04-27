package com.andy.completable_futures;

import java.io.InputStream;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

/**
 * <p>
 * Illustrates the concept of pending CompletableFuture pipelines.
 * </p>
 *
 * <p>
 * A pending CompletableFuture instance never completes by itself with any state.
 * Instead, it provides a starting point for a more intricate task pipeline that can be executed later.
 * </p>
 *
 * <p>
 * In this example, we create a pending CompletableFuture pipeline for image processing.
 * The pipeline includes loading an image, processing it, and handling errors.
 * The pipeline is created using the createTask method and can be executed by completing the start stage.
 * </p>
 */

public class PendingCompletableFuturesPipelines{

static class ImageProcessor {

    public record Task(CompletableFuture<Path> start, CompletableFuture<InputStream> end) {
    }

    public Task createTask(int maxHeight, int maxWidth, boolean keepAspectRatio, boolean trimWhitespace) {
        var start = new CompletableFuture<Path>();

        var end = start.thenApply(this::loadImage)
                .exceptionally(this::handleImageProcessingError)
                .thenApply(image -> processImage(image, maxHeight, maxWidth, keepAspectRatio, trimWhitespace));

        return new Task(start, end);
    }

    private InputStream loadImage(Path path) {
        System.out.println("Loading image from " + path);
        // Simulate loading an image from a file
        return null;
    }

    private InputStream processImage(InputStream image, int maxHeight, int maxWidth, boolean keepAspectRatio, boolean trimWhitespace) {
        System.out.println("Processing image...");
        // Simulate image processing
        return image;
    }

    private InputStream handleImageProcessingError(Throwable ex) {
        System.err.println("Image processing error: " + ex.getMessage());
        return null;
    }

    public static void main(String[] args) {
        ImageProcessor imageProcessor = new ImageProcessor();

        var task = imageProcessor.createTask(800, 600, false, true);

        var imagePath = Path.of("image.jpg");
        task.start().complete(imagePath);

        try {
            var processedImage = task.end().get();
            System.out.println("Image processing completed.");
        } catch (Exception e) {
            System.err.println("Error accessing processed image: " + e.getMessage());
        }
    }
}
}