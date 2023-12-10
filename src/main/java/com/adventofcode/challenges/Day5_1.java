package com.adventofcode.challenges;

import com.adventofcode.Main;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day5_1 {
    public static void Run() throws IOException {
        System.out.println("Advent of code, day 5 number 1");

        // get the file contents seperated into lines
        List<String> lines = Main.readFileToLines("input-day-5.txt");

        // first line are the seeds
        String seedLine = lines.get(0).replaceFirst("seeds:\\s+", "");

        // extract the seeds
        Long[] seeds = Arrays.stream(seedLine.split("\\s+")).map(s -> Long.parseLong(s)).toList().toArray(new Long[0]);

        HashMap<Long, Long> seedPositions = new HashMap<>();
        for(long seed : seeds) {
            seedPositions.put(seed, seed);
        }

        HashMap<String, HashMap<Long, Long>> mappings = new HashMap<>();
        String currentMap = "";

        // lets go through the lines and start mapping
        for(String line : lines) {
            if(Pattern.matches("([a-z\\-]+)\\s+map:", line)) {
                // register new map
                currentMap = line.split("\\s+", 2)[0];
                mappings.put(currentMap, new HashMap<>());
            } else if(Pattern.matches("(?<destination>([0-9]+))\\s(?<start>([0-9]+))\\s(?<length>([0-9]+))", line)) {
                Matcher m = Pattern.compile("(?<destination>([0-9]+))\\s(?<start>([0-9]+))\\s(?<length>([0-9]+))").matcher(line);
                if(m.find()) {
                    long destinationRangeStart = Long.parseLong(m.group("destination"));
                    long sourceRangeStart = Long.parseLong(m.group("start"));
                    int length = Integer.parseInt(m.group("length"));

                    List<Map.Entry<Long, Long>> foundSeeds = seedPositions.entrySet().stream()
                            .filter(pos -> sourceRangeStart <= pos.getValue() && (sourceRangeStart + (length - 1)) >= pos.getValue())
                            .toList();

                    for(Map.Entry<Long, Long> foundSeed : foundSeeds) {
                        // calculate the position from the source start
                        if(!mappings.get(currentMap).containsKey(foundSeed.getKey())) {
                            long newPosition = (destinationRangeStart + (foundSeed.getValue() - sourceRangeStart));
                            seedPositions.put(foundSeed.getKey(), newPosition);

                            mappings.get(currentMap).put(foundSeed.getKey(), newPosition);
                        }
                    }
                }
            }
        }

        System.out.println("Lowest position: " + seedPositions.values().stream().min(Long::compare).get());
    }
}
