package com.redhat.training;

import javax.enterprise.context.ApplicationScoped;

import com.redhat.training.books.Book;
import com.redhat.training.books.BookNotAvailableException;
import com.redhat.training.inventory.Inventory;


@ApplicationScoped
public class Library {

    private final Inventory inventory;
    private final LoanRegistry loans = new LoanRegistry();

    public Library(Inventory inventory) {
        this.inventory = inventory;
    }

    public void checkout(String studentId, String isbn) throws BookNotAvailableException {
        if (!inventory.isBookAvailable(isbn)) {
            throw new BookNotAvailableException(isbn);
        }

        Book book = inventory.withdraw(isbn);
        loans.markAsBorrowed(studentId, book);

    }

    public double getAvailabilityRate() {
        return 1 - ((double) loans.count() / inventory.count());
    }
}

