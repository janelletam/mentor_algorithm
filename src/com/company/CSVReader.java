package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.*;

public class CSVReader {

    static BufferedReader bufferedReader;
    static StringTokenizer tokenizer;

    // Read Mentors
    public static ArrayList<Mentor> readMentorsFromCSV(String fileName) throws IOException {
        ArrayList<Mentor> listOfMentors = new ArrayList<>();

        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"utf-8"));

        String line = nextLine();
        while (!line.equals("null")) {
            //!!!
            // Use string.split to load a string array with the values from
            // each line of the file, using a comma as the delimiter
            String[] attributes = line.split(",");
            Mentor mentor = createMentor(attributes);

            // adding Mentor into ArrayList
            listOfMentors.add(mentor);

            // Read next line before looping. If end of file reached, line would be null
            line = nextLine();
        }

        bufferedReader.close();
        return listOfMentors;
    }

    // Read Pods
    public static ArrayList<Pod> readPodsFromCSV(String fileName) throws IOException {
        ArrayList<Pod> listOfPods = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        bufferedReader = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII);

        String line = nextLine();
        while (!line.equals("null")) { //!!!

            // Use string.split to load a string array with the values from
            // each line of the file, using a comma as the delimiter
            String[] attributes = line.split(",");
            Pod pod = createPod(attributes);

            // adding Pod into ArrayList
            listOfPods.add(pod);

            // Read next line before looping. If end of file reached, line would be null
            line = nextLine();
        }

        bufferedReader.close();
        return listOfPods;
    }

    private static Mentor createMentor(String[] attributes) {
        String firstName = attributes[0];
        String preferredName = attributes[1];
        String lastName = attributes[2];

        String age = attributes[3];
        String phoneNumber = attributes[4];
        String email = attributes[5];
        String pronouns = attributes[6];
        String school = attributes[7];
        String yearsAttended = attributes[8];
        String major = attributes[9];

        //Todo: Join these fields together
        String emergencyContactName;
        int i = 0;
        if (attributes.length == 2) {
            emergencyContactName = attributes[10] + attributes[11];
            i = 1;
        } else {
            emergencyContactName = attributes[10];
        }

        String emergencyContactEmail = attributes[11 + i];
        String emergencyContactNumber = attributes[12 + i];

        String isReadingMentor = attributes[13 + i];

        String availabilityOnline = attributes[14 + i];
        String availabilityInPersonSchool = attributes[15 + i];
        String availabilityInPersonCommunityCentre = attributes[16 + i];
        String availabilityClayton = attributes[17 + i];

        String isReturning = attributes[18 + i];
        String isPhotoVideoConsentTrue = attributes[19 + i];

        // Create and return Mentor with these attributes
        return new Mentor(firstName, preferredName, lastName, age, phoneNumber, email, pronouns,
                school, yearsAttended, major, emergencyContactName, emergencyContactEmail,
                emergencyContactNumber, isReadingMentor, availabilityOnline,
                availabilityInPersonSchool, availabilityInPersonCommunityCentre, availabilityClayton,
                isReturning, isPhotoVideoConsentTrue);
    }

    private static Pod createPod(String[] attributes) {
        String time = attributes[0];
        String podName= attributes[1];
        boolean isReading = false;
        boolean isMath = false;
        boolean isIP = false;
        boolean isOnline = false;
        boolean isClayton = false;

        // If any of the variables from the file are 1 (meaning true), make the pod's variables also true
        if (attributes[2].equals("1")) isReading = true;
        if (attributes[3].equals("1")) isMath = true;
        if (attributes[4].equals("1")) isIP = true;
        if (attributes[5].equals("1")) isOnline = true;
        if (attributes[6].equals("1")) isClayton = true;

        // Create and return Pod with these attributes
        return new Pod(time, podName, isReading, isMath, isIP, isOnline, isClayton);
    }

    static String next() throws IOException {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(bufferedReader.readLine().trim());
        }
        return tokenizer.nextToken();
    }

    long nextLong() throws IOException {
        return Long.parseLong(next());
    }

    int nextInt() throws IOException {
        return Integer.parseInt(next());
    }

    double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    static char nextCharacter() throws IOException {
        return next().charAt(0);
    }

    static String nextLine() throws IOException {
        String s = bufferedReader.readLine();
        if (s == null) {
            return "null";
        }
        return s.trim();

    }

}