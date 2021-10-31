package com.company.exchange.exception;
/**
 * This is application general InvalidCurenncyException
 * @author makari.tohid@gmail.com
 */
public class InvalidCurenncyException extends RuntimeException {

    public InvalidCurenncyException() {
    }

    public InvalidCurenncyException(String message) {
        super(message);
    }

    public InvalidCurenncyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCurenncyException(Throwable cause) {
        super(cause);
    }

    public InvalidCurenncyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
