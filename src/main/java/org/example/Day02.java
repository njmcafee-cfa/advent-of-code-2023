package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day02 {
    private static Long sumFirstMethod = 0L;
    private static Long sumSecondMethod = 0L;

    private static final Path path = Paths.get("src/main/resources/input_Day02.txt");

    static void processFile() throws IOException {

        Map<Integer, Map<String,Integer>> colorHighValuesFromEachGame = new HashMap<>();


        List<String> lines = new ArrayList<>();

        try(Stream<String> linesFromFile = Files.lines(path)){
            linesFromFile.forEachOrdered(lines::add);
        } catch (IOException e) {
            throw new RuntimeException();
        }

        colorHighValuesFromEachGame = getColorHighValuesFromEachGame(lines);

        firstProblem(colorHighValuesFromEachGame);

        secondProblem(colorHighValuesFromEachGame);

        System.out.println("First Method:  " + sumFirstMethod);
        System.out.println("Second Method: " + sumSecondMethod);
    }

    //Attempts:
    //  First Problem:
    //    228 - too low - I had flipped my assertion, thinking the game was possible only if the bag had fewer of each color than the actual number retrieved
    //    1867 - correct!
    //  Second Problem:
    //    84538 - correct!

    private static void firstProblem(Map<Integer,Map<String,Integer>> colorHighValuesFromEachGame) throws IOException {

        Map<String,Integer> gamePossibilityColorMap = Map.of("red", 12, "green", 13, "blue", 14);

        colorHighValuesFromEachGame.forEach((gameId,colorMap)->{
            AtomicReference<Boolean> gamePossibility = new AtomicReference<>(true);

            Map<String,Boolean> colorPossibilityMap = new HashMap<>();
            colorPossibilityMap.put("red", false);
            colorPossibilityMap.put("green", false);
            colorPossibilityMap.put("blue", false);

            colorMap.forEach((color,number)->{
                colorPossibilityMap.put(color, (number <= gamePossibilityColorMap.get(color)));
            });

            colorPossibilityMap.forEach((color,possible)->{
                if (!possible) {
                    gamePossibility.set(false);
                }
            });

            if (Boolean.TRUE.equals(gamePossibility.get())) {
                sumFirstMethod += (long) gameId;
            }
        });
    }

    private static Map<Integer,Map<String,Integer>> getColorHighValuesFromEachGame(List<String> lines) {
        Map<Integer,Map<String,Integer>> colorHighValuesFromEachGame = new HashMap<>();

        lines.forEach(line->{
            Map<String,Integer> colorHighValues = new HashMap<>();

            String[] lineSplitColon = line.split(":");
            String gameWithIdFromSplit = lineSplitColon[0];
            String gameInfoStringFromSplit = lineSplitColon[1];

            int gameId = getGameId(gameWithIdFromSplit);

            String[] gameInfoStringArray = gameInfoStringFromSplit.split(";");

            colorHighValues = getColorHighValues(gameInfoStringArray);

            colorHighValuesFromEachGame.put(gameId, colorHighValues);
        });

        return colorHighValuesFromEachGame;
    }

    private static Map<String,Integer> getColorHighValues(String[] gameInfoStringArray) {
        Map<String,Integer> colorHighValues = new HashMap<>();
        colorHighValues.put("red", 0);
        colorHighValues.put("green", 0);
        colorHighValues.put("blue", 0);

        Arrays.stream(gameInfoStringArray).forEach(gameInfoString->{
            Map<String,Integer> colorMap = getColorMap(gameInfoString);

            colorMap.forEach((color,number)->{
                if (number > colorHighValues.get(color)) {
                    colorHighValues.put(color,number);
                }
            });
        });

        return colorHighValues;
    }

    private static Map<String, Integer> getColorMap(String gameInfoString) {
        Map<String,Integer> result = new HashMap<>();

        String[] colorNumberStrings = gameInfoString.split(",");

        for (String colorNumberString : colorNumberStrings) {
            String[] colorNumberPair = colorNumberString.trim().split(" ");

            result.put(colorNumberPair[1], Integer.parseInt(colorNumberPair[0]));
        }

        return result;
    }


    private static int getGameId(String gameString) {
        Pattern gameIdPattern = Pattern.compile("\\d+");

        Matcher gameIdMatcher = gameIdPattern.matcher(gameString);

        int gameId = 0;

        if (gameIdMatcher.find()) {
            String gameIdString = gameIdMatcher.group();
            gameId = Integer.parseInt(gameIdString);
        }
        return gameId;
    }

    private static void secondProblem(Map<Integer,Map<String,Integer>> colorHighValuesFromEachGame) {
        colorHighValuesFromEachGame.forEach((gameId,colorHighValues)->{
            Long gamePower = getGamePower(colorHighValues);
            sumSecondMethod += gamePower;
        });
    }

    private static Long getGamePower(Map<String,Integer> colorHighValues) {
        AtomicReference<Long> gamePower = new AtomicReference<>(1L);

        colorHighValues.forEach((color,number)-> {
            gamePower.updateAndGet(v -> v * number);
        });

        return gamePower.get();
    }
}
