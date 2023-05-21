package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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
    static ArrayList<newMentor> allMentors = new ArrayList<>();
    static ArrayList<LastTermMentor> lastTermVolunteer = new ArrayList<>();
    static ArrayList<Pod> allPods = new ArrayList<>();
    static HashMap<Pod,ArrayList<newMentor>> sortedList = new HashMap<>();
    static HashSet<newMentor> blackList = new HashSet<>();
    public static void main(String[] args) {
        //input all the mentors in the allMentors class
        //input all the pods

        //input blackList

        newMentor a = new newMentor();
        if(a.equals)



        for (int i = 0; i < allPods.size(); i++) {
            sortedList.put(allPods.get(i),new ArrayList<>());
        }

        for (int i = 0; i < allMentors.size(); i++) {
            newMentor thisMentor = allMentors.get(i);
            if(blackList.contains(thisMentor)){
                continue;
            }

            boolean added = false;
            if(Integer.parseInt(thisMentor.getMWT5to6OL()) == 1){
                if(sortedList.get().size() >= 8){ //can be added
                    sortedList.get().add(thisMentor);
                    added = true;
                }
            }
            else if(Integer.parseInt(thisMentor.getMWT9to11OL()) == 1){
                if(sortedList.get().size() >= 8){ //can be added
                    sortedList.get().add(thisMentor);
                    added = true;
                }
            }

            if(added){
                continue;
            }

            if(Integer.parseInt(thisMentor.getTT4to6Mosaic()) == 2){
                if(sortedList.get().size() >= 8){ //can be added
                    sortedList.get().add(thisMentor);
                    added = true;
                }
            }
            else if(Integer.parseInt(thisMentor.getTT4to6Sunset()) == 2){
                if(sortedList.get().size() >= 8){ //can be added
                    sortedList.get().add(thisMentor);
                    added = true;
                }
            }
            else if(Integer.parseInt(thisMentor.getTT9to11Surrey()) == 2){
                if(sortedList.get().size() >= 8){ //can be added
                    sortedList.get().add(thisMentor);
                    added = true;
                }
            }


            if(added){
                break;
            }

            for (int i = 0; i < allMentors.size(); i++) {
                newMentor thisMentor = allMentors.get(i);
                if(blackList.contains(thisMentor)){
                    continue;
                }

                boolean added = false;
                if(Integer.parseInt(thisMentor.getMWT5to6OL()) == 2){
                    if(sortedList.get().size() >= 8){ //can be added
                        sortedList.get().add(thisMentor);
                        added = true;
                    }
                }
                else if(Integer.parseInt(thisMentor.getMWT9to11OL()) == 2){
                    if(sortedList.get().size() >= 8){ //can be added
                        sortedList.get().add(thisMentor);
                        added = true;
                    }
                }

                if(added){
                    continue;
                }

                if(Integer.parseInt(thisMentor.getTT4to6Mosaic()) == 2){
                    if(sortedList.get().size() >= 8){ //can be added
                        sortedList.get().add(thisMentor);
                        added = true;
                    }
                }
                else if(Integer.parseInt(thisMentor.getTT4to6Sunset()) == 1){
                    if(sortedList.get().size() >= 8){ //can be added
                        sortedList.get().add(thisMentor);
                        added = true;
                    }
                }
                else if(Integer.parseInt(thisMentor.getTT9to11Surrey()) == 2){
                    if(sortedList.get().size() >= 8){ //can be added
                        sortedList.get().add(thisMentor);
                        added = true;
                    }
                }

        }
    }


}
