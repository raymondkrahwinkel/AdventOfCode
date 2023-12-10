package com.adventofcode.challenges;

import com.adventofcode.Main;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Day5_2 {
    public static void Run() throws IOException {
        System.out.println("Advent of code, day 5 number 2");

        long result = Long.MAX_VALUE;

        // get the file contents seperated into lines
        List<String> lines = Main.readFileToLines("input-day-5.txt");

        // first line are the seeds
        String seedLine = lines.get(0).replaceFirst("seeds:\\s+", "");

        // extract the seeds
        Long[] seeds = Arrays.stream(seedLine.split("\\s+")).map(s -> Long.parseLong(s)).toList().toArray(new Long[0]);

        for(int i = 0; i < seeds.length; i+=2) {
            long[] seedsLongPosArray = LongStream.rangeClosed(seeds[i], (seeds[i] + seeds[i + 1])).toArray();
            var doneKeysRef = new Object() {
                Boolean[] doneKeys = new Boolean[seedsLongPosArray.length];
            };

            // let's go through the lines and start mapping
            for (String line : lines) {
                if (Pattern.matches("([a-z\\-]+)\\s+map:", line)) {
                    // reset
                    doneKeysRef.doneKeys = new Boolean[seedsLongPosArray.length];
                } else if (Pattern.matches("(?<destination>([0-9]+))\\s(?<start>([0-9]+))\\s(?<length>([0-9]+))", line)) {
                    Matcher m = Pattern.compile("(?<destination>([0-9]+))\\s(?<start>([0-9]+))\\s(?<length>([0-9]+))").matcher(line);
                    if (m.find()) {
                        long destinationRangeStart = Long.parseLong(m.group("destination"));
                        long sourceRangeStart = Long.parseLong(m.group("start"));
                        int length = Integer.parseInt(m.group("length"));

                        IntStream.range(0, seedsLongPosArray.length)
                                .parallel()
                                .filter(index ->
                                        sourceRangeStart <= seedsLongPosArray[index] &&
                                        (sourceRangeStart + (length - 1)) >= seedsLongPosArray[index] &&
                                        (doneKeysRef.doneKeys[index] == null || !doneKeysRef.doneKeys[index])
                                )
                                .forEach(index -> {
                                    seedsLongPosArray[index] = (destinationRangeStart + (seedsLongPosArray[index] - sourceRangeStart));
                                    doneKeysRef.doneKeys[index] = true;
                                });
                    }
                }
            }

            // get the lowest value
            long minValue = Arrays.stream(seedsLongPosArray).min().getAsLong();
            if(minValue < result) {
                result = minValue;
            }
        }

        // 25834266 - to low
        System.out.println("Lowest position: " + result);
    }
}
