package com.stephen.chatlimit;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChatlimitApplication {

    public static void main(String[] args) {
        String filePath = "src\\main\\resources\\input.txt";
        String line;
        List<String> lines = new ArrayList<>();
        int limit = 15000;
        int actual = 0;
        int fileNum = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                if (actual + line.length() > limit) {
                    writeToFile(lines, fileNum);
                    fileNum++;
                    lines.clear();
                    actual = 0;
                }
                lines.add(line);
                actual += line.length();
            }
            if (!lines.isEmpty()) {
                writeToFile(lines, fileNum);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeToFile(List<String> lines, int fileNum) {
        String outputFilePath = "src\\main\\resources\\output" + fileNum + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
