package com.company;

import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Mentor> initialList;
    static ArrayList<Mentor> goodList;
    static ArrayList<Mentor> toBeManuallyReviewed;
//    static ArrayList<Mentor> sortedReadingList;
//    static ArrayList<Mentor> sortedMathList;

    static HashMap<String, ArrayList<Mentor>> output;
    static ArrayList<Mentor> cannotHaveAPositionFor;

    static final int ONE_PREFERENCE = 1;
    static final int POD_MAX_SIZE = 8;

//    static HashMap<String, ArrayList<Mentor>> ReadingOutPut;

    public static void main(String[] args) throws IOException {
        // Step 0: Initialization - Create multiple ArrayLists of mentors before sort
        initialList = new ArrayList<>();
        toBeManuallyReviewed = new ArrayList<>();
        goodList = new ArrayList<>();
        output = new HashMap<>();
        cannotHaveAPositionFor = new ArrayList<>();
        System.out.println("Finished initialization");

        // Read from csv input file (application form data)
        initialList = CSVReader.readMentorsFromCSV("src/com/company/testCSVFile.csv");
        System.out.println("Finished reading from input");

        // Step 1: First Check Validity of Volunteers
        for (int i = 0; i < initialList.size(); i++) {
            Mentor currentMentor = initialList.get(i);
            if (currentMentor.isValid()) {
                goodList.add(currentMentor);
            } else {
                toBeManuallyReviewed.add(currentMentor);
            }
        }

        // Step 2: Put returning mentors first at the beginning of the list, then add new mentors
        goodList = sortArrayListByReturning(goodList);

        // Step 3: Initialize all the possible times
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

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //Important: The initialization of Mentor already placed all the available Clayton and in person times in front.

        // Step 4: Place the people who only have 1 preference
        for (int i = 0; i < goodList.size(); i++) {
            Mentor currentMentor = goodList.get(i);
            if (currentMentor.getAllAvailableTimes().size() == ONE_PREFERENCE && currentMentor.getIsReturning()) {
                ArrayList<Mentor> currentTimeField = output.get(currentMentor.getAllAvailableTimes().get(0));
                if (currentTimeField.size() < POD_MAX_SIZE) {
                    currentTimeField.add(currentMentor);
                    goodList.remove(currentMentor);
                    // Current mentor has been removed from goodList, so a new Mentor has replaced their
                    // previous index spot
                    i--;
                } else {
                    // Mentor has only indicated one availability. However, there are no more spots in that pod
                    cannotHaveAPositionFor.add(currentMentor);
                }
            }
        }

        // Step 5: Place everyone else
        for (int i = 0; i < goodList.size(); i++) {
            Mentor currentMentor = goodList.get(i);
            boolean foundPosition = false;
            for (int j = 0; j < currentMentor.getAllAvailableTimes().size(); j++) {
                ArrayList<Mentor> currentTimeField = output.get(currentMentor.getAllAvailableTimes().get(j));
                // Add Mentor into the first pod with available spots
                if (currentTimeField.size() < POD_MAX_SIZE) {
                    currentTimeField.add(currentMentor);
                    foundPosition = true;
                    break;
                }
            }
            // Mentor has not indicated availability
            if (!foundPosition) {
                cannotHaveAPositionFor.add(currentMentor);
            }
        }

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:\\work-and-ECs\\lms-developer\\algorithms\\src\\com\\company\\output.txt")));

        //Todo: Create separate output class to separate logic

        // Step 6: Output of Program
        for (String time : output.keySet()
        ) {
            out.println();
            out.println(time + ":");
            for (int i = 0; i < output.get(time).size(); i++) {
                out.println(output.get(time).get(i));
            }
            out.println();
        }
        out.println("\nCannot have position for");
        for (int i = 0; i < cannotHaveAPositionFor.size(); i++) {
            out.println(cannotHaveAPositionFor.get(i));
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
            Mentor thisMentor = currentList.get(i);
            // Add returning mentors to front of list
            if (thisMentor.getIsReturning()) {
                sortedMentorList.add(lastReturningMentorIndex, thisMentor);
                lastReturningMentorIndex++;
            }
            // Add new mentors to end of list
            else sortedMentorList.add(thisMentor);
        }

        return sortedMentorList;
    }

    public static void addToMathList(Mentor M, String Time) {
    }

    public static void addToReadingList(Mentor M, String Time) {
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

}
