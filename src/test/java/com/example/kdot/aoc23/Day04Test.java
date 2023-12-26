package com.example.kdot.aoc23;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day04Test {

    @Test
    public void testReadInput() {
        Day04 target = new Day04("day04.part01.testInput.txt");
        var result = target.readInput();

        Assertions.assertEquals(6, result.cards.size());
        Assertions.assertEquals(5, result.cards.get(0).winningNumbers.size());
        Assertions.assertEquals(8, result.cards.get(0).myNumbers.size());
        Assertions.assertEquals(6, result.cards.getLast().id);
    }

    @Test
    public void testCalculatePart01() {
        Day04 target = new Day04("day04.part01.testInput.txt");
        Assertions.assertEquals(13,target.calculatePart01(target.readInput()));
    }

    @Test
    public void testCalculatePart02() {
        Day04 target = new Day04("day04.part02.testInput.txt");
        var input = target.readInput();
        Assertions.assertEquals(6, input.cards.size());
        Assertions.assertEquals(30,target.calculatePart02(input));
    }

}
