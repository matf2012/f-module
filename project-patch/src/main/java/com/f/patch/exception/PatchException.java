package com.f.patch.exception;

/**
 * @Description:
 * @author: feng
 * @date: 2020-07-24
 */
public class PatchException extends RuntimeException {

    private String message;

    public PatchException(String message) {
        super(message);
        this.message = message;
    }

    public PatchException(String message,Throwable th) {
        super(message,th);
        this.message = message;
    }

}

