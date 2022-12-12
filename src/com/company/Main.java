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

        // Step 2: Sort returning mentors first, then sort new mentors
        sortArrayListByReturning(goodList);

        // Step 3: Initialize all the possible times
        for (int i = 0; i < goodList.size(); i++) {
            Mentor currentMentor = goodList.get(i);
            ArrayList<String> currentMentorAvailability = currentMentor.allAvailableTimes;

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
        //Important: The initialization of Mentor already placed all the available in person times in front.

        // Step 4: Place the people who only have 1 preference
        for (int i = 0; i < goodList.size(); i++) {
            Mentor thisM = goodList.get(i);
            ArrayList<String> currentMT = thisM.allAvailableTimes;
            if (thisM.getNumOfAvailabilities() == 1 && thisM.getIsReturning()) {
                ArrayList<Mentor> currTimeField = output.get(currentMT.get(0));
                if (currTimeField.size() < 8) {
                    currTimeField.add(thisM);
                    goodList.remove(thisM);
                    i--;
                } else {
                    cannotHaveAPositionFor.add(thisM);
                }
            }
        }

        //Step 5: Place everyone else
        for (int i = 0; i < goodList.size(); i++) {
            Mentor thisM = goodList.get(i);
            ArrayList<String> currentMT = thisM.allAvailableTimes;
            boolean foundPosition = false;
            for (int j = 0; j < currentMT.size(); j++) {
                ArrayList<Mentor> currTimeField = output.get(currentMT.get(j));
                if (currTimeField.size() < 8) {
                    currTimeField.add(thisM);
                    foundPosition = true;
                    break;
                }
            }
            if (!foundPosition) {
                cannotHaveAPositionFor.add(thisM);
            }
        }

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("D:\\work-and-ECs\\lms-developer\\algorithms\\src\\com\\company\\output.txt")));

        //Todo: Create separate output class to separate logic

        //Step 6: Output of Program
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

        //code to read in from a CSV file still in progress
    }

    //    public static ArrayList<MentorWithGFields> sortMathReading(ArrayList<MentorWithGFields> currentList){
//        ArrayList<MentorWithGFields> list = new ArrayList<>();
//
//        for(int i = 0; i < currentList.size(); i++){
//            MentorWithGFields thisMen = currentList.get(i);
//            if(thisMen.isReadingMentor()){
//                sortedReadingList.add(thisMen);
//            }
//            else{
//                sortedMathList.add(thisMen);
//            }
//        }
//        return list;
//
//    }

    public static ArrayList<Mentor> sortArrayListByReturning(ArrayList<Mentor> currentList) {
        ArrayList<Mentor> list = new ArrayList<>();

        for (int i = 0; i < currentList.size(); i++) {
            Mentor thisMentor = currentList.get(i);
            if (thisMentor.getIsReturning()) {
                list.add(0, thisMentor);
            } else {
                list.add(thisMentor);
            }
        }
        return list;
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
