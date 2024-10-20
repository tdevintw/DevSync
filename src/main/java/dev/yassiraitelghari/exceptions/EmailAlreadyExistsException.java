package dev.yassiraitelghari.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{

    public EmailAlreadyExistsException(String email){
        super("Email "+ email  + " Already exist");
    }
}
