package com.company;

import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Mentor> initialMentorList;
    static ArrayList<Mentor> goodList;

    static ArrayList<Mentor> toBeManuallyReviewed;

    //Todo: Each term, update availability list
    static ArrayList<Pod> allPods;
    static LinkedHashMap<Pod, ArrayList<Mentor>> output;
    static ArrayList<Mentor> mentorWaitList;

    static final int ONE_PREFERENCE = 1;
    static final int POD_MAX_SIZE = 8;

    static final String MENTOR_CSV_FILE_PATH = "src/com/company/Winter 2023 - LBN Mentor Registration-export-data-11-01-2023-10_17_56_PM.csv";
    static final String POD_CSV_FILE_PATH = "src/com/company/allPods.csv";
    static final String OUTPUT_FILE_PATH = "src/com/company/output.txt";

    public static void main(String[] args) throws IOException {
        // Step 0: Initialization - Create multiple ArrayLists of mentors before sort
        initialMentorList = new ArrayList<>();
        toBeManuallyReviewed = new ArrayList<>();
        goodList = new ArrayList<>();

        // For Mentors who have indicated availability, but there are not spots for them
        mentorWaitList = new ArrayList<>();

        allPods = new ArrayList<>();
        output = new LinkedHashMap<>();

        System.out.println("Finished initialization");

        // Step 0: Read from CSV files to initialize all mentors and pods
        initialMentorList = CSVReader.readMentorsFromCSV(MENTOR_CSV_FILE_PATH);
        System.out.println("Finished reading from mentor input");

        allPods = CSVReader.readPodsFromCSV(POD_CSV_FILE_PATH);
        System.out.println("Finished reading from pod input");

        // Step 1: First check validity of Mentors
        for (Mentor currentMentor : initialMentorList) {
            if (currentMentor.isValid()) {
                goodList.add(currentMentor);
            } else {
                toBeManuallyReviewed.add(currentMentor);
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

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE_PATH)));

        //Todo: Create separate output class to separate logic

        // Step 6: Output of Program
        for (Pod pod : output.keySet()) {
            out.println();
            out.println(pod.toString());
            for (Mentor thisMentor : output.get(pod)) {
                out.println(thisMentor);
            }
            out.println();
        }

        out.println("\n\nMentor Wait List:");
        for (Mentor waitListedMentor : mentorWaitList) {
            out.println(waitListedMentor);
        }

        out.println("\n\nTo Be Manually Reviewed:");
        for (Mentor toBeReviewedMentor : toBeManuallyReviewed) {
            out.println(toBeReviewedMentor);
        }

        out.close();
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

    public static void placeMentorInPod(Mentor currentMentor) {
        // Match availability to name of Pod
        ArrayList<Pod> possiblePods = new ArrayList<>();

        for (Pod thisPod : allPods) {
            for (String thisAvailableTime : currentMentor.getAllAvailableTimes()) {
                System.out.println(thisAvailableTime);
                if (thisPod.getTime().equalsIgnoreCase(thisAvailableTime)
                        && (thisPod.isReading() && currentMentor.isReadingMentor())
                        || (thisPod.isMath() && currentMentor.isMathMentor())) {
                    possiblePods.add(thisPod);
                }
            }
        }

        // If there are no possible Pods the Mentor can be placed in,
        // add the Mentor to the toBeManuallyReviewed list
        if (possiblePods.size() == 0) {
            toBeManuallyReviewed.add(currentMentor);
            goodList.remove(currentMentor);
            return;
        }

        // Iterate through all the possible pods until Mentor is placed
        for (Pod thisPod : possiblePods) {
            ArrayList<Mentor> podList = output.get(thisPod);
            if (podList.size() < POD_MAX_SIZE) {
                podList.add(currentMentor);
                goodList.remove(currentMentor);
                // Current mentor has been removed from goodList, so a new Mentor has replaced their
                // previous index spot
                return;
            }
        }

        // No spots for Mentor, so add them to the mentorWaitList
        mentorWaitList.add(currentMentor);
        goodList.remove(currentMentor);
    }

}
