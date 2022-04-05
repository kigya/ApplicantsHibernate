package com.kigya.valid;

import com.kigya.utils.Contstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidProperNoun {

    @Contract(pure = true)
    public static boolean isValidProperNoun(@NotNull String string) {
        return (!string.isBlank() && string.length() <=
                Contstants.MAX_LENGHT_EXCEPTION);
    }
}
