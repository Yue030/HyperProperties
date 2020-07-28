package com.yue.Hyper.Exception;

public class FileNotFoundException extends Exception{

    /**
     * File Not Found.
     */
    public FileNotFoundException() {
        super();
    }

    /**
     * File Not Found.
     * @param msg Message
     */
    public FileNotFoundException(String msg) {
        super(msg);
    }

    /**
     * File Not Found.
     * @param msg Message
     * @param cause Cause by
     */
    public FileNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
