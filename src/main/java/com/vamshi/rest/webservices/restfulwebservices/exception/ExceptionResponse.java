package com.vamshi.rest.webservices.restfulwebservices.exception;

import java.util.Date;


//To create a message for exception with our own structure which the whole organization uses.
public class ExceptionResponse {

    //Structure should be language independent, there might be other resources which are exposed from node.js or .net etc
    private final Date timestamp;
    private final String message;
    private final String details;

    public ExceptionResponse(Date timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    //Setters are not needed
    //Generate only Getters

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}
