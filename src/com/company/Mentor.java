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
    private String availabilityInPersonSchool;
    private String availabilityInPersonCommunityCentre;
    private String availabilityClayton;

    private boolean isReturning;
    private boolean isPhotoVideoConsentTrue;

    private String additionalNotesAboutMentor;

    // Our own variable
    private ArrayList<String> allAvailableTimes;

    public Mentor(String firstName, String preferredName, String lastName, String age,
                  String phoneNumber, String email, String pronouns, String school,
                  String yearsAttended, String major, String emergencyContactName,
                  String emergencyContactEmail, String emergencyContactNumber,
                  String isReadingMentor, String availabilityOnline,
                  String availabilityInPersonSchool, String availabilityInPersonCommunityCentre,
                  String availabilityClayton, String isReturning, String isPhotoVideoConsentTrue) {
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
        this.availabilityInPersonSchool = availabilityInPersonSchool;
        this.availabilityInPersonCommunityCentre = availabilityInPersonCommunityCentre;
        this.availabilityClayton = availabilityClayton;

        // NOTE: Need space after ";" b/c we later compare the characters of these fields with our list of Pods
        // Space interferes with this comparison
        allAvailableTimes = new ArrayList<>();
        String[] individualClaytonTimes = availabilityClayton.split("; ");
        String[] individualIPSchoolTimes = availabilityInPersonSchool.split("; ");
        String[] individualIPCommunityCentreTimes = availabilityInPersonCommunityCentre.split("; ");
        String[] individualOnlineTimes = availabilityOnline.split("; ");

        // Prioritize Clayton > IP > Earlier Online Times > Later Online Times
        // Add all Clayton availabilities from individualClaytonTimes to allAvailableTimes
        for (String individualClaytonTime : individualClaytonTimes) {
            if (individualClaytonTime != null && !(individualClaytonTime.equalsIgnoreCase("Nan"))
                    && !(individualClaytonTime.equalsIgnoreCase(""))) {
                allAvailableTimes.add(individualClaytonTime);
            }
        }

        // Add all in-person availabilities from individualIPSchoolTimes to allAvailableTimes
        for (String individualIPSchoolTime : individualIPSchoolTimes) {
            if (individualIPSchoolTime != null && !(individualIPSchoolTime.equalsIgnoreCase("Nan"))
                    && !(individualIPSchoolTime.equalsIgnoreCase(""))) {
                allAvailableTimes.add(individualIPSchoolTime);
            }
        }

        // Add all in-person availabilities from individualIPCommunityCentreTimes to allAvailableTimes
        for (String individualIPCommunityCentreTime : individualIPCommunityCentreTimes) {
            if (individualIPCommunityCentreTime != null && !(individualIPCommunityCentreTime.equalsIgnoreCase("Nan"))
                    && !(individualIPCommunityCentreTime.equalsIgnoreCase(""))) {
                allAvailableTimes.add(individualIPCommunityCentreTime);
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

    }

    //Todo: Migrate logic to output class


    @Override
    public String toString() {
        return firstName + ',' + preferredName + ',' + lastName + ',' +
                age + ',' + pronouns + ',' + email + ',' + phoneNumber + ',' + school + ',' + yearsAttended + ',' + major + ',' +
                emergencyContactName + ";" + emergencyContactNumber + ";" + emergencyContactEmail + ',' +
                isReturning + ',' + isPhotoVideoConsentTrue + ',' + additionalNotesAboutMentor + ',' +
                isReadingMentor + ',' + isMathMentor + ',' + availabilityOnline + ',' + availabilityInPersonSchool + ',' +
                availabilityInPersonCommunityCentre + ',' + availabilityClayton;
    }

    public static String printMentorOutputFieldOrder() {
        return "First Name" + ',' + "Preferred Name" + ',' + "Last Name" + ',' +
                "Age" + ',' + "Pronouns" + ',' + "Email" + ',' + "Phone Number" + ',' + "School" + ',' + "Years Attended" + ',' + "Major (if applicable)" + ',' +
                "Emergency Contact" + ',' +
                "Returning Mentor" + ',' + "Photo Video Consent" + ',' + "Additional Notes About Mentor" + ',' +
                "Reading Mentor" + ',' + "Math Mentor" + ',' + "Online Availability" + ',' + "In Person School Availability" + ',' +
                "In Person Community Centre Availability" + ',' + "Clayton Availability";
    }

    public boolean isValid() {
        return (age >= 15);
    }

    // Mentor is not available if they have not selected any times
    public boolean isAvailable() {
        return (!((availabilityClayton.equalsIgnoreCase("NaN") || availabilityClayton.equalsIgnoreCase("")) &&
                (availabilityOnline.equalsIgnoreCase("NaN") || availabilityOnline.equalsIgnoreCase("")) &&
                (availabilityInPersonSchool.equalsIgnoreCase("NaN") || availabilityInPersonSchool.equalsIgnoreCase("")) &&
                (availabilityInPersonCommunityCentre.equalsIgnoreCase("NaN") || availabilityInPersonCommunityCentre.equalsIgnoreCase(""))));
    }

    // Determine if the mentor has indicated availability for an in-person program.
    // In-person pods are harder to fill than online pods, so they should be placed in an
    // in-person program.
    public boolean hasInPerson() {
        for (int i = 0; i < availabilityInPersonSchool.length(); i++) {
            char thisChar = availabilityInPersonSchool.charAt(i);
            if (thisChar == ';') {
                return true;
            }
        }
        for (int i = 0; i < availabilityInPersonCommunityCentre.length(); i++) {
            char thisChar = availabilityInPersonCommunityCentre.charAt(i);
            if (thisChar == ';') {
                return true;
            }
        }
        return false;
    }

    public boolean hasOnline() {
        if (availabilityOnline.length() != 0) return true;
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

    public String getAvailabilityInPersonSchool() {
        return availabilityInPersonSchool;
    }

    public void setAvailabilityInPersonSchool(String availabilityInPersonSchool) {
        this.availabilityInPersonSchool = availabilityInPersonSchool;
    }

    public String getAvailabilityInPersonCommunityCentre() {
        return availabilityInPersonCommunityCentre;
    }

    public void setAvailabilityInPersonCommunityCentre(String availabilityInPersonCommunityCentre) {
        this.availabilityInPersonCommunityCentre = availabilityInPersonCommunityCentre;
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

    public String getAdditionalNotesAboutMentor() {
        return additionalNotesAboutMentor;
    }

    public void setAdditionalNotesAboutMentor(String additionalNotesAboutMentor) {
        this.additionalNotesAboutMentor = additionalNotesAboutMentor;
    }

}