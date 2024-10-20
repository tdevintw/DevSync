package dev.yassiraitelghari.exceptions;

public class InvalidEmailFormatException extends RuntimeException{
    public InvalidEmailFormatException(String email){
        super("Email "+email+" doesn't follow a valid format");
    }
}
