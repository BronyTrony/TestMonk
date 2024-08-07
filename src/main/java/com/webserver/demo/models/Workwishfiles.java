package com.webserver.demo.models;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Workwishfiles {

    public static String FileReader() {
        String pathToFile = "src/main/resources/";
        String fileName = "Users.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToFile + fileName))) {
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            Random r = new Random();
            return lines.get(r.nextInt(lines.size()));
        } catch (IOException e) {
            e.printStackTrace();
        } return null;
    }

    public static void FileWriter(User k) throws IOException {
        String pathToFile = "src/main/resources/";
        String fileName = "Results.txt";
        File newFile = new File (pathToFile + fileName);
        if (!newFile.isFile()) {
            newFile.createNewFile();
        }
        try (BufferedWriter writter = new BufferedWriter(new FileWriter(pathToFile + fileName, true))) {
            writter.write(k.toString());
            writter.newLine();
            }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}