package com.adventofcode.challenges;

import com.adventofcode.Main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1_2 {
    public static void Run() throws IOException {
        System.out.println("Advent of code, day 1 number 2");

        int result = 0;

        Map<String, Integer> textToNumber = Map.of(
                "one", 1,
                "two", 2,
                "three", 3,
                "four", 4,
                "five", 5,
                "six", 6,
                "seven", 7,
                "eight", 8,
                "nine", 9
        );

        // get the current working dir
        Path inputFile = Paths.get(Main.ResourceDir.toString(), "input-day-1_2.txt");

        // create regex matcher to only match the numbers
        String patternStr = "([1-9]|" + String.join("|", textToNumber.keySet().stream().toList()) +")";
        Pattern p = Pattern.compile(patternStr);

        List<String> lines = Files.readAllLines(inputFile);

        for(String line : lines) {
            if(line.isEmpty()) {
                continue;
            }

            Matcher m = p.matcher(line);

            String firstMatch = "";
            String lastMatch = null;

            int startPosition = 0;
            while(m.find(startPosition)) {
                if(firstMatch.isEmpty()) {
                    firstMatch = m.group();
                }

                lastMatch = m.group();

                // move the matcher position to fix the overlap of for example 'oneeight' which should result in 'eight' for the last match
                startPosition++;
            }

            if(firstMatch.isEmpty()) {
                continue;
            }

            if(textToNumber.containsKey(firstMatch)) {
                firstMatch = textToNumber.get(firstMatch).toString();
            }

            if(textToNumber.containsKey(lastMatch)) {
                lastMatch = textToNumber.get(lastMatch).toString();
            }

            int number = Integer.parseInt(firstMatch + lastMatch);
            result += number;
        }

        System.out.println(result);
    }
}
