package aoc2024.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class HistorianHysteria{
    public static void main(String[] args) {

        List<Integer> leftList = new ArrayList<>();
        List<Integer> rightList = new ArrayList<>();


        try (BufferedReader br = new BufferedReader(new FileReader("src/aoc2024/day1/input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                leftList.add(Integer.parseInt(parts[0]));
                rightList.add(Integer.parseInt(parts[1]));
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        solvePuzzle(leftList, rightList);

    }

    public static void solvePuzzle(List<Integer> leftList, List<Integer> rightList){
        leftList.sort(Comparator.naturalOrder());
        rightList.sort(Comparator.naturalOrder());

        List<Integer> finalList = IntStream.range(0, leftList.size())
                .boxed()
                .map(index -> Math.abs(leftList.get(index) - rightList.get(index)))
                .toList();

        long finalResult = finalList.stream()
                .reduce(0, Integer::sum);

        System.out.println("Total Distance between the lists: " + finalResult);

        long similarityScore = 0L;

        for (int element: leftList){
            similarityScore += (long) Collections.frequency(rightList, element) * element;
        }

        System.out.println("Similarity Score: " + similarityScore);
    }

}
