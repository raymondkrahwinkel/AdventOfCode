package com.adventofcode.challenges;

import com.adventofcode.Main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day2_2 {
    public static void Run() throws IOException {
        System.out.println("Advent of code, day 2 number 2");

        int result = 0;

        // get the current working dir
        Path inputFile = Paths.get(Main.ResourceDir.toString(), "input-day-2.txt");
        List<String> lines = Files.readAllLines(inputFile);

        Pattern p = Pattern.compile("Game (?<gameId>\\d+):\\s(?<gameData>.*?)$");
        for(String line : lines) {
            Matcher m = p.matcher(line);
            if(m.find()) {
                String gameData = m.group("gameData");
                List<String[]> rounds = Arrays.stream(gameData.split(";")).map(round -> round.split(",")).toList();

                HashMap<String, Integer> minimalCubes = new HashMap<>();

                for(String[] round : rounds) {
                    for(String cube : round) {
                        Matcher cubeMatcher = Pattern.compile("(?<amount>\\d+)\\s(?<cube>.*)").matcher(cube);

                        if(cubeMatcher.find()) {
                            Integer amount = Integer.parseInt(cubeMatcher.group("amount"));
                            String cubeType = cubeMatcher.group("cube");

                            if (minimalCubes.getOrDefault(cubeType, -1) < amount) {
                                minimalCubes.put(cubeType, amount);
                            }
                        }
                    }
                }

                int calculated = -1;
                for(Map.Entry<String, Integer> entry : minimalCubes.entrySet()) {
                    int amount = entry.getValue();
                    if(calculated == -1) {
                        calculated = amount;
                    } else {
                        calculated = (calculated * amount);
                    }
                };

                result += calculated;
            }
        }

        System.out.println("Result: " + result);
    }
}
