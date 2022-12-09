package learningbuddiesnetwork.company;

import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Main {

    static ArrayList<Mentor> initialList;
    static ArrayList<Mentor> sortedReadingList;
    static ArrayList<Mentor> sortedMathList;
    static ArrayList<Mentor> notApplicable;



    public static void main(String[] args) {
	// write your code here

        //create array list of mentors that before sorted

        ArrayList<Mentor> list = new ArrayList<>();

        initialList = new ArrayList<>();
        sortedMathList = new ArrayList<>();
        sortedReadingList = new ArrayList<>();
        notApplicable = new ArrayList<>();


        //TODO: reimplement .sort in Collection after writing to compare function in Mentor





        //code to read in from a CSV file still in progress




    }

    public static ArrayList<Mentor> sortMathReading(ArrayList<Mentor> currentList){
        ArrayList<Mentor> list = new ArrayList<>();

        for(int i = 0; i < currentList.size(); i++){
            Mentor thisMen = currentList.get(i);
            if(thisMen.isReadingMentor()){
                sortedReadingList.add(thisMen);
            }
            if(thisMen.isMathMentor()){
                sortedMathList.add(thisMen);
            }
        }
        return list;

    }
    public static ArrayList<Mentor> sortArrayListByReturning(ArrayList<Mentor> currentList){
        ArrayList<Mentor> list = new ArrayList<>();

        for(int i = 0; i < currentList.size(); i++){
            Mentor thisMen = currentList.get(i);
            if(thisMen.isReturning()){
                list.add(0,thisMen);
            }
            else{
                list.add(thisMen);
            }
        }
        return list;

    }


    public static ArrayList<Mentor> isPhotoVideoConsent(ArrayList<Mentor> currentList){
        ArrayList<Mentor> list = new ArrayList<>();

        for(int i = 0; i < currentList.size(); i++){
            Mentor thisMen = currentList.get(i);
            if(thisMen.isReturning()){
                list.add(0,thisMen);
            }
            else{
                list.add(thisMen);
            }
        }
        return list;

    }


    public static int binarySearch(ArrayList<Mentor> currentList, int value){

        //TODO: binary search to be implemented.
        int right = currentList.size();
        int left = 0;

        while(left <= right){
            int mid = left + (right -left)/2;


        }
        return -1;
    }



}
