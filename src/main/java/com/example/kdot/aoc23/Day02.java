package com.example.kdot.aoc23;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Day02 {

    static Logger log = Logger.getLogger(Day02.class.getName());

    public static void main(String[] args) throws IOException, URISyntaxException {
        Day02 d = new Day02("day02.input.txt");
        var input = d.readInput();
        log.log(Level.INFO, "Result Part01 {0}", new Object[]{d.calculateResultPart01(input)});
        log.log(Level.INFO, "Result Part02 {0}", new Object[]{d.calculateResultPart02(input)});
    }

    private List<String> rawInput;


    public Day02(String filename) throws IOException, URISyntaxException {
        var p = Paths.get(ClassLoader.getSystemResource(filename).toURI());
        this.rawInput = Files.readAllLines(p);
    }

    public List<Game> readInput() {
        return rawInput.stream().map(l -> {
            Game game = new Game();
            game.id = Integer.parseInt(l.subSequence(5, l.indexOf(":")).toString());
            List<String> sets = Arrays.asList(l.substring(l.indexOf(":") + 1).split(";"));
            sets.forEach(s -> {
                List<String> cubes = Arrays.asList(s.split(","));

                GSet set = new GSet();
                cubes.forEach(c -> {
                    //log.info("cubes '"+c+"'");
                    var x = c.trim().split(" ");
                    switch (x[1].trim()) {
                        case "green":
                            set.green = Integer.parseInt(x[0].trim());
                            break;
                        case "red":
                            set.red = Integer.parseInt(x[0].trim());
                            break;
                        case "blue":
                            set.blue = Integer.parseInt(x[0].trim());
                            break;
                        default:
                            //do nothing
                    }
                });
                game.sets.add(set);
            });
            log.info("Input " + game);
            return game;
        }).toList();
    }

    public Integer calculateResultPart01(List<Game> input) {
        //filter reds > 12
        //filter green > 13
        //filter blue > 14
        List<Game> result = input.stream().filter(g -> {
            return g.sets.stream().allMatch(s -> s.red <= 12 && s.green <= 13 && s.blue <= 14);
        }).toList();
        //result.forEach(g -> log.info("x"+g));

        return result.stream().mapToInt(Game::getId).sum();
    }

    public Integer calculateResultPart02(List<Game> input) {
        var reformedData = input.stream().mapToInt(g -> {
            List<Integer> redCubes = new ArrayList<>();
            List<Integer> blueCubes = new ArrayList<>();
            List<Integer> greenCubes = new ArrayList<>();
            g.sets.forEach(s -> {
                redCubes.add(s.red);
                blueCubes.add(s.blue);
                greenCubes.add(s.green);
            });
            return redCubes.stream().mapToInt(i -> i).max().getAsInt() * //
                    blueCubes.stream().mapToInt(i -> i).max().getAsInt() *
                    greenCubes.stream().mapToInt(i -> i).max().getAsInt();

        });
        return reformedData.sum();
    }

    public class Game {
        int id;
        List<GSet> sets = new ArrayList<>();

        int getId() {
            return this.id;
        }

        @Override
        public String toString() {
            return "Game{" +
                    "id=" + id +
                    ", sets=" + sets +
                    '}';
        }
    }

    public class GSet {
        int red = 0;
        int blue = 0;
        int green = 0;

        @Override
        public String toString() {
            return "GSet{" +
                    "red=" + red +
                    ", blue=" + blue +
                    ", green=" + green +
                    '}';
        }
    }


}
