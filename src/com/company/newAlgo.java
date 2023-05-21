package com.company;

import java.util.*;


public class newAlgo {

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

    static final int maxNumOfPplPerPod = 10;
    /*
    Output lists:
        - Sorted List
        - List to be manually reviewed

     */
    public static void main(String[] args) {
        //reads in all the input
        for (int i = 0; i < allMentors.size(); i++) {
            for (int j = i; j < allMentors.size(); j++) {
                if (allMentors.get(i).equals(allMentors.get(j))) {
                    allMentors.remove(j);
                    j--;
                }
            }
        }

        for (int i = 0; i < allMentors.size(); i++) {
            for (int j = i; j < blackList.size(); j++) {
                if (allMentors.get(i).equals(blackList.get(j))) {
                    allMentors.remove(j);
                    j--;
                }
            }
        }

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

        for (int i = 0; i < allMentors.size(); i++) {
            placeMentorInPod(sortedMentors.get(i));
        }


    }


    public static void placeMentorInPod(termMentor currentMentor) {// Match availability to name of Pod

        for (Pod thisPod : allPods) {
            if (currentMentor.allAvailableTimes.get(thisPod).equals((Integer) 1)) {
                if (thisPod.numOfMentors < maxNumOfPplPerPod) {
                    currentMentor.termPod = thisPod;
                    thisPod.numOfMentors++;
                    return;
                }
            }
        }

        for (Pod thisPod : allPods) {
            if (currentMentor.allAvailableTimes.get(thisPod).equals((Integer) 2)) {
                if (thisPod.numOfMentors < maxNumOfPplPerPod) {
                    currentMentor.termPod = thisPod;
                    thisPod.numOfMentors++;
                    return;
                }
            }
        }

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


}
