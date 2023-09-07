package com.andy.part4_streams.basics;

import java.util.ArrayList;
import java.util.List;

public class ExternalVsInternalIterationExample {

    record Book(String title, int year, Genre genre) {
    }

    enum Genre {
        SCIENCE_FICTION,
        FANTASY,
        MYSTERY,
        ROMANCE
    }
    
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Dune", 1965, Genre.SCIENCE_FICTION));
        books.add(new Book("1984", 1949, Genre.SCIENCE_FICTION));
        books.add(new Book("Brave New World", 1932, Genre.SCIENCE_FICTION));

        externalIteration(books);

        List<String> result = books.stream()
                .filter(book -> book.year() < 1960)
                .filter(book -> book.genre() == Genre.SCIENCE_FICTION)
                .map(Book::title)
                .limit(3)
                .toList();

        System.out.println("Result using internal iteration (Java Streams): " + result);
    }


    private static void externalIteration(List<Book> books) {
        List<String> result = new ArrayList<>();

        for (var book : books) {
            if (book.year() >= 1960) {
                continue;
            }

            if (book.genre() != Genre.SCIENCE_FICTION) {
                continue;
            }

            var title = book.title();
            result.add(title);

            if (result.size() == 3) {
                break;
            }
        }

        System.out.println("Result using external iteration: " + result);
    }
}
