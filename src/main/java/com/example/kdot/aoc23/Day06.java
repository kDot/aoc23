package com.example.kdot.aoc23;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Day06 {

    static Logger log = Logger.getLogger(Day06.class.getName());

    public static void main(String[] args) throws IOException, URISyntaxException {
        var d = new Day00("day06.input.txt");
        var input = d.readInput();
        log.log(Level.INFO, "Result Part01 {0}", new Object[] { d.calculatePart01(input) });
        // log.log(Level.INFO, "Result Part02 {0}", new
        // Object[]{d.calculatePart02(input)});
    }

    private final List<String> rawInput;

    Day06(String filename) {
        try {
            Path p = Paths.get(ClassLoader.getSystemResource(filename).toURI());
            this.rawInput = Files.readAllLines(p);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    Model readInput() {
        var model = new Day06.Model();
        var durationLine = rawInput.get(0);
        var recordLine = rawInput.get(1);

        var durationsAsString = durationLine.substring(durationLine.indexOf(":") + 1).trim().split(" ");
        var durations = Arrays.asList(durationsAsString).stream().filter(s -> !s.isBlank()).mapToInt(i -> Integer.valueOf(i))
                .toArray();
        var recordsAsString = recordLine.substring(recordLine.indexOf(":") + 1).trim().split(" ");
        var records = Arrays.asList(recordsAsString).stream().filter(s -> !s.isBlank()).mapToInt(i -> Integer.valueOf(i))
                .toArray();

        assert durations.length == records.length;

        for (int i = 0; i < durations.length; ++i) {
            var race = new Race();
            race.duration = durations[i];
            race.record = records[i];
            model.races.add(race);
        }

        return model;
    }

    int calculatePart01(Day06.Model input) {
        return 0;
    }

    int calculatePart02(Day06.Model input) {
        return 0;
    }

    class Model {
        List<Race> races = new ArrayList<>();

    }

    class Race {
        int duration;
        int record;
    }

}
