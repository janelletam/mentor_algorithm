package com.company;
import java.util.*;
import java.io.*;

public class newMentor{

    // All fields below are from application form
    // NOTE: To count total number of availabilities, use getAllAvailableTimes().size()

    private String firstName;
    private String preferredName;
    private String lastName;

    private String phoneNumber;
    //Todo: Join these fields together

    private String programPreference;
    private String MWT9to11OL;
    private String MWT5to6OL;
    private String TT9to11Surrey;
    private String TT4to6Sunset;
    private String TT4to6Mosaic;

    // Our own variable
    private ArrayList<String> allAvailableTimes;


    //"Submission Date","First Name (Legal)","Preferred First Name (if different from legal name)","Last Name (Legal)","Phone Number","Program Preference Options","Online - Mondays, Wednesdays & Thursdays 9:40 - 11:10 am","Online - Mondays, Wednesdays & Thursdays 5:10 - 6:40 pm","In-Person at Surrey City Centre Library - Tuesdays & Thursdays 9:30 - 11:00 am","In-Person at Sunset Community Centre - Tuesdays & Thursdays 4:30 - 6:00 pm","In-Person at Mosaic Family Centre - Tuesdays & Thursdays 4:30 - 6:00 pm"

    public newMentor(String submissionDate, String firstName, String preferredName, String lastName,
                  String phoneNumber, String programPreference, String MWT9to11OL, String MWT5to6OL, String TT9to11Surrey, String TT4to6Sunset, String TT4to6Mosaic ) {
        this.firstName = firstName;
        this.preferredName = preferredName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.programPreference = programPreference;
        this.MWT9to11OL = MWT9to11OL;
        this.MWT5to6OL = MWT5to6OL;
        this.TT9to11Surrey = TT9to11Surrey;
        this.TT4to6Sunset = TT4to6Sunset;
        this.TT4to6Mosaic = TT4to6Mosaic;

        // In the application form, users can choose to be either a Reading Mentor or a Math Mentor or both
        // Prioritize Reading Mentors b/c there are more reading pods
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPreferredName() {
        return preferredName;
    }

    public void setPreferredName(String preferredName) {
        this.preferredName = preferredName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProgramPreference() {
        return programPreference;
    }

    public String getMWT9to11OL() {
        return MWT9to11OL;
    }

    public void setMWT9to11OL(String MWT9to11OL) {
        this.MWT9to11OL = MWT9to11OL;
    }

    public String getMWT5to6OL() {
        return MWT5to6OL;
    }

    public void setMWT5to6OL(String MWT5to6OL) {
        this.MWT5to6OL = MWT5to6OL;
    }

    public String getTT9to11Surrey() {
        return TT9to11Surrey;
    }

    public void setTT9to11Surrey(String TT9to11Surrey) {
        this.TT9to11Surrey = TT9to11Surrey;
    }

    public String getTT4to6Sunset() {
        return TT4to6Sunset;
    }

    public void setTT4to6Sunset(String TT4to6Sunset) {
        this.TT4to6Sunset = TT4to6Sunset;
    }

    public String getTT4to6Mosaic() {
        return TT4to6Mosaic;
    }

    public void setTT4to6Mosaic(String TT4to6Mosaic) {
        this.TT4to6Mosaic = TT4to6Mosaic;
    }

    @Override
    public int equals(newMentor ) {
        return o1.phoneNumber.equals(o2.phoneNumber);
    }
}
