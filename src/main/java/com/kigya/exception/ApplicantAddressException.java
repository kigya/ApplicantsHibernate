package com.kigya.exception;

public final class ApplicantAddressException extends Exception {

    public ApplicantAddressException() {
        super();
    }

    public ApplicantAddressException(String message) {
        super(message);
    }

    public ApplicantAddressException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicantAddressException(Throwable cause) {
        super(cause);
    }

    public ApplicantAddressException(String message, Throwable cause,
                                     boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
