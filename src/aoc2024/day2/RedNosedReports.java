package aoc2024.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RedNosedReports {
    public static void main(String[] args) {
        long safeReportCountL1 = 0L;
        long safeReportCountL2 = 0L;

        try (BufferedReader br = new BufferedReader(new FileReader("src/aoc2024/day2/input.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                List<Integer> report = new ArrayList<>();
                for(String part: parts){
                    report.add(Integer.parseInt(part));
                }

                if(checkIfSafeReportL1(report)){
                    safeReportCountL1 += 1L;
                }
                if(checkIfSafeReportL2(report)){
                    safeReportCountL2 += 1L;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        System.out.println("Safe report count: " + safeReportCountL1);
        System.out.println("Safe report count with tolerance of 1: " + safeReportCountL2);

    }

    private static boolean checkIfSafeReportL1(List<Integer> report){
        boolean isIncreasing = report.get(0) > report.get(1);

        for (int i = 0; i < report.size() - 1; i++) {
            int diff = Math.abs(report.get(i) - report.get(i+1));
            if(diff == 0 || diff > 3){
                return false;
            }
            if((report.get(i) > report.get(i+1)) != isIncreasing){
                return false;
            }
        }

        return true;

    }

    private static boolean checkIfSafeReportL2(List<Integer> report){
        for (int i = 0; i < report.size(); i++) {
            List<Integer> reportWithoutOneElement = new ArrayList<>(report);
            reportWithoutOneElement.remove(i);
            if(checkIfSafeReportL1(reportWithoutOneElement)){
                return true;
            }
        }
        return false;
    }
}
