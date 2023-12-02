package advent.of.code.year2023.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileUtil {
    public static List<String> getLinesFromFile(Path path) {
        List<String> lines = new ArrayList<>();

        try(Stream<String> linesFromFile = Files.lines(path)){
            linesFromFile.forEachOrdered(lines::add);
        } catch (IOException e) {
            throw new RuntimeException();
        }

        return lines;
    }
}
