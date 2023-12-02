package advent.of.code.year2023;

import advent.of.code.year2023.day.Day01;
import advent.of.code.year2023.day.Day02;
import advent.of.code.year2023.day.Day03;

public class Main {
    public static void main(String[] args) {
        Day01 day01 = new Day01();
        day01.processFile();

        Day02 day02 = new Day02();
        day02.processFile();

        Day03 day03 = new Day03();
        day03.processFile();
    }
}