package com.bodiukh.blog.exceptions;

public class EmailExistsException extends Exception {

    public EmailExistsException(final String message) {
        super(message);
    }
}
