package com.adventofcode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static Path ResourceDir;

    public static void main(String[] args) {
        ResourceDir = Paths.get(new File("").getAbsolutePath(), "resources");

        try {
//            com.adventofcode.challenges.Day1_1.Run();
//            com.adventofcode.challenges.Day1_2.Run();
//            com.adventofcode.challenges.Day2_1.Run();
//            com.adventofcode.challenges.Day2_2.Run();
//            com.adventofcode.challenges.Day3_1.Run();
//            com.adventofcode.challenges.Day3_2.Run();
//            com.adventofcode.challenges.Day4_1.Run();
//            com.adventofcode.challenges.Day4_2.Run();
//            com.adventofcode.challenges.Day5_1.Run();
            com.adventofcode.challenges.Day5_2.Run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> readFileToLines(String filename) throws IOException {
        // get the current working dir
        Path inputFile = Paths.get(Main.ResourceDir.toString(), filename);
        return Files.readAllLines(inputFile);
    }
}