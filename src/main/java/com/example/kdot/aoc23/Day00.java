package com.example.kdot.aoc23;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Day00 {

    static Logger log = Logger.getLogger(Day00.class.getName());

    public static void main(String[] args) throws IOException, URISyntaxException {
        var d = new Day00("day00.input.txt");
        var input = d.readInput();
        log.log(Level.INFO, "Result Part01 {0}", new Object[] { d.calculatePart01(input) });
        // log.log(Level.INFO, "Result Part02 {0}", new
        // Object[]{d.calculatePart02(input)});
    }

    private final List<String> rawInput;

    Day00(String filename) {
        try {
            Path p = Paths.get(ClassLoader.getSystemResource(filename).toURI());
            this.rawInput = Files.readAllLines(p);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    Model readInput() {
        var model = new Day00.Model();
        rawInput.forEach(line -> line.trim());

        return model;
    }

    int calculatePart01(Day00.Model input) {
        return 0;
    }

    int calculatePart02(Day00.Model input) {
        return 0;
    }

    class Model {

    }

}
