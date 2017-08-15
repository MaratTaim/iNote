package com.epam.inote.exception;

/**
 * Created by User on 10.08.2017.
 *
 */
public class INoteException extends RuntimeException{

    public INoteException() {
        super();
    }

    public INoteException(String message) {
        super(message);
    }

    public INoteException(String message, Throwable cause) {
        super(message, cause);
    }

    public INoteException(Throwable cause) {
        super(cause);
    }
}
