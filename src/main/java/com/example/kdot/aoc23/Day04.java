package com.example.kdot.aoc23;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Day04 {

    static Logger log = Logger.getLogger(Day04.class.getName());

    public static void main(String[] args) throws IOException, URISyntaxException {
        Day04 d = new Day04("day04.input.txt");
        var input = d.readInput();
        log.log(Level.INFO, "Result Part01 {0}", new Object[] { d.calculatePart01(input) });
        log.log(Level.INFO, "Result Part02 {0}", new Object[] { d.calculatePart02(input) });
    }

    private final List<String> rawInput;

    Day04(String filename) {
        try {
            Path p = Paths.get(ClassLoader.getSystemResource(filename).toURI());
            this.rawInput = Files.readAllLines(p);
        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    Model readInput() {
        Model model = new Model();

        rawInput.forEach(row -> {
            var card = new Card();
            model.cards.add(card);
            var rowTokenized = row.split(":|\\|");
            card.id = Integer.valueOf(rowTokenized[0].replaceAll("Card", "").replace(":", "").trim());
            var rawWinningNumbers = rowTokenized[1].trim().split("\s+");
            Arrays.stream(rawWinningNumbers).forEach(wn -> card.winningNumbers.add(Integer.parseInt(wn.trim())));
            var rawMyNumbers = rowTokenized[2].trim().split("\s+");
            Arrays.stream(rawMyNumbers).forEach(mn -> card.myNumbers.add(Integer.parseInt(mn.trim())));
        });

        return model;
    }

    int calculatePart01(Model input) {
        input.cards.forEach(card -> {
            var points = 0;
            var matches = card.myNumbers.stream().mapToInt(mn -> {
                return card.winningNumbers.contains(mn) ? 1 : 0;
            }).sum();
            if (matches > 0) {
                points = 1;
                matches--;
            }
            while (matches > 0) {
                points += points;
                matches--;
            }
            card.points = points;
        });
        return input.cards.stream().mapToInt(Card::getPoints).sum();
    }

    long calculatePart02(Model input) {

        Map<Integer, Long> cardCount = new HashMap<>();
        int maxCards = input.cards.size();
        input.cards.forEach(card -> {
            var count = cardCount.containsKey(card.id) ? cardCount.get(card.id) + 1 : 1;
            cardCount.put(card.id, count);
            var matches = card.myNumbers.stream().mapToInt(mn -> {
                return card.winningNumbers.contains(mn) ? 1 : 0;
            }).sum();
            for (int i = 1; i <= matches; ++i) {
                int xId = card.id + i;
                if (xId > maxCards) {
                    break;
                }
                count = cardCount.containsKey(xId) ? cardCount.get(xId) + cardCount.get(card.id)
                        : cardCount.get(card.id);
                cardCount.put(xId, count);
            }
            log.info("Card " + card.id + " with " + cardCount.get(card.id) + " copies has " + matches
                    + " matches. Updated total:" + cardCount.values().stream().mapToLong(i -> i).sum());
        });

        return cardCount.values().stream().mapToLong(i -> i).sum();
    }

    class Model {
        List<Card> cards = new ArrayList<>();
    }

    class Card {
        List<Integer> winningNumbers = new ArrayList<>();

        List<Integer> myNumbers = new ArrayList<>();

        int id;

        int points;

        public int getPoints() {
            return points;
        }

    }
}
