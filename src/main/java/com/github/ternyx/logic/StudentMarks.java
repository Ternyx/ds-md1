package com.github.ternyx.logic;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import com.github.ternyx.structures.MyLinkedStack;
import com.github.ternyx.structures.Student;

/**
 * StudentMarks
 */
public class StudentMarks {
    public static void printMaxMarks(String fileName) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            MyLinkedStack<Student> maxMarkStudents = new MyLinkedStack<>();

            String line;
            while ((line = br.readLine()) != null) {
                String[] splitted = line.split("\\s+");
                Student potentialStudent =
                        new Student(splitted[1], Double.parseDouble(splitted[0]));

                if (maxMarkStudents.isEmpty()) {
                    maxMarkStudents.push(potentialStudent);
                    continue;
                }

                double topMark = maxMarkStudents.top().getMark();
                double currentMark = potentialStudent.getMark();

                if (currentMark < topMark) {
                    continue;
                } 

                if (currentMark > topMark) {
                    maxMarkStudents.clear();
                }
                maxMarkStudents.push(potentialStudent);
            }
            System.out.println("Students with the max marks:");
            System.out.println(maxMarkStudents.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
            System.err.println("Couldn't read the file?");
        }
    }
}
