package com.tszala.aoc2020.day2;

import com.tszala.aoc2020.day1.Day1;
import com.tszala.aoc2020.utils.FileOps;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day2 {

    public static final String PASSWORD_TEXT_PATTERN = "(\\d+)-(\\d+) (\\w): (\\w+)";
    public static final Pattern passwordPattern = Pattern.compile(PASSWORD_TEXT_PATTERN);

    public static void main(String[] args) {
        System.out.println("Advent of Code Day 2");
        List<String> inputAsText = FileOps.getInputAsText(Day2.class.getResourceAsStream("input.txt"));
        List<PasswordWithPolicy> passwordsWithPolicy = inputAsText.stream()
                .map(passwordPattern::matcher)
                .filter(m -> m.matches() && m.groupCount() == 4)
                .map(m -> new PasswordWithPolicy(Integer.parseInt(m.group(1)),
                        Integer.parseInt(m.group(2)), m.group(3).charAt(0), m.group(4)))
                .collect(Collectors.toList());
        System.out.println("Number of passwords " + passwordsWithPolicy.size());
        long count = passwordsWithPolicy.stream().filter(PasswordWithPolicy::isValid).count();
        System.out.println("The quiz 1 answer is " + count);
        long countQuiz2 = passwordsWithPolicy.stream().filter(PasswordWithPolicy::isValid2).count();
        System.out.println("The quiz 2 answer is " + countQuiz2);
    }
}
