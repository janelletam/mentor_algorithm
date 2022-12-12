package learningbuddiesnetwork.company;

import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Mentor> initialList;
    static ArrayList<MentorWithGFields> goodList;
    static ArrayList<MentorWithGFields> revisedList;
//    static ArrayList<MentorWithGFields> sortedReadingList;
//    static ArrayList<MentorWithGFields> sortedMathList;

    static ArrayList<MentorWithGFields> toBeReviewed;

    static HashMap<String, ArrayList<MentorWithGFields>> output;
    static ArrayList<MentorWithGFields> cannotHaveAPositionFor;

//    static HashMap<String, ArrayList<MentorWithGFields>> ReadingOutPut;


    public static void main(String[] args) throws IOException {
        // write your code here

        //create array list of mentors that before sorted

        initialList = new ArrayList<>();
        revisedList = new ArrayList<>();
//        sortedMathList = new ArrayList<>();
//        sortedReadingList = new ArrayList<>();
        toBeReviewed = new ArrayList<>();
        goodList = new ArrayList<>();
        output = new HashMap<>();
        cannotHaveAPositionFor = new ArrayList<>();


        //initialization finished


        initialList = CSVReader.readMentorsFromCSV("/Users/timothylin/IdeaProjects/mentor_algorithm1/src/learningbuddiesnetwork/company/testCSVFile.csv");
        //input finished

        for (int i = 1; i < initialList.size(); i++) {
            MentorWithGFields newM = new MentorWithGFields(initialList.get(i));
            revisedList.add(newM);
        }
        //now all the property lists are revised

        //Step 1: First Check Validity of Volunteers
        for (int i = 0; i < revisedList.size(); i++) {
            MentorWithGFields newM = revisedList.get(i);
            if (newM.isValid()) {
                goodList.add(newM);
            } else {
                toBeReviewed.add(newM);
            }
        }

        //Step 2: Sort the returning and new mentors with returning coming first
        sortArrayListByReturning(goodList);

        //Step 3:Initialize all the possible times
        for (int i = 0; i < goodList.size(); i++) {
            MentorWithGFields thisM = goodList.get(i);
            ArrayList<String> thisMAva = thisM.allAvailableTimes;
            for (int j = 0; j < thisMAva.size(); j++) {
                String thisAvailableTime = thisMAva.get(j);

                if (!output.containsKey(thisAvailableTime)) {
                    output.put(thisAvailableTime, new ArrayList<MentorWithGFields>());
                }
//                if(!MathOutPut.containsKey(thisAvailableTime)){
//                    MathOutPut.put(thisAvailableTime,new ArrayList<MentorWithGFields>());
//                }
//                if(!ReadingOutPut.containsKey(thisAvailableTime)){
//                    ReadingOutPut.put(thisAvailableTime,new ArrayList<MentorWithGFields>());
//                }
            }
        }

        //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //Important: The initialization of MentorWithGFields already placed all the available in person times in front.

        //Step 4: Place the people who only have 1 preference
        for (int i = 0; i < goodList.size(); i++) {
            MentorWithGFields thisM = goodList.get(i);
            ArrayList<String> currentMT = thisM.allAvailableTimes;
            if (thisM.getNumOfAvailabilities() == 1 && thisM.isReturning()) {
                ArrayList<MentorWithGFields> currTimeField = output.get(currentMT.get(0));
                if (currTimeField.size() < 8) {
                    currTimeField.add(thisM);
                    goodList.remove(thisM);
                    i--;
                } else {
                    cannotHaveAPositionFor.add(thisM);
                }
            }
        }

        //Step 5: Place everyoneElse
        for (int i = 0; i < goodList.size(); i++) {
            MentorWithGFields thisM = goodList.get(i);
            ArrayList<String> currentMT = thisM.allAvailableTimes;
            boolean foundPosition = false;
            for (int j = 0; j < currentMT.size(); j++) {
                ArrayList<MentorWithGFields> currTimeField = output.get(currentMT.get(j));
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

        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("/Users/timothylin/IdeaProjects/mentor_algorithm1/src/learningbuddiesnetwork/company/output.txt")));

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

    public static ArrayList<MentorWithGFields> sortArrayListByReturning(ArrayList<MentorWithGFields> currentList) {
        ArrayList<MentorWithGFields> list = new ArrayList<>();

        for (int i = 0; i < currentList.size(); i++) {
            MentorWithGFields thisMentor = currentList.get(i);
            if (thisMentor.isReturning()) {
                list.add(0, thisMentor);
            } else {
                list.add(thisMentor);
            }
        }
        return list;

    }

    public static void addToMathList(MentorWithGFields M, String Time) {

    }

    public static void addToReadingList(MentorWithGFields M, String Time) {

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


//    public static int binarySearch(ArrayList<Mentor> currentList, int value){
//
//        //TODO: binary search to be implemented.
//        int right = currentList.size();
//        int left = 0;
//
//        while(left <= right){
//            int mid = left + (right -left)/2;
//
//
//        }
//        return -1;
//    }


}
