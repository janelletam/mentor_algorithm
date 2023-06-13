package com.company;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Main {

    static ArrayList<Mentor> initialMentorList;
    static ArrayList<Mentor> goodList;

    static ArrayList<Mentor> toBeManuallyReviewed;

    //Todo: Each term, update availability list
    static ArrayList<Pod> allPods;
    static ArrayList<LastTermMentor> lastTermMentorList;
    static LinkedHashMap<Pod, ArrayList<Mentor>> output;
    static ArrayList<Mentor> mentorWaitList;

    static int numberOfOpenSpots;

    static final int ONE_PREFERENCE = 1;
    static final int POD_MAX_SIZE = 8;

    static final String MENTOR_CSV_FILE_PATH = "src/com/company/LBN_Mentor_Application_Form_(Su2023-06-12_18_52_13.csv";
    static final String POD_CSV_FILE_PATH = "src/com/company/allPods - Summer 2023.csv";
    static final String LAST_TERM_MENTORS_FILE_PATH = "src/com/company/Winter 2023 Registration Data - Sheet1.csv";
    static final String OUTPUT_FILE_PATH = "src/com/company/";
    static final String OUTPUT_FILE_NAME = "Generated-Class-Contact-List-";

    static final String AGE_NOT_VALID = "Age not valid";
    static final String NO_POSSIBLE_PODS = "No possible pods the Mentor can be placed in. " +
            "Mentor may not have indicated availability or their preferences may not match any pods.";
    static final String LAST_TERM_MENTOR = "Same pod as last term";

    public static void main(String[] args) throws IOException {

        // Step 0: Initialization - Create multiple ArrayLists of mentors before sort
        initialMentorList = new ArrayList<>();
        toBeManuallyReviewed = new ArrayList<>();
        goodList = new ArrayList<>();

        // For Mentors who have indicated availability, but there are not spots for them
        mentorWaitList = new ArrayList<>();

        // List of all the mentors from last term
        lastTermMentorList = new ArrayList<>();

        allPods = new ArrayList<>();
        output = new LinkedHashMap<>();

        System.out.println("Finished initialization");

        // Step 0: Read from CSV files to initialize all mentors and pods
        initialMentorList = CSVReader.readMentorsFromCSV(MENTOR_CSV_FILE_PATH);
        System.out.println("Finished reading from mentor input");

        allPods = CSVReader.readPodsFromCSV(POD_CSV_FILE_PATH);
        System.out.println("Finished reading from pod input");

        lastTermMentorList = CSVReader.readLastTermMentorsCSV(LAST_TERM_MENTORS_FILE_PATH);
        System.out.println("Finished reading from last term mentors input");

        // Step 1: First check validity of Mentors
        for (Mentor currentMentor : initialMentorList) {
            if (currentMentor.isValid()) {
                goodList.add(currentMentor);
            } else {
                toBeManuallyReviewed.add(currentMentor);
                currentMentor.setAdditionalNotesAboutMentor(AGE_NOT_VALID);
            }
        }

        // Step 2: Put returning mentors first at the beginning of the list, then add new mentors
        goodList = sortArrayListByReturning(goodList);

        // Step 3: Initialize all the possible Pods. Each Pod should hold an ArrayList of Mentors
        for (Pod pod : allPods) {
            output.put(pod, new ArrayList<>());
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // Important: The initialization of Mentor already placed all the available Clayton and IP times in front.
        // Therefore, the IP and Clayton availabilities will be prioritized

        // Step 4: Place the returning Mentors who only have 1 preference
        for (int i = 0; i < goodList.size(); i++) {
            Mentor currentMentor = goodList.get(i);
            if (currentMentor.getAllAvailableTimes().size() == ONE_PREFERENCE && currentMentor.isReturning()) {
                placeMentorInPod(currentMentor);
                // Current Mentor has been removed from goodList, so a new Mentor has replaced their previous index spot
                i--;
            }
        }

        // Step 5: Place everyone else
        for (int i = 0; i < goodList.size(); i++) {
            Mentor currentMentor = goodList.get(i);
            placeMentorInPod(currentMentor);
            // Current Mentor has been removed from goodList, so a new Mentor has replaced their previous index spot
            i--;
        }

        //Todo: Create separate output class to separate logic

        // Step 6: Output of Program

        // Generate csv string of results that will be saved to an output file.
        StringBuilder classContactList = new StringBuilder();

        classContactList.append("GENERATED CLASS CONTACT LIST: " + getCurrentTime());

        // Step 7: Print Class Contact List Summary
        classContactList.append("\n\n---------------------------------------------------");
        classContactList.append("\nCLASS CONTACT LIST SUMMARY");
        classContactList.append("\nFilled pods: ");
        for (Pod thisPod : generateListOfFilledPods()) {
            classContactList.append("\n" + thisPod.getPodName());
        }

        classContactList.append("\n\nPods that still need mentors: ");
        for (String thisPod : generateListOfNotFilledPods()) {
            classContactList.append("\n" + thisPod);
        }

        classContactList.append("\n\nTotal number of applicants: " + initialMentorList.size());

        classContactList.append("\n\nTotal number of mentors in In-Person Programs: " + calculateNumberOfIPMentors());
        classContactList.append("\nTotal number of mentors in Online Programs: " + calculateNumberOfOnlineMentors());


        classContactList.append("\n\nTotal number of placed mentors (qualified): " + calculateNumberOfPlacedMentors());
        classContactList.append("\nTotal number of mentors that need manual review: " + toBeManuallyReviewed.size());
        classContactList.append("\nTotal number of waitlisted mentors: " + mentorWaitList.size());
        classContactList.append("\n\nTotal number of mentor spots left: " + numberOfOpenSpots);

        for (Pod pod : output.keySet()) {
            classContactList.append("\n\n---------------------------------------------------");
            classContactList.append("\n" + pod.toString());
            classContactList.append("\n" + Mentor.printMentorOutputFieldOrder());
            for (Mentor thisMentor : output.get(pod)) {
                classContactList.append("\n" + thisMentor.toString());
            }
        }

        classContactList.append("\n\n---------------------------------------------------");
        classContactList.append("\nMentor Wait List:");
        classContactList.append("\n" + Mentor.printMentorOutputFieldOrder());
        for (Mentor waitListedMentor : mentorWaitList) {
            classContactList.append("\n" + waitListedMentor);
        }

        classContactList.append("\n\n---------------------------------------------------");
        classContactList.append("\nTo Be Manually Reviewed:");
        classContactList.append("\n" + Mentor.printMentorOutputFieldOrder());
        for (Mentor toBeReviewedMentor : toBeManuallyReviewed) {
            classContactList.append("\n" + toBeReviewedMentor);
        }

        classContactList.append("\n\n---------------------------------------------------");
        classContactList.append("\nReading Mentors Contact Information:");
        classContactList.append("\n" + Mentor.printContactInfoFieldOrder());
        for (Pod pod : output.keySet()) {
            if (pod.isReading()) {
                for (Mentor thisMentor : output.get(pod)) {
                    classContactList.append("\n" + thisMentor.printContactInfo());
                }
            }
        }

        classContactList.append("\n\n---------------------------------------------------");
        classContactList.append("\nMath Mentors Contact Information:");
        classContactList.append("\n" + Mentor.printContactInfoFieldOrder());
        for (Pod pod : output.keySet()) {
            if (pod.isMath()) {
                for (Mentor thisMentor : output.get(pod)) {
                    classContactList.append("\n" + thisMentor.printContactInfo());
                }
            }
        }

        classContactList.append("\n\n---------------------------------------------------");
        classContactList.append("\nWaitlisted Mentors Contact Information:");
        classContactList.append("\n" + Mentor.printContactInfoFieldOrder());
        for (Mentor waitListedMentor : mentorWaitList) {
            classContactList.append("\n" + waitListedMentor.printContactInfo());
        }

        classContactList.append("\n\n---------------------------------------------------");
        classContactList.append("\nTo be Manually Reviewed Contact Information:");
        classContactList.append("\n" + Mentor.printContactInfoFieldOrder());
        for (Mentor toBeReviewedMentor : toBeManuallyReviewed) {
            classContactList.append("\n" + toBeReviewedMentor.printContactInfo());
        }

        System.out.println(OUTPUT_FILE_PATH + OUTPUT_FILE_NAME + getCurrentDate() + ".csv");
        File file = new File(OUTPUT_FILE_PATH + OUTPUT_FILE_NAME + getCurrentDate() + ".csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.append(classContactList);
        }
    }

    public static ArrayList<Mentor> sortArrayListByReturning(ArrayList<Mentor> initialMentorList) {
        ArrayList<Mentor> sortedMentorList = new ArrayList<>();

        // Place all the returning Mentors in the order that they applied at the front of the ArrayList
        // (so returning Mentors who apply sooner get priority)
        // Place all the new Mentors in the order that they applied after the last returning Mentor
        // (so mew Mentors who apply sooner get priority)

        int lastReturningMentorIndex = 0;

        for (Mentor currentMentor : initialMentorList) {
            // Add returning Mentors to front of list
            if (currentMentor.isReturning()) {
                sortedMentorList.add(lastReturningMentorIndex, currentMentor);
                lastReturningMentorIndex++;
            }
            // Add new Mentors to end of list
            else sortedMentorList.add(currentMentor);
        }

        return sortedMentorList;
    }

    public static void placeMentorInPod(Mentor currentMentor) {// Match availability to name of Pod
        ArrayList<Pod> possiblePods = new ArrayList<>();

        for (Pod thisPod : allPods) {
            for (String thisAvailableTime : currentMentor.getAllAvailableTimes()) {
                System.out.println(thisAvailableTime);
                if (thisPod.getTime().equalsIgnoreCase(thisAvailableTime)
                        && ((thisPod.isReading() && currentMentor.isReadingMentor())
                        || (thisPod.isMath() && currentMentor.isMathMentor()))) {
                    possiblePods.add(thisPod);
                }
            }
        }

        // If there are no possible Pods the Mentor can be placed in,
        // add the Mentor to the toBeManuallyReviewed list
        if (possiblePods.size() == 0) {
            toBeManuallyReviewed.add(currentMentor);
            currentMentor.setAdditionalNotesAboutMentor(NO_POSSIBLE_PODS);
            goodList.remove(currentMentor);
            return;
        }

        // If current Mentor is returning, then go through the entire last term mentor list to see if they were in
        // a pod from last term. See if first names and last names match.
        // If so, check to see if the mentor's previous pod is in the list of Possible Pods
        // If yes and there is room in the pod, add mentor to their previous pod
        if (currentMentor.isReturning()) {
            for (LastTermMentor lastTermMentor : lastTermMentorList) {
                if (lastTermMentor.getFirstName().equalsIgnoreCase(currentMentor.getFirstName()) &&
                        lastTermMentor.getLastName().equalsIgnoreCase(currentMentor.getLastName())) {

                    // Iterate through all the possible pods until Mentor is placed
                    for (Pod thisPod : possiblePods) {
                        ArrayList<Mentor> podList = output.get(thisPod);

                        if (podList.size() < POD_MAX_SIZE && thisPod.getPodName().equalsIgnoreCase(lastTermMentor.getPodName())) {
                            podList.add(currentMentor);
                            goodList.remove(currentMentor);
                            lastTermMentorList.remove(lastTermMentor);
                            currentMentor.setAdditionalNotesAboutMentor(LAST_TERM_MENTOR);
                            currentMentor.setPod(thisPod.getPodName());

                            // Current mentor has been removed from goodList, so a new Mentor has replaced their
                            // previous index spot
                            return;
                        }
                    }
                }
            }
        }

        // Iterate through all the possible pods until Mentor is placed
        for (Pod thisPod : possiblePods) {
            ArrayList<Mentor> podList = output.get(thisPod);

            if (podList.size() < POD_MAX_SIZE) {
                podList.add(currentMentor);
                goodList.remove(currentMentor);
                currentMentor.setPod(thisPod.getPodName());
                // Current mentor has been removed from goodList, so a new Mentor has replaced their
                // previous index spot
                return;
            }
        }

        // No spots for Mentor, so add them to the mentorWaitList
        mentorWaitList.add(currentMentor);
        currentMentor.setAdditionalNotesAboutMentor(possiblePods.toString());
        goodList.remove(currentMentor);
    }

    public static String getCurrentTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String getCurrentDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    // Collect Data
    public static ArrayList<Pod> generateListOfFilledPods() {
        ArrayList<Pod> listOfFilledPods = new ArrayList<>();
        for (Pod thisPod : output.keySet()) {
            if (output.get(thisPod).size() == POD_MAX_SIZE) {
                listOfFilledPods.add(thisPod);
            };
        }

        return listOfFilledPods;
    }

    public static ArrayList<String> generateListOfNotFilledPods() {
        ArrayList<String> listOfNotFilledPods = new ArrayList<>();
        for (Pod thisPod : output.keySet()) {
            if (output.get(thisPod).size() < POD_MAX_SIZE) {
                numberOfOpenSpots = numberOfOpenSpots + (POD_MAX_SIZE - output.get(thisPod).size());

                listOfNotFilledPods.add(thisPod.getPodName() + " | " + thisPod.getTime() + " | " +
                        (POD_MAX_SIZE - output.get(thisPod).size()) + " Spots");
            };
        }

        return listOfNotFilledPods;
    }

    public static int calculateNumberOfPlacedMentors() {
        int numberOfPlacedMentors = 0;
        for (Pod thisPod : output.keySet()) {
            numberOfPlacedMentors = numberOfPlacedMentors + output.get(thisPod).size();
        }

        return numberOfPlacedMentors;
    }

    public static int calculateNumberOfIPMentors() {
        int numberOfIPMentors = 0;
        for (Pod thisPod : output.keySet()) {
            if (thisPod.isIP()) numberOfIPMentors = numberOfIPMentors + output.get(thisPod).size();
        }
        return numberOfIPMentors;
    }

    public static int calculateNumberOfOnlineMentors() {
        int numberOfOnlineMentors = 0;
        for (Pod thisPod : output.keySet()) {
            if (thisPod.isOnline()) numberOfOnlineMentors = numberOfOnlineMentors + output.get(thisPod).size();
        }
        return numberOfOnlineMentors;
    }
}
