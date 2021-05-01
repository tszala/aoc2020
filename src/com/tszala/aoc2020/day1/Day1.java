package com.tszala.aoc2020.day1;

import com.tszala.aoc2020.utils.FileOps;
import com.tszala.aoc2020.utils.Tuple;

import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Day1 {

    public static void main(String[] args) {
        // write your code here
        System.out.println("Day 1 of Advent of Code");
        List<Integer> numbers = FileOps.getInputAsNumbers(Day1.class.getResourceAsStream("input.txt"));
        List<Integer> numbersLessThan2020 = numbers.stream().filter(n -> n < 2020).collect(Collectors.toList());
        Optional<Tuple<Integer, Integer>> sumTo2020 = numbersLessThan2020
                .stream()
                .map(n -> new Tuple<>(n, numbersLessThan2020))
                .map(Day1::zip)
                .flatMap(List::stream)
                .filter(tuple -> tuple.left + tuple.right == 2020)
                .findFirst();
        sumTo2020.ifPresentOrElse(t-> System.out.println("Quiz one answer is " + t.left * t.right),
                () -> System.out.println("Quiz one answer not present"));

        Optional<Tuple<Tuple<Integer,Integer>, Integer>> sumTo2020OfThree = numbersLessThan2020
                .stream()
                .map(n -> new Tuple<>(n, numbersLessThan2020))
                .map(Day1::zip)
                .flatMap(List::stream)
                .filter(tuple -> tuple.left + tuple.right < 2020)
                .map(t -> new Tuple<>(t, numbersLessThan2020))
                .map(Day1::zip)
                .flatMap(List::stream)
                .filter(t -> t.right + t.left.left + t.left.right == 2020)
                .findFirst();
        sumTo2020OfThree.ifPresentOrElse(t-> System.out.println("Quiz two answer is " + t.left.left * t.left.right * t.right),
                () -> System.out.println("Quiz two anser not present"));

    }

    private static <T> List<Tuple<T, Integer>> zip(Tuple<T, List<Integer>> tuple) {
        return tuple.right.stream().map(n -> new Tuple<>(tuple.left, n)).collect(Collectors.toList());
    }
}
