package com.adventofcode.challenges;

import com.adventofcode.Main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Day4_2 {
    public static void Run() throws IOException {
        System.out.println("Advent of code, day 4 number 2");

        int result = 0;

        // get the current working dir
        Path inputFile = Paths.get(Main.ResourceDir.toString(), "input-day-4.txt");
        List<String> lines = Files.readAllLines(inputFile);

        List<List<String[]>> cards = lines.stream().map(str -> str.replaceAll("Card\\s+(\\d+):\\s", ""))
                .map(l -> l.split("\\s\\|\\s"))
                .map(s -> Arrays.stream(s).map(b -> b.split("\\s+")).toList())
                .toList();

        HashMap<Integer, Integer> copiesPerCard = new HashMap<>();

        for(int c = 0; c < cards.size(); c++) {
            List<String[]> card = cards.get(c);

            List<String> intersectResult = Arrays.stream(card.get(0))
                    .distinct()
                    .filter(f -> Arrays.asList(card.get(1)).contains(f))
                    .collect(Collectors.toList());

            if(!copiesPerCard.containsKey(c)) {
                copiesPerCard.put(c, 1);
            }

            int currentCardCopies = copiesPerCard.get(c);

            for(int n = (c + 1); n < (intersectResult.size() + (c + 1)); n++) {
                int currentCopies = copiesPerCard.getOrDefault(n, 1);
                currentCopies = currentCopies + currentCardCopies;
                copiesPerCard.put(n, currentCopies);
            }
        }

        result = copiesPerCard.values().stream().toList().stream().mapToInt(Integer::intValue).sum();

        System.out.println("Result: " + result);
    }
}
