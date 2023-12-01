package com.adventofcode;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static Path ResourceDir;

    public static void main(String[] args) {
        ResourceDir = Paths.get(new File("").getAbsolutePath(), "resources");

        try {
//            com.adventofcode.challenges.Day1_1.Run();
            com.adventofcode.challenges.Day1_2.Run();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}