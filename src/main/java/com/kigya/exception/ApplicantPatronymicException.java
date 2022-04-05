package com.kigya.exception;

public final class ApplicantPatronymicException extends Exception {

    public ApplicantPatronymicException() {
        super();
    }

    public ApplicantPatronymicException(String message) {
        super(message);
    }

    public ApplicantPatronymicException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicantPatronymicException(Throwable cause) {
        super(cause);
    }

    public ApplicantPatronymicException(String message, Throwable cause,
                                        boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
