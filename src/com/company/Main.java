package com.company;

import java.util.ArrayList;

import static com.company.CSVReader.readMentorsFromCSV;

public class Main {

    public static void main(String[] args) {
	// write your code here
        // Read mentors from CSV
        ArrayList<Mentor> listOfMentors = readMentorsFromCSV("D:\\work-and-ECs\\lms-developer\\algorithms\\src\\com\\company\\resources\\mentors.csv");

        // Print CSV File
        for (Mentor m : listOfMentors) {
            System.out.println(m.getAge());
        }
    }
}
