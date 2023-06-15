package com.company;



import java.util.HashMap;

public class Mentor extends ParentMentor {
    // All fields below are from application form
    // NOTE: To count total number of availabilities, use getAllAvailableTimes().size()

    private String firstName;


    private String preferredName;

    private String lastName;

    private String dateOfBirth;
    private int age;

    private String phoneNumber;

    private String email;
    private String gmail;

    private String pronouns;

    private String languages;

    private String school;

    private String programCompleted;

    private String returnInTheFuture;

    private String emergencyContactFirstName;

    private String emergencyContactLastName;

    private String emergencyContactName;

    private String emergencyContactEmail;

    private String emergencyContactNumber;

    private String whyDoYouWantToVolunteer;

    private String experienceWithIndigenous;

    private String explainExperienceWithIndigenous;

    private String experienceWithNeurodivergent;
    private boolean isPhotoVideoPermissionTrue;

    private String moodleUsername;

    private String moodleEmail;
    private boolean isReturning;

    private String previousPosition = "nan";

    private boolean isReadingMentor;
    private boolean isMathMentor;


    private String OnlineMWTMorn;

    private String OnlineMWTAfternoon;

    private String IPSurreyMorn;

    private String IPSunsetAfternoon;

    private String IPMosaicAfternoon;

    private static String TIMESLOT_0 = "Online - Mondays & Wednesdays & Thursdays 9:40 - 11:10 am";
    private static String TIMESLOT_1 = "Online - Mondays & Wednesdays & Thursdays 5:10 - 6:40 pm";
    private static String TIMESLOT_2 = "In-Person at Surrey City Centre Library - Tuesdays & Thursdays 9:30 - 11:00 am";
    private static String TIMESLOT_3 = "In-Person at Sunset Community Centre - Tuesdays & Thursdays 4:50 - 6:15pm";
    private static String TIMESLOT_4 = "In-Person at Mosaic Family Centre - Tuesdays & Thursdays 4:30 - 6:00 pm";

    private String additionalCommentsOnAvailability;

    private String additionalNotesAboutMentor = "nan";
    private String pod;

    // Our own variable
    private HashMap<String, String> allAvailableTimes = new HashMap<>();

    public Mentor(String firstName, String preferredName, String lastName, String dateOfBirth,
                  String phoneNumber, String email, String gmail, String pronouns, String languages,
                  String school, String programCompleted, String returnInTheFuture,
                  String emergencyContactFirstName, String emergencyContactLastName,
                  String emergencyContactEmail, String emergencyContactNumber,
                  String whyDoYouWantToVolunteer, String experienceWithIndigenous,
                  String explainExperienceWithIndigenous, String experienceWithNeurodivergent,
                  String isPhotoVideoConsentTrue, String moodleUsername, String moodleEmail,
                  String isReturning, String previousPosition, String isReadingMentor,
                  String OnlineMWTMorn, String OnlineMWTAfternoon, String IPSurreyMorn,
                  String IPSunsetAfternoon, String IPMosaicAfternoon, String additionalCommentsOnAvailability) {
        super(firstName,lastName);
        this.firstName = firstName;
        this.preferredName = preferredName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;

        // TODO: Write function to convert date of birth to age
        // age = Period.between(dateOfBirth, LocalDate.now()).getYears();

        this.phoneNumber = phoneNumber;
        this.email = email;
        this.gmail = gmail;
        this.pronouns = pronouns;
        this.languages = languages;
        this.school = school;
        this.programCompleted = programCompleted;
        this.returnInTheFuture = returnInTheFuture;

        this.emergencyContactFirstName = emergencyContactFirstName;
        this.emergencyContactLastName = emergencyContactLastName;
        this.emergencyContactName = (new StringBuilder()).append(getEmergencyContactFirstName()).append(getEmergencyContactLastName()).toString();

        this.emergencyContactEmail = emergencyContactEmail;
        this.emergencyContactNumber = emergencyContactNumber;

        this.whyDoYouWantToVolunteer = whyDoYouWantToVolunteer;
        this.experienceWithIndigenous = experienceWithIndigenous;
        this.explainExperienceWithIndigenous = explainExperienceWithIndigenous;
        this.experienceWithNeurodivergent = experienceWithNeurodivergent;

        this.moodleUsername = moodleUsername;
        this.moodleEmail = moodleEmail;
        this.previousPosition = previousPosition;

        // In the application form, users can choose to be either a Reading Mentor or a Math Mentor or both
        // Prioritize Reading Mentors b/c there are more reading pods
        if (isReadingMentor.equalsIgnoreCase("Reading Mentor") || isReadingMentor.equalsIgnoreCase("Reading Mentor\nMath Mentor")) {
            this.isReadingMentor = true;
            this.isMathMentor = false;
        } else {
            this.isReadingMentor = false;
            this.isMathMentor = true;
        }

        this.OnlineMWTMorn = OnlineMWTMorn;
        this.OnlineMWTAfternoon = OnlineMWTAfternoon;
        this.IPSurreyMorn = IPSurreyMorn;
        this.IPSunsetAfternoon = IPSunsetAfternoon;
        this.IPMosaicAfternoon = IPMosaicAfternoon;

        this.additionalCommentsOnAvailability = additionalCommentsOnAvailability;

        allAvailableTimes.put(TIMESLOT_0, OnlineMWTMorn);
        allAvailableTimes.put(TIMESLOT_1, OnlineMWTAfternoon);
        allAvailableTimes.put(TIMESLOT_2, IPSurreyMorn);
        allAvailableTimes.put(TIMESLOT_3, IPSunsetAfternoon);
        allAvailableTimes.put(TIMESLOT_4, IPMosaicAfternoon);

        /*
        // NOTE: Need space after ";" b/c we later compare the characters of these fields with our list of Pods
        // Space interferes with this comparison
        allAvailableTimes = new ArrayList<>();
        String[] individualClaytonTimes = IPSunsetAfternoon.split("; ");
        String[] individualIPSchoolTimes = OnlineMWTAfternoon.split("; ");
        String[] individualIPCommunityCentreTimes = IPSurreyMorn.split("; ");
        String[] individualOnlineTimes = OnlineMWTMorn.split("; ");

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

         */
        if (isReturning.equalsIgnoreCase("yes")) this.isReturning = true;
        else this.isReturning = false;

        if (isPhotoVideoConsentTrue.equalsIgnoreCase("yes")) this.isPhotoVideoPermissionTrue = true;
        else this.isPhotoVideoPermissionTrue = false;

    }

    public Mentor() {
    }

    //Todo: Migrate logic to output class

    @Override
    public String toString() {
        return firstName.replace(",",";") + ',' + preferredName.replace(",",";") + ',' + lastName.replace(",",";") + ',' +
                age  + ',' + phoneNumber.replace(",",";")  + ',' + email.replace(",",";")  + ',' + gmail.replace(",",";")  + ',' + pronouns.replace(",",";")  + ',' + languages.replace(",",";")  + ',' +
                school.replace(",",";")  + ',' + programCompleted.replace(",",";")  + ',' + returnInTheFuture.replace(",",";")  + ',' +
                emergencyContactName.replace(",",";")  + "," + emergencyContactNumber.replace(",",";")  + "," + emergencyContactEmail.replace(",",";")  + ',' +
                whyDoYouWantToVolunteer.replace(",",";")  + "," + experienceWithIndigenous.replace(",",";")  + "," + explainExperienceWithIndigenous.replace(",",";")  + ',' + experienceWithNeurodivergent.replace(",",";")  + ',' +
                isPhotoVideoPermissionTrue + ',' + moodleUsername.replace(",",";")+","+ moodleEmail.replace(",",";") +"," +  toString(isReturning) + "," + previousPosition.replace(",",";") +',' + additionalNotesAboutMentor.replace(",",";")  + ',' +
                isReadingMentor + ',' + toString(isMathMentor) + ',' + OnlineMWTMorn.replace(",",";")  + ',' + OnlineMWTAfternoon.replace(",",";")  + ',' +
                IPSurreyMorn.replace(",",";")  + ',' + IPSunsetAfternoon.replace(",",";")  + ',' + IPMosaicAfternoon.replace(",",";") + "," + additionalCommentsOnAvailability.replace(",",";");
    }

    public String toString(boolean b){
        if(b){
            return "True";
        }
        else {
            return "False";
        }
    }

    public static String printMentorOutputFieldOrder() {
        return "First Name" + ',' + "Preferred Name" + ',' + "Last Name" + ',' +
                "Age" + ',' + "Phone Number" + ',' + "Email" + ',' + "Gmail" + ',' + "Pronouns" + ","+ "Languages" + ',' + "School" + ',' +
                "Did you complete this program" + ',' + "If you are not currently enrolled in school do you plan to return in the future? (Yes or No)" + ',' +
                "Emergency Contact (First Name Last Name)" + ',' + "Emergency Contact (Email Address)" + ',' + "Emergency Contact (Phone Number)" + ',' +
                "Why do you want to volunteer with us? (Related Experience)" + ',' + "Do you have any experience working with Indigenous populations/individuals?" + ',' + "Please explain your experience working with Indigenous populations/individuals." + ',' +
                "Do you have any experience working with neurodivergent populations/individuals (ie. ASD ADHD learning disabilities)?" + ',' +
                "Photo Video Consent" + ',' + "Moodle Account Username" + ',' + "Moodle Account Email" + ',' + "Returning Mentor" + ',' +
                "Previous Position" + ',' +"Additional Notes About Mentor" + ',' +
                "Reading Mentor" + ',' + "Math Mentor" + ',' + "Online - Mondays & Wednesdays & Thursdays 9:40 - 11:10 am" + ',' + "Online - Mondays & Wednesdays & Thursdays 5:10 - 6:40 pm" + ',' +
                "In-Person at Surrey City Centre Library - Tuesdays & Thursdays 9:30 - 11:00 am" + ',' + "In-Person at Sunset Community Centre - Tuesdays & Thursdays 4:50 - 6:15pm"
                +","+ "In-Person at Mosaic Family Centre - Tuesdays & Thursdays 4:30 - 6:00 pm" +","+ "Additional Comments on Availability";
    }


    public String printContactInfo() {
        return firstName + ',' + preferredName + ',' + lastName + ',' +
                email + ',' + gmail + ',' + phoneNumber + ',' + isReadingMentor + ','
                + isMathMentor + ',' + pod;
    }

    public static String printContactInfoFieldOrder() {
        return "First Name" + ',' + "Preferred Name" + ',' + "Last Name" + ',' +
                "Email" + ',' + "Gmail" + ',' + "Phone Number" + ',' + "Reading Mentor" + ','
                + "MathMentor" + ',' + "Pod";
    }

    public boolean isAgeValid() {
        return (age >= 15);
    }

    // Mentor is not available if they have not selected any times
    public boolean isAvailable() {
        return (!((IPSunsetAfternoon.equalsIgnoreCase("NaN") || IPSunsetAfternoon.equalsIgnoreCase("")) &&
                (OnlineMWTMorn.equalsIgnoreCase("NaN") || OnlineMWTMorn.equalsIgnoreCase("")) &&
                (OnlineMWTAfternoon.equalsIgnoreCase("NaN") || OnlineMWTAfternoon.equalsIgnoreCase("")) &&
                (IPSurreyMorn.equalsIgnoreCase("NaN") || IPSurreyMorn.equalsIgnoreCase(""))));
    }

    // Determine if the mentor has indicated availability for an in-person program.
    // In-person pods are harder to fill than online pods, so they should be placed in an
    // in-person program.
    public boolean hasInPerson() {
        for (int i = 0; i < OnlineMWTAfternoon.length(); i++) {
            char thisChar = OnlineMWTAfternoon.charAt(i);
            if (thisChar == ';') {
                return true;
            }
        }
        for (int i = 0; i < IPSurreyMorn.length(); i++) {
            char thisChar = IPSurreyMorn.charAt(i);
            if (thisChar == ';') {
                return true;
            }
        }
        return false;
    }

    public boolean hasOnline() {
        if (OnlineMWTMorn.length() != 0) return true;
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

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPronouns() {
        return pronouns;
    }

    public void setPronouns(String pronouns) {
        this.pronouns = pronouns;
    }

    public String getLanguages() {
        return languages;
    }

    public void setLanguages(String languages) {
        this.languages = languages;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getProgramCompleted() {
        return programCompleted;
    }

    public void setProgramCompleted(String programCompleted) {
        this.programCompleted = programCompleted;
    }

    public String getReturnInTheFuture() {
        return returnInTheFuture;
    }

    public void setReturnInTheFuture(String returnInTheFuture) {
        this.returnInTheFuture = returnInTheFuture;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmergencyContactFirstName() {
        return emergencyContactFirstName;
    }

    public void setEmergencyContactFirstName(String emergencyContactFirstName) {
        this.emergencyContactFirstName = emergencyContactFirstName;
    }

    public String getEmergencyContactLastName() {
        return emergencyContactLastName;
    }

    public void setEmergencyContactLastName(String emergencyContactLastName) {
        this.emergencyContactLastName = emergencyContactLastName;
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

    public String getOnlineMWTMorn() {
        return OnlineMWTMorn;
    }

    public void setOnlineMWTMorn(String onlineMWTMorn) {
        this.OnlineMWTMorn = onlineMWTMorn;
    }

    public String getOnlineMWTAfternoon() {
        return OnlineMWTAfternoon;
    }

    public void setOnlineMWTAfternoon(String onlineMWTAfternoon) {
        this.OnlineMWTAfternoon = onlineMWTAfternoon;
    }

    public String getIPSurreyMorn() {
        return IPSurreyMorn;
    }

    public void setIPSurreyMorn(String IPSurreyMorn) {
        this.IPSurreyMorn = IPSurreyMorn;
    }

    public String getIPSunsetAfternoon() {
        return IPSunsetAfternoon;
    }

    public void setIPSunsetAfternoon(String IPSunsetAfternoon) {
        this.IPSunsetAfternoon = IPSunsetAfternoon;
    }

    public String getIPMosaicAfternoon() {
        return IPMosaicAfternoon;
    }

    public void setIPMosaicAfternoon(String IPMosaicAfternoon) {
        this.IPMosaicAfternoon = IPMosaicAfternoon;
    }

    public boolean isReturning() {
        return isReturning;
    }

    public void setReturning(boolean isReturning) {
        this.isReturning = isReturning;
    }

    public boolean isPhotoVideoPermissionTrue() {
        return isPhotoVideoPermissionTrue;
    }

    public void setPhotoVideoPermissionTrue(boolean isPhotoVideoConsentTrue) {
        this.isPhotoVideoPermissionTrue = isPhotoVideoConsentTrue;
    }

    public String getWhyDoYouWantToVolunteer() {
        return whyDoYouWantToVolunteer;
    }

    public void setWhyDoYouWantToVolunteer(String whyDoYouWantToVolunteer) {
        this.whyDoYouWantToVolunteer = whyDoYouWantToVolunteer;
    }

    public String getExperienceWithIndigenous() {
        return experienceWithIndigenous;
    }

    public void setExperienceWithIndigenous(String experienceWithIndigenous) {
        this.experienceWithIndigenous = experienceWithIndigenous;
    }

    public String getExplainExperienceWithIndigenous() {
        return explainExperienceWithIndigenous;
    }

    public void setExplainExperienceWithIndigenous(String explainExperienceWithIndigenous) {
        this.explainExperienceWithIndigenous = explainExperienceWithIndigenous;
    }

    public String getExperienceWithNeurodivergent() {
        return experienceWithNeurodivergent;
    }

    public void setExperienceWithNeurodivergent(String experienceWithNeurodivergent) {
        this.experienceWithNeurodivergent = experienceWithNeurodivergent;
    }

    public String getMoodleUsername() {
        return moodleUsername;
    }

    public void setMoodleUsername(String moodleUsername) {
        this.moodleUsername = moodleUsername;
    }

    public String getMoodleEmail() {
        return moodleEmail;
    }

    public void setMoodleEmail(String moodleEmail) {
        this.moodleEmail = moodleEmail;
    }

    public String getPreviousPosition() {
        return previousPosition;
    }

    public void setPreviousPosition(String previousPosition) {
        this.previousPosition = previousPosition;
    }

    public void setAllAvailableTimes(HashMap<String, String> allAvailableTimes) {
        this.allAvailableTimes = allAvailableTimes;
    }

    public HashMap<String, String> getAllAvailableTimes() {
        return allAvailableTimes;
    }

    public String getAdditionalNotesAboutMentor() {
        return additionalNotesAboutMentor;
    }

    public void setAdditionalNotesAboutMentor(String additionalNotesAboutMentor) {
        this.additionalNotesAboutMentor = additionalNotesAboutMentor;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }
}