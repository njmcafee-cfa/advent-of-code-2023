package advent.of.code.year2023.day;

import advent.of.code.year2023.template.DayTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static advent.of.code.year2023.util.ListTypeConversion.stringToInteger;
import static advent.of.code.year2023.util.MatcherUtil.getListOfMatchingGroupsFromString;

public class Day04 extends DayTemplate {
    private final Map<Integer, List<Integer>> winningNumbersFromEachLine;

    private final Map<Integer, List<Integer>> myNumbersFromEachLine;

    private final Map<Integer,Integer> winsPerLine;

    public Day04() {
        super("04");
        this.winningNumbersFromEachLine = processWinningNumbersFromEachLine(fileLines);
        this.myNumbersFromEachLine = processMyNumbersFromEachLine(fileLines);
        this.winsPerLine = processWinsPerLine(myNumbersFromEachLine, winningNumbersFromEachLine);
    }

    //Attempts:
    //  First Problem:
    //    788 - too low
    //    20407 - correct
    //  Second Problem:
    //    7930298 - too low
    //    23790824 - too low
    //    23806951 - correct


    private static int processGameNumber(String line) {
        String[] lineSplitColon = line.split(":");

        return Integer.parseInt(getListOfMatchingGroupsFromString(lineSplitColon[0], "[0-9]+").get(0));
    }

    private static String processWinningNumbers(String line) {
        String[] lineSplitColon = line.split(":");
        String[] lineSplitPipe = lineSplitColon[1].split("[|]");

        return lineSplitPipe[0];
    }

    private static String processMyNumbers(String line) {
        String[] lineSplitColon = line.split(":");
        String[] lineSplitPipe = lineSplitColon[1].split("[|]");

        return lineSplitPipe[1];
    }

    private static Map<Integer,List<Integer>> processWinningNumbersFromEachLine(List<String> lines) {
        Map<Integer, List<Integer>> winningNumbersFromEachLine = new HashMap<>();

        lines.forEach(line->{
            int lineNumber = processGameNumber(line);
            String winningNumbersString = processWinningNumbers(line);
            List<Integer> winningNumbers = stringToInteger(getListOfMatchingGroupsFromString(winningNumbersString, "[0-9]+"));

            winningNumbersFromEachLine.put(lineNumber, winningNumbers);
        });

        return winningNumbersFromEachLine;
    }

    private static Map<Integer,List<Integer>> processMyNumbersFromEachLine(List<String> lines) {
        Map<Integer, List<Integer>> myNumbersFromEachLine = new HashMap<>();

        lines.forEach(line->{
            int lineNumber = processGameNumber(line);
            String myNumbersString = processMyNumbers(line);
            List<Integer> myNumbers = stringToInteger(getListOfMatchingGroupsFromString(myNumbersString, "[0-9]+"));

            myNumbersFromEachLine.put(lineNumber, myNumbers);
        });

        return myNumbersFromEachLine;
    }

    private static Map<Integer,Integer> processWinsPerLine(
            Map<Integer, List<Integer>> myNumbersFromEachLine,
            Map<Integer, List<Integer>> winningNumbersFromEachLine) {

        Map<Integer,Integer> winsPerLine = new HashMap<>();

        myNumbersFromEachLine.forEach((lineNumber, myNumbers)->{
            AtomicInteger winCounter = new AtomicInteger();

            myNumbers.forEach(myNumber->{
                List<Integer> winningNumbers = winningNumbersFromEachLine.get(lineNumber);
                if (winningNumbers.contains(myNumber)) {
                    winCounter.set(winCounter.get() + 1);
                }
            });

            winsPerLine.put(lineNumber,winCounter.get());
        });

        return winsPerLine;
    }

    @Override
    protected void firstProblem() {
        winsPerLine.forEach((line,wins)->{
            int points = (wins > 0) ? (int) Math.pow(2.0, wins - 1.0) : 0;
            resultFirstProblem += (long) points;
        });
    }

    @Override
    protected void secondProblem() {
        Long[] cardCounter = new Long[winsPerLine.size()];

        IntStream.range(0,winsPerLine.size()).forEach(i->{
            cardCounter[i] = 1L;
        });

        winsPerLine.forEach((lineNumber, wins)->{
            int lineIndex = lineNumber - 1;
            if (wins > 0 && lineIndex + 1 < winsPerLine.size()) {
                int startingIndex = lineIndex + 1;
                int endingIndex = lineIndex + wins < winsPerLine.size() ? lineIndex + wins : winsPerLine.size() - 1;
                IntStream.range(startingIndex, endingIndex + 1).forEach(i -> {
                    cardCounter[i] += cardCounter[lineIndex];
                });
            }
        });

        Arrays.stream(cardCounter).forEach(cardCount->{
            resultSecondProblem += cardCount;
        });
    }
}
