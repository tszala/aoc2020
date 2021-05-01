package com.tszala.aoc2020.day2;

import java.util.stream.IntStream;

public class PasswordWithPolicy {

    public final int lowerBound;
    public final int upperBound;
    public final char letter;
    public final String password;

    public PasswordWithPolicy(int lowerBound, int upperBound, char letter, String password) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        this.letter = letter;
        this.password = password;
    }

    public boolean isValid() {
        int numberOfAppearance = IntStream.range(0, password.length()).map(i -> password.charAt(i) == letter ? 1 : 0).sum();
        return lowerBound <= numberOfAppearance && numberOfAppearance <= upperBound;
    }

    public boolean isValid2() {
        boolean firstLetterMatches = lowerBound > 0
                && password.length() > lowerBound
                && password.charAt(lowerBound - 1) == letter;
        boolean secondLetterMatches = upperBound > 0
                && password.length() >= upperBound
                && password.charAt(upperBound - 1) == letter;

        return (firstLetterMatches && !secondLetterMatches) || (!firstLetterMatches && secondLetterMatches);
    }



}
