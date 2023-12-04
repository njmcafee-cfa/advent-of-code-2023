package advent.of.code.year2023.util;

import java.util.ArrayList;
import java.util.List;

public class ListTypeConversion {
    public static List<Integer> stringToInteger(List<String> strings) {
        List<Integer> result = new ArrayList<>();
        strings.forEach(string->{
            result.add(Integer.parseInt(string));
        });
        return result;
    }
}
