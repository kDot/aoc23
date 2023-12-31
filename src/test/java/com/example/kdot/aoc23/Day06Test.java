package com.example.kdot.aoc23;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day06Test {

    @Test
    void testReadInput() {
        var target = new Day06("day06.part01.testInput.txt");
        var result = target.readInput();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(3, result.races.size());
    }

    @Test
    void testCalculatePart01() {
        var target = new Day06("day06.part01.testInput.txt");
        Assertions.assertEquals(288,target.calculatePart01(target.readInput()));
    }

    @Test
    void testCalculatePart02() {
        var target = new Day06("day06.part01.testInput.txt");
        Assertions.assertEquals(71503,target.calculatePart02(target.readInput()));
    }

}
