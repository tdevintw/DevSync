package dev.yassiraitelghari.exceptions;

public class InvalidInputWhileRegisteringException extends RuntimeException{
    public InvalidInputWhileRegisteringException(){

        super("First name , last name , role and password Are Required");
    }
}
