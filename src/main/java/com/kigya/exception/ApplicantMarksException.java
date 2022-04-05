package com.kigya.exception;

public final class ApplicantMarksException extends Exception {

    public ApplicantMarksException() {
        super();
    }

    public ApplicantMarksException(String message) {
        super(message);
    }

    public ApplicantMarksException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicantMarksException(Throwable cause) {
        super(cause);
    }

    public ApplicantMarksException(String message, Throwable cause,
                                   boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
