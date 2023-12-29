package com.example.kdot.aoc23;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

class Day03Test {

    @Test
    void testReadDOMPart01() throws IOException, URISyntaxException {
        Day03 target = new Day03("day03.part01.testInput.txt");
        var result = target.readInput();
        Assertions.assertEquals(10, result.data.length);
        Assertions.assertEquals(10, result.data[9].length);

        Assertions.assertEquals(".....+.58.",String.join("",result.data[5]));
    }

    @Test
    void testHasAnyAdjacentSymbol() throws IOException, URISyntaxException {
        Day03 target = new Day03("day03.part01.testInput.txt");
        Day03.Model m = new Day03.Model();
        m.data= new String[][]{{".", ".", "."}, {".", ".", "."}, {".", ".", "."}};

        Assertions.assertFalse(target.hasAnyAdjacentSymbol(m,1,1));
        Assertions.assertFalse(target.hasAnyAdjacentSymbol(m,0,0));
        Assertions.assertFalse(target.hasAnyAdjacentSymbol(m,2,2));

        m.data= new String[][]{{".", ".", ".", "."}, {".", ".", ".", "."}, {".", "*", ".", "."}};
        Assertions.assertTrue(target.hasAnyAdjacentSymbol(m,1,1));
        Assertions.assertTrue(target.hasAnyAdjacentSymbol(m,2,2));
        Assertions.assertTrue(target.hasAnyAdjacentSymbol(m,1,2));
        Assertions.assertTrue(target.hasAnyAdjacentSymbol(m,0,1));
        Assertions.assertFalse(target.hasAnyAdjacentSymbol(m,0,0));

        m.data= new String[][]{{".", ".", "."}, {".", ".", "*"}, {".", ".", "."}};
        Assertions.assertTrue(target.hasAnyAdjacentSymbol(m,1,0));


    }

    @Test
    void testPart01() throws IOException, URISyntaxException {
        Day03 target = new Day03("day03.part01.testInput.txt");
        Assertions.assertEquals(4361, target.calculatePart01(target.readInput()));

        target = new Day03("day03.part01A.testInput.txt");
        var input = target.readInput();
        Assertions.assertEquals(12325, target.calculatePart01(input) );
    }

    @Test
    void testPart02() throws IOException, URISyntaxException {
        Day03 target = new Day03("day03.part02.testInput.txt");
        Assertions.assertEquals(467835, target.calculatePart02(target.readInput()));
    }
}
