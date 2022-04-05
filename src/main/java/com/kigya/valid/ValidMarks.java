package com.kigya.valid;

import com.kigya.utils.Contstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ValidMarks {

    @Contract(pure = true)
    public static boolean isValidMarks(@NotNull List<Integer> marksList) {
        return (!marksList.isEmpty() && marksList.size() <=
                Contstants.MAX_MARKS_AMOUNT &&
                marksList.stream().allMatch(integer -> integer >= 0));
    }
}
