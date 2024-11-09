package org.project.bankingsystem.exception;

public class AccountantException extends RuntimeException{
    public AccountantException() {
    }

    public AccountantException(String message) {
        super(message);
    }
}
