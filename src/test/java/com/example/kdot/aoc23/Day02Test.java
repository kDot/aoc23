package com.example.kdot.aoc23;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day02Test {

    @Test
    void testPart01() throws IOException, URISyntaxException {
        Day02 target = new Day02("day02.part01.testInput.txt");
        Assertions.assertEquals(8, target.calculateResultPart01(target.readInput()));
    }

    @Test
    void testPart02() throws IOException, URISyntaxException {
        Day02 target = new Day02("day02.part02.testInput.txt");
        Assertions.assertEquals(2286, target.calculateResultPart02(target.readInput()));
    }
    
}
