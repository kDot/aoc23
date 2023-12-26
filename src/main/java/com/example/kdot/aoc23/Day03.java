package com.example.kdot.aoc23;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Day03 {

    static Logger log = Logger.getLogger(Day03.class.getName());

    public static void main(String[] args) throws IOException, URISyntaxException {
        Day03 d = new Day03("day03.input.txt");
        var input = d.readInput();
        log.log(Level.INFO, "Result Part01 {0}", new Object[]{d.calculatePart01(input)});
        log.log(Level.INFO, "Result Part02 {0}", new Object[]{d.calculatePart02(input)});
    }

    private List<String> rawInput;

    public Day03(String filename) throws IOException, URISyntaxException {
        var p = Paths.get(ClassLoader.getSystemResource(filename).toURI());
        this.rawInput = Files.readAllLines(p);
    }

     Model readInput() {
        Model model = new Model();
        model.data = new String[rawInput.size()][rawInput.get(0).length()];

        //x and y should be equal
        //assert rawInput.size() == rawInput.get(0).length();

        for (int i = 0; i < rawInput.size(); ++i) {
            for (int j = 0; j < rawInput.get(i).length(); ++j) {
                var value = rawInput.get(i).substring(j, j + 1);
                model.data[i][j] = value;
            }
        }

        return model;
    }

     int calculatePart01(Model model) {
        int summary = 0;
        for (int y = 0; y < model.data.length; ++y) {
            String currentNumber= "";
            boolean hasSymbolAround = false;
            for (int x = 0; x < model.data[0].length; ++x) {
                if (model.data[y][x].matches("[^\\d]") && !currentNumber.isEmpty() ) {
                    if (hasSymbolAround) {
                        summary += Integer.parseInt(currentNumber);
                    }
                    currentNumber="";
                    hasSymbolAround=false;
                }
                else if (model.data[y][x].matches("\\d")) {
                    if (!hasSymbolAround) {
                        hasSymbolAround=hasAnyAdjacentSymbol(model,x,y);
                    }
                    currentNumber=currentNumber+model.data[y][x];
                }
            }
            if (!currentNumber.isEmpty() && hasSymbolAround) {
                summary += Integer.parseInt(currentNumber);
            }
            currentNumber="";
            hasSymbolAround=false;
        }
        return summary;
    }

    boolean hasAnyAdjacentSymbol(Model model, int x, int y){
        return findAdjacentSymbol(model,x,y,"[^a-zA-Z0-9.]") != null;
    }

    Coordinate findStarAdjacentSymbol(Model model, int x, int y){
        return findAdjacentSymbol(model,x,y,"\\*");
    }

     Coordinate findAdjacentSymbol(Model model, int x, int y,String regexPatter) {
        if (y > 0) {
            //line above needs to be checked
            if (x > 1 && model.data[y - 1][x - 1].matches(regexPatter)) {
                return new Coordinate(x-1,y-1);
            }
            if (model.data[y-1][x].matches(regexPatter)) {
                return new Coordinate(x,y-1);
            }
            if (x < model.data[y-1].length - 1 && model.data[y -1][x+1].matches(regexPatter)) {
                return new Coordinate(x+1,y-1);
            }
        }

        //my line
        if (x > 1 && model.data[y][x-1].matches(regexPatter)) {
            return new Coordinate(x-1,y);
        }
        if (model.data[y][x].matches(regexPatter)) {
            return new Coordinate(x,y);
        }
        if (x < model.data[y].length - 1 && model.data[y][x+1].matches(regexPatter)) {
            return new Coordinate(x+1,y);
        }

        //nextline
        if (y< model.data.length-1) {
            if (x > 1 && model.data[y+1][x-1].matches(regexPatter)) {
                return new Coordinate(x-1,y+1);
            }
            if (model.data[y+1][x].matches(regexPatter)) {
                return new Coordinate(x,y+1);
            }
            if (x < model.data[y+1].length - 1 && model.data[y+1][x+1].matches(regexPatter)) {
                return new Coordinate(x+1,y+1);
            }
        }

        return null;
    }

    int calculatePart02(Model model) {
        Map<Coordinate, List<Integer>> newModel = new HashMap<>();
        for (int y = 0; y < model.data.length; ++y) {
            for (int x = 0; x < model.data[0].length; ++x) {
                if (model.data[y][x].matches("\\*")  ) {
                    newModel.put(new Coordinate(x,y), new ArrayList<>());
                }
            }
        }
        for (int y = 0; y < model.data.length; ++y) {
            String currentNumber= "";
            Coordinate adjacentStar = null;
            for (int x = 0; x < model.data[0].length; ++x) {

                if (model.data[y][x].matches("[^\\d]") && !currentNumber.isEmpty() ) {
                    if (adjacentStar != null) {
                        newModel.get(adjacentStar).add(Integer.parseInt(currentNumber));
                    }
                    currentNumber="";
                    adjacentStar=null;
                }
                else if (model.data[y][x].matches("\\d")) {
                    if (adjacentStar == null) {
                        adjacentStar = findStarAdjacentSymbol(model,x,y);
                    }
                    currentNumber=currentNumber+model.data[y][x];
                }
            }
            if (!currentNumber.isEmpty() && adjacentStar!= null) {
                newModel.get(adjacentStar).add(Integer.parseInt(currentNumber));
            }
            currentNumber="";
            adjacentStar=null;
        }

        var sum = newModel.keySet().stream() //
                .filter(key -> newModel.get(key).size()==2)
                .map(key -> newModel.get(key).get(0)*newModel.get(key).get(1))
                .mapToInt(i -> i)
                .sum();

        return sum;
    }

    public static class Model {
        String[][] data;
    }

    public static class Coordinate {

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int x;
        int y;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
