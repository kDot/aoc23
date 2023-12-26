package com.example.kdot.aoc23;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day01 {

    static Logger log = Logger.getLogger(Day01.class.getName());

    public static void main(String[] args) throws IOException, URISyntaxException {
        var p = Paths.get(ClassLoader.getSystemResource("day01.input.txt").toURI());
        var lines = Files.readAllLines(p);

        var sum = lines.stream()
                .map(s -> {
                    var firstDigitPattern = Pattern.compile("^[^\\d]*(?<first>\\d)");
                    var lastDigitPattern = Pattern.compile("(?<last>\\d)[^\\d]*$");
                    var fDM = firstDigitPattern.matcher(s);
                    var lDM = lastDigitPattern.matcher(s);
                    fDM.find();
                    lDM.find();
                    var firstDigit = fDM.group("first");
                    var lastDigit = lDM.group("last");
                    return "" + firstDigit + lastDigit;
                })
                .map(Integer::valueOf)
                .collect(Collectors.summingInt(Integer::intValue));

        log.log(Level.INFO, "Result Part 01 {0}", new Object[] { sum });

        // part two

        sum = lines.stream()
                .map(s -> {
                    var result = s.replace("one", "on1e");
                    result = result.replace("two", "tw2o");
                    result = result.replace("three", "thr3ee");
                    result = result.replace("four", "fo4ur");
                    result = result.replace("five", "fi5ve");
                    result = result.replace("six", "si6x");
                    result = result.replace("seven", "sev7en");
                    result = result.replace("eight", "ei8ght");
                    result = result.replace("nine", "ni9ne");
                    return result;
                })
                .map(s -> {
                    var firstDigitPattern = Pattern.compile("^[^\\d]*(?<first>\\d)");
                    var lastDigitPattern = Pattern.compile("(?<last>\\d)[^\\d]*$");
                    var fDM = firstDigitPattern.matcher(s);
                    var lDM = lastDigitPattern.matcher(s);
                    fDM.find();
                    lDM.find();
                    var firstDigit = fDM.group("first");
                    var lastDigit = lDM.group("last");
                    return "" + firstDigit + lastDigit;
                })
                .map(Integer::valueOf)
                .collect(Collectors.summingInt(Integer::intValue));

        log.log(Level.INFO, "Result Part 02 {0}", new Object[] { sum });

        
    }
}