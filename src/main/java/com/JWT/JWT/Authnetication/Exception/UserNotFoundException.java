package com.JWT.JWT.Authnetication.Exception;
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(){
           super("User is not found");
    }

    public UserNotFoundException(String message){
        super(message);
    }
}
