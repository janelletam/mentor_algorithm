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
    static final int NUM_OF_ATTRIBUTES_PLUS_ONE = 22;

    //Todo: Clean up code - see if we can combine functions

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
    public static ArrayList<LastTermMentor> readLastTermMentorsCSV(String fileName) throws IOException {
        ArrayList<LastTermMentor> listOfLastTermMentors = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);
        bufferedReader = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII);

        String line = nextLine();
        while (!line.equals("null")) { //!!!

            // Use string.split to load a string array with the values from
            // each line of the file, using a comma as the delimiter
            String[] attributes = line.split(",");
            LastTermMentor lastTermMentor = createLastTermMentor(attributes);

            // adding Pod into ArrayList
            listOfLastTermMentors.add(lastTermMentor);

            // Read next line before looping. If end of file reached, line would be null
            line = nextLine();
        }

        bufferedReader.close();
        return listOfLastTermMentors;
    }

    // Read Returning Mentors
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

        String dateOfBirth = attributes[3];
        String phoneNumber = attributes[4];
        String email = attributes[5];
        String gmail = attributes [6];
        String pronouns = attributes[7];
        String languages = attributes[8];
        String school = attributes[9];
        String programCompleted = attributes[10];
        String returnInTheFuture = attributes[11];
        String emergencyContactFirstName = attributes[12];
        String emergencyContactLastName = attributes[13];

/*        String yearsAttended = attributes[9];
        String major = attributes[10];

        //Todo: Join these fields together
        String emergencyContactName;
        int i = 0;
        if (attributes.length == NUM_OF_ATTRIBUTES_PLUS_ONE) {
            emergencyContactName = attributes[11] + attributes[12];
            i = 1;
        } else {
            emergencyContactName = attributes[11];
        }*/

        String emergencyContactEmail = attributes[14];
        String emergencyContactNumber = attributes[15];
        String whyDoYouWantToVolunteer = attributes[16];
        String experienceWithIndigenous = attributes[17];
        String explainExperienceWithIndigenous = attributes[18];
        String experienceWithNeurodivergent = attributes[19];

        String isPhotoVideoConsentTrue = attributes[20];
        String moodleUsername = attributes[21];
        String moodleEmail = attributes[22];
        String isReturning = attributes[23];
        String previousPosition = attributes[24];
        String isReadingMentor = attributes[25];
        String OnlineMWTMorn = attributes[26];
        String OnlineMWTAfternoon = attributes[27];
        String IPSurreyMorn = attributes[28];
        String IPSunsetAfternoon = attributes[29];
        String IPMosaicAfternoon = attributes[30];
        String additionalNotesAboutMentor = attributes[31];


        // Create and return Mentor with these attributes
        return new Mentor(firstName, preferredName, lastName, dateOfBirth,
                phoneNumber, email, gmail, pronouns, languages,
                school, programCompleted, returnInTheFuture,
                emergencyContactFirstName, emergencyContactLastName,
                emergencyContactEmail, emergencyContactNumber,
                whyDoYouWantToVolunteer, experienceWithIndigenous,
                explainExperienceWithIndigenous, experienceWithNeurodivergent,
                isPhotoVideoConsentTrue, moodleUsername, moodleEmail,
                isReturning, previousPosition, isReadingMentor,
                OnlineMWTMorn, OnlineMWTAfternoon, IPSurreyMorn,
                IPSunsetAfternoon, IPMosaicAfternoon, additionalNotesAboutMentor);
    }

    private static Pod createPod(String[] attributes) {
        String time = attributes[0];
        String podName= attributes[1];
        boolean isReading = false;
        boolean isMath = false;
        boolean isIP = false;
        boolean isOnline = false;

        // If any of the variables from the file are 1 (meaning true), make the pod's variables also true
        if (attributes[2].equals("1")) isReading = true;
        if (attributes[3].equals("1")) isMath = true;
        if (attributes[4].equals("1")) isIP = true;
        if (attributes[5].equals("1")) isOnline = true;

        // Create and return Pod with these attributes
        return new Pod(time, podName, isReading, isMath, isIP, isOnline);
    }

    private static LastTermMentor createLastTermMentor(String[] attributes) {
        String firstName = attributes[0];
        String lastName = attributes[1];
        String podName = attributes[2];

        // Create and return Pod with these attributes
        return new LastTermMentor(firstName, lastName, podName);
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