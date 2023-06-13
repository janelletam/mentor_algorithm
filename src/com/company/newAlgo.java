package com.company;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.*;
import java.time.*;


public class newAlgo {
    /*
    Next Steps:
    - Input
    - Restructuring the Mentor classes
    - Output
    - Debugging
    - Integration
     */



    /*
    Input lists:
        The following lists are the inputs
        -   Volunteer List: A list of all the volunteers who have applied
        -   Black List: List of Volunteers who are blocked from the program
        -   Changes List: List of Volunteers who later moved to a new Pod/ were removed from the program
        -   Last term mentor List: List of Volunteer that are in LBN last term
        -   Pod List: list of pods that are available this term
     */
    static ArrayList<termMentor> allMentors = new ArrayList<>();
    static ArrayList<LastTermMentor> lastTermVolunteer = new ArrayList<>();
    static ArrayList<Pod> allPods = new ArrayList<>();
    static ArrayList<Mentor> blackList = new ArrayList<>();

    static ArrayList<updates> changes = new ArrayList<>();

    static ArrayList<Mentor> waitlist = new ArrayList<>();


    static final int maxNumOfPplPerPod = 10;
    /*
    Output lists:
        - Sorted List
        - List to be manually reviewed
     */


    static final String MENTOR_CSV_FILE_PATH = "src/com/company/USE - Winter 2023 - LBN Mentor Registration-export-data-14-01-2023-08_42_32_PM.csv";
    static final String POD_CSV_FILE_PATH = "src/com/company/allPods.csv";
    static final String LAST_TERM_MENTORS_FILE_PATH = "src/com/company/Fall 2022 Registration Data.csv";
    static final String OUTPUT_FILE_PATH = "src/com/company/";
    static final String OUTPUT_FILE_NAME = "Generated-Class-Contact-List-";


    public static void main(String[] args) throws IOException {
        //reads in all the input


        for (int i = 0; i < allMentors.size(); i++) {
            for (int j = i; j < allMentors.size(); j++) {
                if (allMentors.get(i).equals(allMentors.get(j))) {
                    allMentors.remove(j);
                    j--;
                }
            }
        }
        //blacklist
        for (int i = 0; i < allMentors.size(); i++) {
            for (int j = i; j < blackList.size(); j++) {
                if (allMentors.get(i).equals(blackList.get(j))) {
                    allMentors.remove(j);
                    j--;
                }
            }
        }
        //put all last term mentors first
        ArrayList<termMentor> sortedMentors = new ArrayList<>();
        for (int i = 0; i < allMentors.size(); i++) {
            for (int j = 0; j < lastTermVolunteer.size(); j++) {
                if (allMentors.get(i).equals(lastTermVolunteer.get(j))) {
                    sortedMentors.add(0,allMentors.get(i));
                } else {
                    sortedMentors.add(allMentors.get(i));
                }
            }
        }

        for (int i = 0; i < sortedMentors.size(); i++) {
            placeMentorInPod(sortedMentors.get(i));
        }

        for (int i = 0; i < sortedMentors.size(); i++) {
            if(sortedMentors.get(i).termPod == null){
                waitlist.add(sortedMentors.get(i));
                sortedMentors.remove(i);
                i--;
            }
        }

        for (int i = 0; i < changes.size(); i++) {
            updates thisUpdate = changes.get(i);
            if(thisUpdate.id == 1){
                for (int j = 0; j < sortedMentors.size(); j++) {
                    if(sortedMentors.get(i).equals(thisUpdate.currM)){
                        sortedMentors.remove(i);
                        i--;
                    }
                }
            }
            else{
                for (int j = 0; j < sortedMentors.size(); j++) {
                    if(sortedMentors.get(i).equals(thisUpdate.currM)){
                        termMentor thisT = sortedMentors.get(i);
                        thisT.termPod = thisUpdate.changedPod;
                        sortedMentors.remove(i);
                        sortedMentors.add(i,thisT);
                    }
                }
            }
        }

        //output

        classContactListOutput(sortedMentors);


    }

    public static void classContactListOutput(ArrayList<termMentor> sorted) throws IOException {
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

        classContactList.append("\n\nTotal number of applicants: " + allMentors.size());

        classContactList.append("\n\nTotal number of mentors in In-Person Programs: " + calculateNumberOfIPMentors());
        classContactList.append("\nTotal number of mentors in Online Programs: " + calculateNumberOfOnlineMentors());
//        classContactList.append("\nTotal number of mentors in Clayton Programs: " + calculateNumberOfClaytonMentors());



        classContactList.append("\n\nTotal number of placed mentors (qualified): " + calculateNumberOfPlacedMentors());
        classContactList.append("\nTotal number of waitlisted mentors: " + waitlist.size());
//        classContactList.append("\n\nTotal number of mentor spots left: " + numberOfOpenSpots);

        for (Pod pod: allPods) {
            classContactList.append("\n\n---------------------------------------------------");
            classContactList.append("\n" + pod.toString());
            classContactList.append("\n" + Mentor.printMentorOutputFieldOrder());
            for (termMentor thisMentor : sorted) {
                if(thisMentor.termPod.equals(pod)) {
                    classContactList.append("\n" + thisMentor.toString());
                }
            }
        }

        classContactList.append("\n\n---------------------------------------------------");
        classContactList.append("\nMentor Wait List:");
        classContactList.append("\n" + Mentor.printMentorOutputFieldOrder());
        for (Mentor waitListedMentor : waitlist) {
            classContactList.append("\n" + waitListedMentor);
        }

//        classContactList.append("\n\n---------------------------------------------------");
//        classContactList.append("\nTo Be Manually Reviewed:");
//        classContactList.append("\n" + Mentor.printMentorOutputFieldOrder());
//        for (Mentor toBeReviewedMentor : toBeManuallyReviewed) {
//            classContactList.append("\n" + toBeReviewedMentor);
//        }

        System.out.println(OUTPUT_FILE_PATH + OUTPUT_FILE_NAME + getCurrentDate() + ".csv");
        File file = new File(OUTPUT_FILE_PATH + OUTPUT_FILE_NAME + getCurrentDate() + ".csv");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.append(classContactList);
        }
    }


    public static void placeMentorInPod(termMentor currentMentor) {// Match availability to name of Pod
        //if mentor has first priority, put them there
        for (Pod thisPod : allPods) {
            if (currentMentor.allAvailableTimes.get(thisPod).equals((Integer) 1)) {
                if (thisPod.numOfMentors < maxNumOfPplPerPod) {
                    currentMentor.termPod = thisPod;
                    thisPod.numOfMentors++;
                    return;
                }
            }
        }

        //second priority
        for (Pod thisPod : allPods) {
            if (currentMentor.allAvailableTimes.get(thisPod).equals((Integer) 2)) {
                if (thisPod.numOfMentors < maxNumOfPplPerPod) {
                    currentMentor.termPod = thisPod;
                    thisPod.numOfMentors++;
                    return;
                }
            }
        }

        //third priority
        for (Pod thisPod : allPods) {
            if (currentMentor.allAvailableTimes.get(thisPod).equals((Integer) 3)) {
                if (thisPod.numOfMentors < maxNumOfPplPerPod) {
                    currentMentor.termPod = thisPod;
                    thisPod.numOfMentors++;
                    return;
                }
            }
        }
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
        for (Pod thisPod : allPods) {
            if (thisPod.numOfMentors == maxNumOfPplPerPod) {
                listOfFilledPods.add(thisPod);
            };
        }

        return listOfFilledPods;
    }

    public static ArrayList<String> generateListOfNotFilledPods() {
        ArrayList<String> listOfNotFilledPods = new ArrayList<>();
        for (Pod thisPod : allPods) {
            if (thisPod.numOfMentors < maxNumOfPplPerPod) {

                listOfNotFilledPods.add(thisPod.getPodName() + " | " + thisPod.getTime() + " | " +
                        (maxNumOfPplPerPod - thisPod.numOfMentors) + " Spots");
            };
        }

        return listOfNotFilledPods;
    }

    public static int calculateNumberOfPlacedMentors(ArrayList<termMentor> a) {
        return a.size();
    }

    public static int calculateNumberOfIPMentors(ArrayList<termMentor> a) {
        int numberOfIPMentors = 0;
        for (termMentor t : a) {
            if (t.termPod.isIP()) numberOfIPMentors++;
        }
        return numberOfIPMentors;
    }

    public static int calculateNumberOfOnlineMentors(ArrayList<termMentor> a) {
        int numberOfOnlineMentors = 0;
        for (termMentor t : a) {
            if (t.termPod.isIP()) numberOfOnlineMentors++;
        }
        return numberOfOnlineMentors;
    }


}
