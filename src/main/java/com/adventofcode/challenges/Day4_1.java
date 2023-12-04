package com.adventofcode.challenges;

import com.adventofcode.Main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4_1 {
    public static void Run() throws IOException {
        System.out.println("Advent of code, day 4 number 1");

        int result = 0;

        // get the current working dir
        Path inputFile = Paths.get(Main.ResourceDir.toString(), "input-day-4.txt");
        List<String> lines = Files.readAllLines(inputFile);

        List<List<String[]>> cards = lines.stream().map(str -> str.replaceAll("Card\\s+(\\d+):\\s", ""))
                .map(l -> l.split("\\s\\|\\s"))
                .map(s -> Arrays.stream(s).map(b -> b.split("\\s+")).toList())
                .toList();

        for(List<String[]> card : cards) {
            List<String> intersectResult = Arrays.stream(card.get(0))
                    .distinct()
                    .filter(f -> Arrays.asList(card.get(1)).contains(f))
                    .collect(Collectors.toList());

            int calc = 0;
            for(int i=0;i< intersectResult.size();i++) {
                if(calc > 0) {
                    calc = calc * 2;
                } else {
                    calc = 1;
                }
            }

            result += calc;
        }

        System.out.println("Result: " + result);
    }
}
