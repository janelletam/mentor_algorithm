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
        initialMentorList = CSVReader.readMentorsFromCSV("src/com/company/allMentors.csv");
        System.out.println("Finished reading from mentor input");

        allPods = CSVReader.readPodsFromCSV("src/com/company/allPods.csv");
        System.out.println("Finished reading from pod input");

        // Step 1: First Check Validity of Volunteers
        for (int i = 0; i < initialMentorList.size(); i++) {
            Mentor currentMentor = initialMentorList.get(i);
            if (currentMentor.isValid()) {
                goodList.add(currentMentor);
            } else {
                toBeManuallyReviewed.add(currentMentor);
            }
        }

        // Step 2: Put returning mentors first at the beginning of the list, then add new mentors
        goodList = sortArrayListByReturning(goodList);

        // Step 3: Initialize all the possible pods. Each pod should hold an ArrayList of mentors
        for (int i = 0; i < allPods.size(); i++) {
            output.put(allPods.get(i), new ArrayList<Mentor>());
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        // Important: The initialization of Mentor already placed all the available Clayton and in person times in front.
        // Therefore, the IP and Clayton availabilities will be prioritized

        // Step 4: Place the returning Mentors who only have 1 preference
        for (int i = 0; i < goodList.size(); i++) {
            Mentor currentMentor = goodList.get(i);
            if (currentMentor.getAllAvailableTimes().size() == ONE_PREFERENCE && currentMentor.getIsReturning()) {
                placeMentorInPod(currentMentor);
                // Current mentor has been removed from goodList, so a new Mentor has replaced their previous index spot
                i--;
            }
        }

        // Step 5: Place everyone else
        for (int i = 0; i < goodList.size(); i++) {
            Mentor currentMentor = goodList.get(i);
            placeMentorInPod(currentMentor);
            // Current mentor has been removed from goodList, so a new Mentor has replaced their previous index spot
            i--;
        }

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:\\work-and-ECs\\lms-developer\\algorithms\\src\\com\\company\\output.txt")));

        //Todo: Create separate output class to separate logic

        // Step 6: Output of Program
        for (Pod pod : output.keySet()
        ) {
            out.println();
            out.println(pod.toString());
            for (int i = 0; i < output.get(pod).size(); i++) {
                out.println(output.get(pod).get(i));
            }
            out.println();
        }

        out.println();
        out.println("\nMentor Wait List:");
        for (int i = 0; i < mentorWaitList.size(); i++) {
            out.println(mentorWaitList.get(i));
        }

        out.println();
        out.println("\nTo be manually reviewed:");
        for (int i = 0; i < toBeManuallyReviewed.size(); i++) {
            out.println(toBeManuallyReviewed.get(i));
        }

        out.close();
    }

    public static ArrayList<Mentor> sortArrayListByReturning(ArrayList<Mentor> currentList) {
        ArrayList<Mentor> sortedMentorList = new ArrayList<>();

        // Place all the returning mentors in the order that they applied at the front of the ArrayList
        // (so returning mentors who apply sooner get priority)
        // Place all the new mentors in the order that they applied after the last returning mentor
        // (so mew mentors who apply sooner get priority)

        int lastReturningMentorIndex = 0;

        for (int i = 0; i < currentList.size(); i++) {
            Mentor currentMentor = currentList.get(i);
            // Add returning mentors to front of list
            if (currentMentor.getIsReturning()) {
                sortedMentorList.add(lastReturningMentorIndex, currentMentor);
                lastReturningMentorIndex++;
            }
            // Add new mentors to end of list
            else sortedMentorList.add(currentMentor);
        }

        return sortedMentorList;
    }

    public static boolean placeMentorInPod(Mentor currentMentor) {
        // Match availability to name of pod
        ArrayList<Pod> possiblePods = new ArrayList<>();

        for (int i = 0; i < allPods.size(); i++) {
            for (int j = 0; j < currentMentor.getAllAvailableTimes().size(); j++) {
                System.out.println(currentMentor.getAllAvailableTimes().get(j));
                if (allPods.get(i).getTime().equalsIgnoreCase(currentMentor.getAllAvailableTimes().get(j))
                        && ((allPods.get(i).isReading() && currentMentor.getIsReadingMentor())
                        || (allPods.get(i).isMath() && currentMentor.getIsMathMentor()))) {
                    possiblePods.add(allPods.get(i));
                }
            }
        }

        // If there are no possible pods the mentor can be placed in,
        // add the Mentor to the toBeManuallyReviewed list
        if (possiblePods.size() == 0) {
            toBeManuallyReviewed.add(currentMentor);
            goodList.remove(currentMentor);
            return false;
        }

        // Iterate through all the possible pods until Mentor is placed
        for (int j = 0; j < possiblePods.size(); j++) {
            ArrayList<Mentor> currentTimeField = output.get(possiblePods.get(j));
            if (currentTimeField.size() < POD_MAX_SIZE) {
                currentTimeField.add(currentMentor);
                goodList.remove(currentMentor);
                // Current mentor has been removed from goodList, so a new Mentor has replaced their
                // previous index spot
                return true;
            }
        }

        // No spots for Mentor, so add them to the mentorWaitList
        mentorWaitList.add(currentMentor);
        goodList.remove(currentMentor);
        return false;
    }

//    public static ArrayList<Mentor> isPhotoVideoConsent(ArrayList<Mentor> currentList){
//        ArrayList<Mentor> list = new ArrayList<>();
//
//        for(int i = 0; i < currentList.size(); i++){
//            Mentor thisMen = currentList.get(i);
//            if(thisMen.isReturning()){
//                list.add(0,thisMen);
//            }
//            else{
//                list.add(thisMen);
//            }
//        }
//        return list;
//
//    }

    /*
        for (int i = 0; i < goodList.size(); i++) {
            Mentor currentMentor = goodList.get(i);
            ArrayList<String> currentMentorAvailability = currentMentor.getAllAvailableTimes();

            for (int j = 0; j < currentMentorAvailability.size(); j++) {
                String thisAvailableTime = currentMentorAvailability.get(j);

                if (!output.containsKey(thisAvailableTime)) {
                    output.put(thisAvailableTime, new ArrayList<Mentor>());
                }
//                if(!MathOutPut.containsKey(thisAvailableTime)){
//                    MathOutPut.put(thisAvailableTime,new ArrayList<Mentor>());
//                }
//                if(!ReadingOutPut.containsKey(thisAvailableTime)){
//                    ReadingOutPut.put(thisAvailableTime,new ArrayList<Mentor>());
//                }
            }
        }
*/

}
