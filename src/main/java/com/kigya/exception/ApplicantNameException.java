package com.kigya.exception;

public final class ApplicantNameException extends Exception {

    public ApplicantNameException() {
        super();
    }

    public ApplicantNameException(String message) {
        super(message);
    }

    public ApplicantNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicantNameException(Throwable cause) {
        super(cause);
    }

    public ApplicantNameException(String message, Throwable cause,
                                  boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
