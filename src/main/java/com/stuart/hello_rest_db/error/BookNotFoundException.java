package com.stuart.hello_rest_db.error;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(Long id) {
        super("Book id not found: " + id);
    }
}
