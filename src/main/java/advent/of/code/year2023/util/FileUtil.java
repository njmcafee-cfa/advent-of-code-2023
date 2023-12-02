package advent.of.code.year2023.util;

import advent.of.code.year2023.util.exception.FileAccessException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileUtil {
    private FileUtil(){}

    public static List<String> getLinesFromFile(Path path) {
        List<String> lines = new ArrayList<>();

        try(Stream<String> linesFromFile = Files.lines(path)){
            linesFromFile.forEachOrdered(lines::add);
        } catch (IOException e) {
            throw new FileAccessException("Error opening file at path: " + path, e);
        }

        return lines;
    }
}
