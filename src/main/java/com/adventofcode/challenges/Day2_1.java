package com.adventofcode.challenges;

import com.adventofcode.Main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day2_1 {
    public static void Run() throws IOException {
        System.out.println("Advent of code, day 2 number 1");

        Map<String, Integer> loadedInBag = Map.of(
                "red", 12,
                "green", 13,
                "blue", 14
        );

        int result = 0;

        // get the current working dir
        Path inputFile = Paths.get(Main.ResourceDir.toString(), "input-day-2.txt");
        List<String> lines = Files.readAllLines(inputFile);

        Pattern p = Pattern.compile("Game (?<gameId>\\d+):\\s(?<gameData>.*?)$");
        for(String line : lines) {
            Matcher m = p.matcher(line);
            if(m.find()) {
                String gameId = m.group("gameId");
                String gameData = m.group("gameData");

                List<String[]> rounds = Arrays.stream(gameData.split(";")).map(round -> round.split(",")).toList();

                boolean possible = true;

                for(String[] round : rounds) {
                    for(String cube : round) {
                        Matcher cubeMatcher = Pattern.compile("(?<amount>\\d+)\\s(?<cube>.*)").matcher(cube);

                        if(cubeMatcher.find()) {
                            Integer amount = Integer.parseInt(cubeMatcher.group("amount"));
                            String cubeType = cubeMatcher.group("cube");

                            if (loadedInBag.getOrDefault(cubeType, -1) < amount) {
                                possible = false;
                            }
                        } else {
                            boolean test = true;
                        }
                    }
                }

                if(possible) {
                    result += Integer.parseInt(gameId);
                } else {
                    System.out.println(gameId + " is not possible");
                }
            }
        }

        System.out.println("Result: " + result);
        var l = lines;
    }
}
