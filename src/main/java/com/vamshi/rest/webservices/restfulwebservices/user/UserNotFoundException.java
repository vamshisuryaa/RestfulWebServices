package com.vamshi.rest.webservices.restfulwebservices.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    //Generate Constructor from superclass
    public UserNotFoundException(String message) {
        super(message);
    }







}
