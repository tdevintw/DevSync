package dev.yassiraitelghari.exceptions;

public class InvalidCredentialsException extends RuntimeException{
   public  InvalidCredentialsException(){
       super("Email or Password are Incorrect");
   }
}
