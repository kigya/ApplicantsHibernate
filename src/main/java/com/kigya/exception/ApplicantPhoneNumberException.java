package com.kigya.exception;

public final class ApplicantPhoneNumberException extends Exception {

    public ApplicantPhoneNumberException() {
        super();
    }

    public ApplicantPhoneNumberException(String message) {
        super(message);
    }

    public ApplicantPhoneNumberException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicantPhoneNumberException(Throwable cause) {
        super(cause);
    }

    public ApplicantPhoneNumberException(String message, Throwable cause,
                                         boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
