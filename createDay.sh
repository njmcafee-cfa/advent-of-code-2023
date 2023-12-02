#!/usr/bin/env bash

set -e

WORK_DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"

cd "$WORK_DIR"

DAY=$1

RESOURCES_DIRECTORY="src/main/resources/day$DAY"
CLASS_FILE="src/main/java/advent/of/code/year2023/day/Day$DAY.java"
TEMPLATE_FILE="src/main/resources/ClassTemplate.txt"
APPLICATION_FILE="src/main/java/advent/of/code/year2023/Main.java"

mkdir "$RESOURCES_DIRECTORY"

touch "$RESOURCES_DIRECTORY/input.txt"
touch "$RESOURCES_DIRECTORY/problem1.txt"
touch "$RESOURCES_DIRECTORY/problem2.txt"
touch "$CLASS_FILE"

sed "s/\${DAY}/$DAY/g" $TEMPLATE_FILE > "$CLASS_FILE"

LINE_NUM=$(awk '/import/{i=NR} END{print i}' "$APPLICATION_FILE")

sed -i '' "${LINE_NUM}a\\
import advent.of.code.year2023.day.Day$DAY;\\
" "$APPLICATION_FILE"

LINE_NUM=$(awk '/}/{i=NR} END{print i-1}' "$APPLICATION_FILE")

sed -i '' "${LINE_NUM}i\\
\\
        Day$DAY day$DAY = new Day$DAY();\\
        day$DAY.processFile();\\
" "$APPLICATION_FILE"
