package com.kigya.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Formatter {

    public static @NotNull String correctProperNoun(@NotNull String properNoun) {
        return properNoun.substring(0, 1).toUpperCase() +
                properNoun.substring(1).toLowerCase().trim();
    }

    public static @NotNull String capitalizeWords(@NotNull String str) {
        String[] words = str.split("\\s");
        StringBuilder capitalizeWord = new StringBuilder();
        for (String w : words) {
            String first = w.substring(0, 1);
            String afterfirst = w.substring(1);
            capitalizeWord
                    .append(first.toUpperCase())
                    .append(afterfirst)
                    .append(" ");
        }
        return capitalizeWord.toString().trim();
    }

    @Contract("_ -> !null")
    public static @NotNull String marksToString(@NotNull List<Integer> marksList) {
        return marksList.toString()
                .replace("[", "")
                .replace("]", "")
                .trim();
    }

}
