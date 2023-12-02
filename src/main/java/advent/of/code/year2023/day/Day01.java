package advent.of.code.year2023.day;

import advent.of.code.year2023.template.DayTemplate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day01 extends DayTemplate {
    public Day01() {
        super("01");
    }

    //Attempts:
    //  First Problem:
    //      53651 - correct
    //  Second Problem:
    //      53896 - too high
    //      53894 - correct

    @Override
    protected void firstProblem() {
        fileLines.forEach(line->{
            StringBuilder stringBuilder = new StringBuilder();
            char[] chars = line.toCharArray();
            for (char c : chars) {
                if (Character.isDigit(c)) {
                    stringBuilder.append(c);
                }
            }
            String num = stringBuilder.substring(0,1) + stringBuilder.substring(stringBuilder.length()-1,stringBuilder.length());
            resultFirstProblem += Long.valueOf(Integer.parseInt(num));
        });
    }

    @Override
    protected void secondProblem() {
        fileLines.forEach(line->{
            String reverseLine = new StringBuilder(line).reverse().toString();
            String forwardNumbers = "one|two|three|four|five|six|seven|eight|nine";
            String reverseNumbers = new StringBuilder(forwardNumbers).reverse().toString();
            Pattern forwardPattern = Pattern.compile("(\\d|" + forwardNumbers + ")");
            Pattern reversePattern = Pattern.compile("(\\d|" + reverseNumbers + ")");

            Matcher forwardMatcher = forwardPattern.matcher(line);
            Matcher reverseMatcher = reversePattern.matcher(reverseLine);

            StringBuilder stringBuilder = new StringBuilder();

            if (forwardMatcher.find()) {
                String digit = forwardMatcher.group();
                stringBuilder.append(convertDigit(digit));
            }

            if (reverseMatcher.find()) {
                String digit = reverseMatcher.group();
                stringBuilder.append(convertReversedDigit(digit));
            }

            resultSecondProblem += Long.parseLong(stringBuilder.toString());
        });
    }

    private static String convertDigit(String input) {
        return input
                .replace("one", "1")
                .replace("two", "2")
                .replace("three", "3")
                .replace("four", "4")
                .replace("five", "5")
                .replace("six", "6")
                .replace("seven", "7")
                .replace("eight", "8")
                .replace("nine", "9");
    }

    private static String convertReversedDigit(String input) {
        return input
                .replace("eno", "1")
                .replace("owt", "2")
                .replace("eerht", "3")
                .replace("ruof", "4")
                .replace("evif", "5")
                .replace("xis", "6")
                .replace("neves", "7")
                .replace("thgie", "8")
                .replace("enin", "9");
    }
}
