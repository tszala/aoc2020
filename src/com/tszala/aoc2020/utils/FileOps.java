package com.tszala.aoc2020.utils;

import com.tszala.aoc2020.day1.Day1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileOps {
    public static List<Integer> getInputAsNumbers(InputStream input) {
        return getInputWithConverter(input, Integer::valueOf);
    }

    public static List<String> getInputAsText(InputStream input) {
        return getInputWithConverter(input, Function.identity());
    }

    private static <T> List<T> getInputWithConverter(InputStream inputStream, Function<String, T> converter) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        return reader.lines().map(converter::apply).collect(Collectors.toList());
    }
}
