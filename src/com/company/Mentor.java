package com.company;

import java.util.ArrayList;

public class Mentor {
    // All fields below are from application form
    // NOTE: To count total number of availabilities, use getAllAvailableTimes().size()

    private String firstName;
    private String preferredName;
    private String lastName;

    private int age;
    private String phoneNumber;
    private String email;
    private String pronouns;
    private String school;
    private String yearsAttended;
    private String major;

    //Todo: Join these fields together
    private String emergencyContactName;
    private String emergencyContactEmail;
    private String emergencyContactNumber;

    private boolean isReadingMentor;
    private boolean isMathMentor;

    private String availabilityOnline;
    private String availabilityInPerson;
    private String availabilityClayton;

    private boolean isReturning;
    private boolean isPhotoVideoConsentTrue;

    // Our own variable
    private ArrayList<String> allAvailableTimes;

    public Mentor(String firstName, String preferredName, String lastName, String age,
                  String phoneNumber, String email, String pronouns, String school,
                  String yearsAttended, String major, String emergencyContactName,
                  String emergencyContactEmail, String emergencyContactNumber,
                  String isReadingMentor, String availabilityOnline,
                  String availabilityInPerson, String availabilityClayton,
                  String isReturning, String isPhotoVideoConsentTrue) {
        this.firstName = firstName;
        this.preferredName = preferredName;
        this.lastName = lastName;
        this.age = Integer.parseInt(age);
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.pronouns = pronouns;
        this.school = school;
        this.yearsAttended = yearsAttended;
        this.major = major;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactEmail = emergencyContactEmail;
        this.emergencyContactNumber = emergencyContactNumber;

        // In the application form, users can choose to be either a "Reading Mentor" or a "Math Mentor"
        if (isReadingMentor.equalsIgnoreCase("Reading Mentor")) {
            this.isReadingMentor = true;
            this.isMathMentor = false;
        } else {
            this.isReadingMentor = false;
            this.isMathMentor = true;
        }

        this.availabilityOnline = availabilityOnline;
        this.availabilityInPerson = availabilityInPerson;
        this.availabilityClayton = availabilityClayton;

        allAvailableTimes = new ArrayList<>();
        String[] individualOnlineTimes = availabilityOnline.split(";");
        String[] individualIPTimes = availabilityInPerson.split(";");
        String[] individualClaytonTimes = availabilityClayton.split(";");

        // Add all online availabilities from individualOnlineTimes to allAvailableTimes
        for (int i = 0; i < individualOnlineTimes.length; i++) {
            if (individualOnlineTimes[i] != null && !(individualOnlineTimes[i].equalsIgnoreCase("Nan"))
                    && !(individualOnlineTimes[i].equalsIgnoreCase(""))) {
                allAvailableTimes.add(individualOnlineTimes[i]);
            }
        }

        // Add all in-person availabilities from individualIPTimes to allAvailableTimes
        for (int i = 0; i < individualIPTimes.length; i++) {
            if (individualIPTimes[i] != null && !(individualIPTimes[i].equalsIgnoreCase("Nan"))
                    && !(individualIPTimes[i].equalsIgnoreCase(""))) {
                allAvailableTimes.add(individualIPTimes[i]);
            }
        }

        // Add all Clayton availabilities from individualClaytonTimes to allAvailableTimes
        for (int i = 0; i < individualClaytonTimes.length; i++) {
            if (individualClaytonTimes[i] != null && !(individualClaytonTimes[i].equalsIgnoreCase("Nan"))
                    && !(individualClaytonTimes[i].equalsIgnoreCase(""))) {
                allAvailableTimes.add(individualClaytonTimes[i]);
            }
        }

        if (isReturning.equalsIgnoreCase("yes")) this.isReturning = true;
        else this.isReturning = false;

        if (isPhotoVideoConsentTrue.equalsIgnoreCase("yes")) this.isPhotoVideoConsentTrue = true;
        else this.isPhotoVideoConsentTrue = false;

    }

    //Todo: Migrate logic to output class
    @Override
    public String toString() {
        return "Student [name=" + firstName + " " + lastName + "  " + "Preferred Name=" +
                preferredName + "Age=" + age + "  " + "Phone Number=" + phoneNumber + "  " +
                "Email=" + email + "  " + "Pronouns=" + pronouns + "  " + "Position=" +
                position() + "]";
    }

    public boolean isValid() {
        return (age >= 15 && isAvailable());
    }

    // Mentor is not available if they have not selected any times
    public boolean isAvailable() {
        return (!(availabilityClayton.equalsIgnoreCase("NaN") &&
                availabilityOnline.equalsIgnoreCase("NaN") &&
                availabilityInPerson.equalsIgnoreCase("NaN")));
    }

    // Determine if the mentor has indicated availability for an in-person program.
    // In-person pods are harder to fill than online pods, so they should be placed in an
    // in-person program.
    public boolean hasInPerson() {
        for (int i = 0; i < availabilityInPerson.length(); i++) {
            char thisChar = availabilityInPerson.charAt(i);
            if (thisChar == ';') {
                return true;
            }
        }
        return false;
    }

    public String position() {
        if (isReadingMentor) {
            return "Reading Mentor";
        }
        return "Math Mentor";
    }

    /* ###################################################################
    Getters and setters
    ################################################################### */

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPronouns() {
        return pronouns;
    }

    public void setPronouns(String pronouns) {
        this.pronouns = pronouns;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getYearsAttended() {
        return yearsAttended;
    }

    public void setYearsAttended(String yearsAttended) {
        this.yearsAttended = yearsAttended;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getEmergencyContactName() {
        return emergencyContactName;
    }

    public void setEmergencyContactName(String emergencyContactName) {
        this.emergencyContactName = emergencyContactName;
    }

    public String getEmergencyContactEmail() {
        return emergencyContactEmail;
    }

    public void setEmergencyContactEmail(String emergencyContactEmail) {
        this.emergencyContactEmail = emergencyContactEmail;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public boolean getIsReadingMentor() {
        return isReadingMentor;
    }

    public void setIsReadingMentor(boolean isReadingMentor) {
        this.isReadingMentor = isReadingMentor;
    }

    public boolean getIsMathMentor() {
        return isMathMentor;
    }

    public void setIsMathMentor(boolean isMathMentor) {
        this.isMathMentor = isMathMentor;
    }

    public String getAvailabilityOnline() {
        return availabilityOnline;
    }

    public void setAvailabilityOnline(String availabilityOnline) {
        this.availabilityOnline = availabilityOnline;
    }

    public String getAvailabilityInPerson() {
        return availabilityInPerson;
    }

    public void setAvailabilityInPerson(String availabilityInPerson) {
        this.availabilityInPerson = availabilityInPerson;
    }

    public String getAvailabilityClayton() {
        return availabilityClayton;
    }

    public void setAvailabilityClayton(String availabilityClayton) {
        this.availabilityClayton = availabilityClayton;
    }

    public boolean getIsReturning() {
        return isReturning;
    }

    public void setIsReturning(boolean isReturning) {
        this.isReturning = isReturning;
    }

    public boolean getIsPhotoVideoConsentTrue() {
        return isPhotoVideoConsentTrue;
    }

    public void setIsPhotoVideoConsentTrue(boolean isPhotoVideoConsentTrue) {
        this.isPhotoVideoConsentTrue = isPhotoVideoConsentTrue;
    }

    public void setAllAvailableTimes(ArrayList<String> allAvailableTimes) {
        this.allAvailableTimes = allAvailableTimes;
    }

    public ArrayList<String> getAllAvailableTimes() {
        return allAvailableTimes;
    }
}