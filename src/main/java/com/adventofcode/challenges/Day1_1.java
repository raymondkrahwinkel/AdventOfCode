package com.adventofcode.challenges;

import com.adventofcode.Main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day1_1
{
    public static void Run() throws IOException {
        System.out.println("Advent of code, day 1 number 1");

        int result = 0;

        // get the current working dir
        Path inputFile = Paths.get(Main.ResourceDir.toString(), "input-day-1_1.txt");

        // create regex matcher to only match the numbers
        Pattern p = Pattern.compile("([1-9])");

        List<String> lines = Files.readAllLines(inputFile);

        for(String line : lines) {
           Matcher m = p.matcher(line);

            String firstMatch = "";
            String lastMatch = null;

            while(m.find()) {
                if(firstMatch.isEmpty()) {
                    firstMatch = m.group();
                }

                lastMatch = m.group();
            }

            if(firstMatch.isEmpty()) {
                continue;
            }

            int number = Integer.parseInt(firstMatch + lastMatch);
            result += number;
        }

        System.out.println(result);
    }
}
