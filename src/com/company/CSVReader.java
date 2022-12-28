package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CSVReader {

    static BufferedReader bufferedReader;
    static final int MAX_ATTRIBUTES_LENGTH = 27;

    // Read Mentors
    public static ArrayList<Mentor> readMentorsFromCSV(String fileName) throws IOException {
        ArrayList<Mentor> listOfMentors = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        bufferedReader = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII);

        String line = nextLine();
        while (!line.equals("null")) { //!!!

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
        String pronouns = attributes[4];

        String preferredEmail = attributes[5];
        String gmail = attributes[6];
        String phoneNumber = attributes[7];

        String school = attributes[8];
        String yearsAttended = attributes[9];
        String major = attributes[10];

        //Todo: Join these fields together. Change attributes.length
        String emergencyContactName;
        int i = 0;
        if (attributes.length == MAX_ATTRIBUTES_LENGTH) {
            emergencyContactName = attributes[11] + attributes[12];
            i = 1;
        } else {
            emergencyContactName = attributes[11];
        }

        String emergencyContactEmail = attributes[12 + i];
        String emergencyContactNumber = attributes[13 + i];

        String isReadingMentor = attributes[14 + i];

        String availabilityOnline = attributes[15 + i];
        String availabilityInPerson = attributes[16 + i];
        String availabilityClayton = attributes[17 + i];
        String availabilityComments = attributes[18 + i];

        String isReturning = attributes[19 + i];
        String isPhotoVideoConsentTrue = attributes[20 + i];

        String tellUsAboutYourself = attributes[21 + i];
        String previousPodType = attributes[22 + i];
        String indigenousExperience = attributes[23 + i];
        String neuroDivergentExperience = attributes[24 + i];
        String otherLanguages = attributes[25 + i];
        String howDidYouHearAboutUs = attributes[26 + i];

        // Create and return Mentor with these attributes
        return new Mentor(firstName, preferredName, lastName, age, pronouns, preferredEmail,
                gmail, phoneNumber, school, yearsAttended, major, emergencyContactName, emergencyContactEmail,
                emergencyContactNumber, isReadingMentor, availabilityOnline,
                availabilityInPerson, availabilityClayton, availabilityComments, isReturning,
                isPhotoVideoConsentTrue, tellUsAboutYourself, previousPodType, indigenousExperience,
                neuroDivergentExperience, otherLanguages, howDidYouHearAboutUs);
    }

    private static Pod createPod(String[] attributes) {
        String time = attributes[0];
        String podName = attributes[1];
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

    static String nextLine() throws IOException {
        String s = bufferedReader.readLine();
        if (s == null) {
            return "null";
        }
        return s.trim();
    }

}