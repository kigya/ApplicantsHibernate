package com.kigya.valid;

import com.kigya.utils.Contstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidPhoneNumber {

    @Contract(pure = true)
    public static boolean isValidPhoneNumber(String string) {
        return (!string.isBlank() && string.length() <=
                Contstants.MAX_LENGHT_EXCEPTION_ADDRESS &&
                string.matches(Contstants.PHONE_NUMBER_REGEX));
    }
}