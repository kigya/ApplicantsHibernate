package com.kigya.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Contstants {

    public static final int MAX_LENGHT_EXCEPTION = 30;
    public static final int MAX_LENGHT_EXCEPTION_ADDRESS = 50;
    public static final int MAX_MARKS_AMOUNT = 8;
    public static final String PHONE_NUMBER_REGEX =
            "/^\\+?\\d{1,3}?[-.\\s]?\\(?\\d{1,3}?\\)?[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$/";
    public static final String ADDRESS_REGEX = "[0-9]+[ |[a-zà-ú.,-]([ .,-]*[a-zà-ú0-9]*)*";
    public static final String SAVING_ERROR_TEXT =
            "Error with saving database contents to file";

}
