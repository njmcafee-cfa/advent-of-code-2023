package advent.of.code.year2023.template;

import advent.of.code.year2023.util.FileUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class DayTemplate {
    protected Long resultFirstProblem = 0L;
    protected Long resultSecondProblem = 0L;
    protected final List<String> fileLines;
    protected final String day;


    protected DayTemplate(String day) {
        this.day = day;

        Path path = Paths.get("src/main/resources/day" + day + "/input.txt");
        this.fileLines = FileUtil.getLinesFromFile(path);
    }

    public final void processFile() {
        firstProblem();
        secondProblem();
        printResults();
    }

    protected abstract void firstProblem();
    protected abstract void secondProblem();
    private void printResults() {
        System.out.println("Day " + day + " Output:");
        System.out.println("  First Problem:  " + resultFirstProblem);
        System.out.println("  Second Problem: " + resultSecondProblem);
    }
}
