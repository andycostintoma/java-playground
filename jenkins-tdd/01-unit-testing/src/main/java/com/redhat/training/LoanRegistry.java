package com.redhat.training;

import com.redhat.training.books.Book;

import java.util.ArrayList;
import java.util.HashMap;

public class LoanRegistry {

    private final HashMap<String, ArrayList<Book>> loans = new HashMap<>();

    public void markAsBorrowed(String studentId, Book book) {
        if (!loans.containsKey(studentId)) {
            loans.put(studentId, new ArrayList<>());
        }
        loans.get(studentId).add(book);
    }

    public int count() {
        int count = 0;
        for (ArrayList<Book> studentLoans : loans.values()) {
            count += studentLoans.size();
        }
        return count;
    }
}
