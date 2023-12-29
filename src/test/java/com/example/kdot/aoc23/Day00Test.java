package com.example.kdot.aoc23;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day00Test {

    @Test
    void testReadInput() {
        var target = new Day00("day00.part01.testInput.txt");
        var result = target.readInput();

        Assertions.assertNotNull(result);
    }

    @Test
    void testCalculatePart01() {
        var target = new Day00("day00.part01.testInput.txt");
        Assertions.assertEquals(00,target.calculatePart01(target.readInput()));
    }

    @Test
    void testCalculatePart02() {
        //var target = new Day00("day00.part02.testInput.txt");
        //var input = target.readInput();
       // Assertions.assertEquals(00,target.calculatePart02(target.readInput()));
    }

}
