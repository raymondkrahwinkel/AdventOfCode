
package com.adventofcode.challenges;

import com.adventofcode.Main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3_2 {
    public static void Run() throws IOException {
        System.out.println("Advent of code, day 3 number 2");

        int result = 0;

        // get the current working dir
        Path inputFile = Paths.get(Main.ResourceDir.toString(), "input-day-3.txt");
        List<String> lines = Files.readAllLines(inputFile);
        List<char[]> splittedLines = lines.stream().map(String::toCharArray).toList();

        HashMap<String, Integer> numbers = new HashMap<>();

        // get the positions for the numbers per line
        for(int i = 0; i < lines.size(); i++) {
            Matcher m = Pattern.compile("\\d+").matcher(lines.get(i));
            while(m.find()) {
                int number = Integer.parseInt(m.group());
                int startLine = Math.max((i - 1), 0);
                int endLine = Math.min((i + 1), lines.size());

                if(endLine >= lines.size()) {
                    endLine = lines.size() - 1;
                }

                for(int l = startLine; l <= endLine; l++) {
                    // check every character within the matching range
                    for (int c = (Math.max((m.start() - 1), 0)); c <= (m.end() >= splittedLines.get(l).length ? (m.end() - 1) : m.end()); c++) {
                        if (!Character.isDigit(splittedLines.get(l)[c]) && splittedLines.get(l)[c] != '.' && splittedLines.get(l)[c] == '*') {
                            String key = l + "-" + c;
                            if(numbers.containsKey(key)) {
                                // multiple
                                result += (numbers.get(key) * number);
                            } else {
                                numbers.put(key, number);
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Result: " + result);
    }
}
