package advent.of.code.year2023.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherUtil {
    public static List<String> getListOfMatchingGroupsFromString(String string, String patternString) {
        List<String> result = new ArrayList<>();

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }
}
