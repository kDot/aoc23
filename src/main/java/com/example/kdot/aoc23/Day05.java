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

public class Day05 {

    static Logger log = Logger.getLogger(Day00.class.getName());

    public static void main(String[] args) throws IOException, URISyntaxException {
        var d = new Day05("day05.input.txt");
        var input = d.readInput();
        log.log(Level.INFO, "Result Part01 {0}", new Object[] { d.calculatePart01(input) });
        log.log(Level.INFO, "Result Part02 {0}", new Object[] { d.calculatePart02(input) });
    }

    private final List<String> rawInput;

    Day05(String filename) {
        try {
            Path p = Paths.get(ClassLoader.getSystemResource(filename).toURI());
            this.rawInput = Files.readAllLines(p);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    Model readInput() {
        var model = new Day05.Model();
        SpecialMap currentMap = null;
        for (String line : rawInput) {
            switch (line) {
                case "seed-to-soil map:":
                    currentMap = model.seed2soil;
                    break;
                case "soil-to-fertilizer map:":
                    currentMap = model.soil2fertilizer;
                    break;
                case "fertilizer-to-water map:":
                    currentMap = model.fertilizer2water;
                    break;
                case "water-to-light map:":
                    currentMap = model.water2light;
                    break;
                case "light-to-temperature map:":
                    currentMap = model.light2temperature;
                    break;
                case "temperature-to-humidity map:":
                    currentMap = model.temperature2huminidy;
                    break;
                case "humidity-to-location map:":
                    currentMap = model.huminidy2location;
                    break;
                default:
                    if (line.trim().isEmpty()) {
                        currentMap = null;
                        break;
                    } else if (line.startsWith("seeds:")) {
                        Arrays.asList(line.substring(line.indexOf(":") + 1).trim().split(" ")).stream()
                                .map(s -> Long.parseLong(s))
                                .forEach(model.seeds::add);
                        break;
                    }
                    var lineAsArray = line.split(" ");
                    currentMap.addConfig(Long.parseLong(lineAsArray[0]), Long.parseLong(lineAsArray[1]),
                            Long.parseLong(lineAsArray[2]));
                    break;
            }
        }
        return model;
    }

    int calculatePart01(Day05.Model input) {
        var nearestLoc = input.seeds.stream() //
                .map(s -> input.seed2soil.getDestination(s))
                .map(soil -> input.soil2fertilizer.getDestination(soil))
                .map(fert -> input.fertilizer2water.getDestination(fert))
                .map(water -> input.water2light.getDestination(water))
                .map(light -> input.light2temperature.getDestination(light))
                .map(temp -> input.temperature2huminidy.getDestination(temp))
                .map(humidity -> input.huminidy2location.getDestination(humidity)) //
                .mapToLong(i -> i).min();
        return (int) nearestLoc.orElse(-1);
    }

    int calculatePart02(Day05.Model input) {
        // 'paging' to prevent long execution on the one hand and out of memory on the other
        List<Long> seedsNew = new ArrayList<>();
        long nearest = Long.MAX_VALUE;
        for (int i = 0; i < input.seeds.size(); i += 2) {
            for (long j = input.seeds.get(i); j < input.seeds.get(i) + input.seeds.get(i + 1); j++) {
                seedsNew.add(i + j);
                if (seedsNew.size() >= 10000) {
                    long loc = Arrays.asList(Long.valueOf(i + j)).stream() //
                            .map(s -> input.seed2soil.getDestination(s))
                            .map(soil -> input.soil2fertilizer.getDestination(soil))
                            .map(fert -> input.fertilizer2water.getDestination(fert))
                            .map(water -> input.water2light.getDestination(water))
                            .map(light -> input.light2temperature.getDestination(light))
                            .map(temp -> input.temperature2huminidy.getDestination(temp))
                            .map(humidity -> input.huminidy2location.getDestination(humidity)) //
                            .mapToLong(l -> l).min().orElse(Long.MAX_VALUE);
                    if (nearest > loc) {
                        nearest = loc;
                    }
                    seedsNew.clear();
                }
            }
        }

        return (int) nearest;
    }

    class Model {

        List<Long> seeds = new ArrayList<>();
        SpecialMap seed2soil = new SpecialMap();
        SpecialMap soil2fertilizer = new SpecialMap();
        SpecialMap fertilizer2water = new SpecialMap();
        SpecialMap water2light = new SpecialMap();
        SpecialMap light2temperature = new SpecialMap();
        SpecialMap temperature2huminidy = new SpecialMap();
        SpecialMap huminidy2location = new SpecialMap();
    }

    class SpecialMap {

        List<Config> configs = new ArrayList<>();

        void addConfig(long destRangeStart, long sourceRangeStart, long rangeLenght) {
            configs.add(new Config(sourceRangeStart, destRangeStart, rangeLenght));
        }

        long getDestination(long source) {
            for (Config config : configs) {
                if (config.sourceRangeStart <= source && source < config.sourceRangeStart + config.rangeLenght) {
                    return config.destRangeStart + source - config.sourceRangeStart;
                }
            }
            return source;
        }

    }

    class Config {
        long sourceRangeStart;
        long destRangeStart;
        long rangeLenght;

        public Config(long sourceRangeStart, long destRangeStart, long rangeLenght) {
            this.sourceRangeStart = sourceRangeStart;
            this.destRangeStart = destRangeStart;
            this.rangeLenght = rangeLenght;
        }

    }

}
