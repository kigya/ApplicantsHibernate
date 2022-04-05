package com.kigya.exception;

public final class ApplicantSurnameException extends Exception {

    public ApplicantSurnameException() {
        super();
    }

    public ApplicantSurnameException(String message) {
        super(message);
    }

    public ApplicantSurnameException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicantSurnameException(Throwable cause) {
        super(cause);
    }

    public ApplicantSurnameException(String message, Throwable cause,
                                     boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
