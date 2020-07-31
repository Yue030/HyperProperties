package com.yue.Hyper.Exception;

public class FileNotExistException extends Exception{

    /**
     * File Not Found.
     */
    public FileNotExistException() {
        super();
    }

    /**
     * File Not Found.
     * @param msg Message
     */
    public FileNotExistException(String msg) {
        super(msg);
    }

    /**
     * File Not Found.
     * @param msg Message
     * @param cause Cause by
     */
    public FileNotExistException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

