package com.company;

import java.util.ArrayList;

public class Mentor {
    // All fields below are from application form
    // NOTE: To count total number of availabilities, use getAllAvailableTimes().size()

    private String firstName;
    private String preferredName;
    private String lastName;

    private String age;
    private String pronouns;
    private String preferredEmail;
    private String gmail;
    private String phoneNumber;

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
    private String availabilityComments;

    private boolean isReturning;
    private boolean isPhotoVideoConsentTrue;

    private String tellUsAboutYourself;
    private String previousPodType;
    private String indigenousExperience;
    private String neuroDivergentExperience;
    private String otherLanguages;
    private String howDidYouHearAboutUs;

    // Our own variable
    private ArrayList<String> allAvailableTimes;

    public Mentor(String firstName, String preferredName, String lastName, String age,
                  String pronouns, String preferredEmail, String gmail, String phoneNumber,
                  String school, String yearsAttended, String major,
                  String emergencyContactName, String emergencyContactEmail, String emergencyContactNumber,
                  String isReadingMentor, String availabilityOnline,
                  String availabilityInPerson, String availabilityClayton, String availabilityComments,
                  String isReturning, String isPhotoVideoConsentTrue,
                  String tellUsAboutYourself, String previousPodType, String indigenousExperience,
                  String neuroDivergentExperience, String otherLanguages, String howDidYouHearAboutUs) {
        this.firstName = firstName;
        this.preferredName = preferredName;
        this.lastName = lastName;
        this.age = age;
        this.pronouns = pronouns;

        this.preferredEmail = preferredEmail;
        this.gmail = gmail;
        this.phoneNumber = phoneNumber;

        this.school = school;
        this.yearsAttended = yearsAttended;
        this.major = major;

        this.emergencyContactName = emergencyContactName;
        this.emergencyContactEmail = emergencyContactEmail;
        this.emergencyContactNumber = emergencyContactNumber;

        // In the application form, users can choose to be either a Reading Mentor or a Math Mentor or both
        // Prioritize Reading Mentors b/c there are more reading pods
        if (isReadingMentor.equalsIgnoreCase("Reading Mentor") || isReadingMentor.equalsIgnoreCase("Reading Mentor; Math Mentor")) {
            this.isReadingMentor = true;
            this.isMathMentor = false;
        } else {
            this.isReadingMentor = false;
            this.isMathMentor = true;
        }

        this.availabilityOnline = availabilityOnline;
        this.availabilityInPerson = availabilityInPerson;
        this.availabilityClayton = availabilityClayton;
        this.availabilityComments = availabilityComments;

        // NOTE: Need space after ";" b/c we later compare the characters of these fields with our list of Pods
        // Space interferes with this comparison
        allAvailableTimes = new ArrayList<>();
        String[] individualClaytonTimes = availabilityClayton.split("; ");
        String[] individualIPTimes = availabilityInPerson.split("; ");
        String[] individualOnlineTimes = availabilityOnline.split("; ");

        // Prioritize Clayton > IP > Earlier Online Times > Later Online Times
        // Add all Clayton availabilities from individualClaytonTimes to allAvailableTimes
        for (String individualClaytonTime : individualClaytonTimes) {
            if (individualClaytonTime != null && !(individualClaytonTime.equalsIgnoreCase("Nan"))
                    && !(individualClaytonTime.equalsIgnoreCase(""))) {
                allAvailableTimes.add(individualClaytonTime);
            }
        }

        // Add all in-person availabilities from individualIPTimes to allAvailableTimes
        for (String individualIPTime : individualIPTimes) {
            if (individualIPTime != null && !(individualIPTime.equalsIgnoreCase("Nan"))
                    && !(individualIPTime.equalsIgnoreCase(""))) {
                allAvailableTimes.add(individualIPTime);
            }
        }

        // Add all online availabilities from individualOnlineTimes to allAvailableTimes
        for (String individualOnlineTime : individualOnlineTimes) {
            if (individualOnlineTime != null && !(individualOnlineTime.equalsIgnoreCase("Nan"))
                    && !(individualOnlineTime.equalsIgnoreCase(""))) {
                allAvailableTimes.add(individualOnlineTime);
            }
        }

        if (isReturning.equalsIgnoreCase("yes")) this.isReturning = true;
        else this.isReturning = false;

        if (isPhotoVideoConsentTrue.equalsIgnoreCase("yes")) this.isPhotoVideoConsentTrue = true;
        else this.isPhotoVideoConsentTrue = false;

        this.tellUsAboutYourself = tellUsAboutYourself;
        this.previousPodType = previousPodType;
        this.indigenousExperience = indigenousExperience;
        this.neuroDivergentExperience = neuroDivergentExperience;
        this.otherLanguages = otherLanguages;
        this.howDidYouHearAboutUs = howDidYouHearAboutUs;
    }

    //Todo: Migrate logic to output class
    @Override
    public String toString() {
        return "Mentor [name=" + firstName + " " + lastName + "  " + "Preferred Name=" +
                preferredName + "Age=" + age + "  " + "Phone Number=" + phoneNumber + "  " +
                "Email=" + preferredEmail + "  " + "Pronouns=" + pronouns + "  " + "Position=" +
                position() + "]";
    }

    public boolean isValid() {
        return (Integer.parseInt(age) >= 15 && isAvailable());
    }

    // Mentor is not available if they have not selected any times
    public boolean isAvailable() {
        return (!((availabilityClayton.equalsIgnoreCase("NaN") || availabilityClayton.equalsIgnoreCase("")) &&
                (availabilityOnline.equalsIgnoreCase("NaN") || availabilityOnline.equalsIgnoreCase("")) &&
                (availabilityInPerson.equalsIgnoreCase("NaN") || availabilityInPerson.equalsIgnoreCase(""))));
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

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getAvailabilityComments() {
        return availabilityComments;
    }

    public void setAvailabilityComments(String availabilityComments) {
        this.availabilityComments = availabilityComments;
    }

    public String getTellUsAboutYourself() {
        return tellUsAboutYourself;
    }

    public void setTellUsAboutYourself(String tellUsAboutYourself) {
        this.tellUsAboutYourself = tellUsAboutYourself;
    }

    public String getPreviousPodType() {
        return previousPodType;
    }

    public void setPreviousPodType(String previousPodType) {
        this.previousPodType = previousPodType;
    }

    public String getIndigenousExperience() {
        return indigenousExperience;
    }

    public void setIndigenousExperience(String indigenousExperience) {
        this.indigenousExperience = indigenousExperience;
    }

    public String getNeuroDivergentExperience() {
        return neuroDivergentExperience;
    }

    public void setNeuroDivergentExperience(String neuroDivergentExperience) {
        this.neuroDivergentExperience = neuroDivergentExperience;
    }

    public String getOtherLanguages() {
        return otherLanguages;
    }

    public void setOtherLanguages(String otherLanguages) {
        this.otherLanguages = otherLanguages;
    }

    public String getHowDidYouHearAboutUs() {
        return howDidYouHearAboutUs;
    }

    public void setHowDidYouHearAboutUs(String howDidYouHearAboutUs) {
        this.howDidYouHearAboutUs = howDidYouHearAboutUs;
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

    public int getAge() {
        return Integer.parseInt(age);
    }

    public void setAge(int age) {
        this.age = (""+age);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPreferredEmail() {
        return preferredEmail;
    }

    public void setPreferredEmail(String preferredEmail) {
        this.preferredEmail = preferredEmail;
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

    public boolean isReadingMentor() {
        return isReadingMentor;
    }

    public void setReadingMentor(boolean isReadingMentor) {
        this.isReadingMentor = isReadingMentor;
    }

    public boolean isMathMentor() {
        return isMathMentor;
    }

    public void setMathMentor(boolean isMathMentor) {
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

    public boolean isReturning() {
        return isReturning;
    }

    public void setReturning(boolean isReturning) {
        this.isReturning = isReturning;
    }

    public boolean isPhotoVideoConsentTrue() {
        return isPhotoVideoConsentTrue;
    }

    public void setPhotoVideoConsentTrue(boolean isPhotoVideoConsentTrue) {
        this.isPhotoVideoConsentTrue = isPhotoVideoConsentTrue;
    }

    public void setAllAvailableTimes(ArrayList<String> allAvailableTimes) {
        this.allAvailableTimes = allAvailableTimes;
    }

    public ArrayList<String> getAllAvailableTimes() {
        return allAvailableTimes;
    }
}