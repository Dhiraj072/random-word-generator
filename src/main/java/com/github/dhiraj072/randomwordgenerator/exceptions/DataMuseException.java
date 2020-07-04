package com.github.dhiraj072.randomwordgenerator.exceptions;

/**
 * Something went wrong when trying to execute a request on DataMuse API,
 * or a bad/empty response was received
 */
public class DataMuseException extends Exception {

    public DataMuseException(String message, Throwable cause) {

        super(message, cause);
    }

    public DataMuseException(String message) {

        super(message);
    }
}
