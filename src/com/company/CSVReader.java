package com.company;

import java.io.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CSVReader {

    static BufferedReader bufferedReader;
    static final int MAX_ATTRIBUTES_LENGTH = 27;
    static final int EMERGENCY_CONTACT_NAME_INDEX = 11;
    static final int TELL_US_ABOUT_YOURSELF_INDEX = 21;

    static final int PHONE_NUMBER_INDEX = 5;

    // Read Mentors
    public static ArrayList<Mentor> readMentorsFromCSV(String fileName) throws IOException {
        ArrayList<Mentor> listOfMentors = new ArrayList<>();
        Path pathToFile = Paths.get(fileName);


        HashSet<String> phoneNumberCheckRepeat = new HashSet<>(); //check if people are repeated through phone numbers


        bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"utf-8"));
//        bufferedReader = Files.newBufferedReader(pathToFile,
//                StandardCharsets.US_ASCII);

        String line = nextLine();
        while (!line.equals("null")) { //!!!

            // Use string.split to load a string array with the values from
            // each line of the file, using a comma as the delimiter
            String[] attributes = getAllValues(line);

            Mentor mentor = createMentor(attributes);

            if(phoneNumberCheckRepeat.add(attributes[PHONE_NUMBER_INDEX])){
                // adding Mentor into ArrayList
                listOfMentors.add(mentor);
            }

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
        String emergencyContactName = attributes[11];


        String emergencyContactEmail = attributes[12];
        String emergencyContactNumber = attributes[13];

        String isReadingMentor = attributes[14];

        String availabilityOnline = attributes[15];
        String availabilityInPerson = attributes[16];
        String availabilityClayton = attributes[17];
        String availabilityComments = attributes[18];

        String isReturning = attributes[19];
        String isPhotoVideoConsentTrue = attributes[20];

        String tellUsAboutYourself = attributes[21];
        String previousPodType = attributes[22];
        String indigenousExperience = attributes[23];
        String neuroDivergentExperience = attributes[24];
        String otherLanguages = attributes[25];
        String howDidYouHearAboutUs = attributes[26];

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

    private static String[] getAllValues(String s){
        String[] response = new String[MAX_ATTRIBUTES_LENGTH];

        //deal with emergency contact name and tell us about yourself first
        int firstIndex = s.indexOf("\"");

        if(firstIndex == -1){
            response = s.split(",");
            return response;
        }

        int secondIndex = s.indexOf("\"",firstIndex+1);


        response[EMERGENCY_CONTACT_NAME_INDEX] = s.substring(firstIndex+1, secondIndex);

        int thirdIndex = s.indexOf("\"", secondIndex+1);
        int fourthIndex = s.indexOf("\"",thirdIndex+1);

        response[TELL_US_ABOUT_YOURSELF_INDEX] = s.substring(thirdIndex+1, fourthIndex);


        //rest of the variables
        String[] firstSetResponses = s.substring(0, (firstIndex-1)).split(",");
        for(int i = 0; i < firstSetResponses.length; i++){
            response[i] = firstSetResponses[i];
        }

        String[] secondSetResponses = s.substring(secondIndex+2, (thirdIndex-1)).split(",");//second index is ", second index + 1 is ",", so must start at second index+2
        for(int i = 0; i < secondSetResponses.length; i++){
            response[i+EMERGENCY_CONTACT_NAME_INDEX+1] = secondSetResponses[i];
        }

        String[] thirdSetResponses = s.substring(fourthIndex+2).split(",");
        for(int i = 0; i < thirdSetResponses.length; i++){
            response[i+TELL_US_ABOUT_YOURSELF_INDEX+1] = thirdSetResponses[i];
        }

        return response;

    }

}