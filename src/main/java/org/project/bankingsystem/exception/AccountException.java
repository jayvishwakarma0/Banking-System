package org.project.bankingsystem.exception;

public class AccountException extends RuntimeException{
    public AccountException() {
    }

    public AccountException(String message) {
        super(message);
    }
}
