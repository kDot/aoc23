package com.example.kdot.aoc23;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day05Test {

    @Test
    public void testReadInput() {
        var target = new Day05("day05.part01.testInput.txt");
        var result = target.readInput();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(4, result.seeds.size());
        Assertions.assertEquals(4, result.fertilizer2water.configs.size());
    }

    @Test
    public void testCalculatePart01() {
        var target = new Day05("day05.part01.testInput.txt");
        Assertions.assertEquals(35,target.calculatePart01(target.readInput()));
    }

    @Test
    public void testCalculatePart02() {
        //reuse test input from part 01 by intend
        var target = new Day05("day05.part01.testInput.txt");
        Assertions.assertEquals(46,target.calculatePart02(target.readInput()));
    }

}
