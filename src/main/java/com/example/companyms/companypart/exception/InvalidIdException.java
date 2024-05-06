package com.example.companyms.companypart.exception;

public class InvalidIdException extends RuntimeException{
    public InvalidIdException() {
    }

    public InvalidIdException(String message){
        super(message);
    }
}
