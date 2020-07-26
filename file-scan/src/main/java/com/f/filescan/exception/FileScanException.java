package com.f.filescan.exception;

/**
 * 异常类
 * @Description:
 * @author: feng
 * @date: 2020-07-21
 */
public class FileScanException extends RuntimeException {

    private String message;

    public FileScanException(String message) {
        super(message);
        this.message = message;
    }

    public FileScanException(String message,Throwable th) {
        super(message,th);
        this.message = message;
    }

}
