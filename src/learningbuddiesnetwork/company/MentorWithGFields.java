package learningbuddiesnetwork.company;

import com.sun.source.tree.ReturnTree;

import java.util.ArrayList;

public class MentorWithGFields {
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

    private String availabilityOnline;
    private String availabilityInPerson;
    private String availabilityClayton;

    private boolean isReturning;
    private boolean isPhotoVideoConsentTrue;

    public ArrayList<String> allAvaTimes;

    public MentorWithGFields(Mentor m) {
        this.firstName = m.getFirstName();
        this.preferredName = m.getPreferredName();
        this.lastName = m.getLastName();
        this.age = Integer.parseInt(m.getAge());
        this.phoneNumber = m.getPhoneNumber();
        this.email = m.getEmail();
        this.pronouns = m.getPronouns();
        this.school = m.getSchool();
        this.yearsAttended = m.getYearsAttended();
        this.major = m.getMajor();
        this.emergencyContactName = m.getEmergencyContactName();
        this.emergencyContactEmail = m.getEmergencyContactEmail();
        this.emergencyContactNumber = m.getEmergencyContactNumber();

        if (m.getIsReadingMentor().equalsIgnoreCase("yes")) {
            this.isReadingMentor = true;
        } else {
            this.isReadingMentor = false;
        }

        this.availabilityOnline = m.getAvailabilityOnline();
        this.availabilityInPerson = m.getAvailabilityInPerson();
        this.availabilityClayton = m.getAvailabilityClayton();

        if (m.getIsReturning().equalsIgnoreCase("yes")) {
            this.isReturning = true;
        } else {
            this.isReadingMentor = false;
        }

        if (m.getIsPhotoVideoConsentTrue().equalsIgnoreCase("yes")) {
            this.isReturning = true;
        } else {
            this.isReadingMentor = false;
        }

        allAvaTimes = new ArrayList<>();
        String[] add = availabilityOnline.split(";");
        String[] add1 = availabilityInPerson.split(";");
        String[] add2 = availabilityClayton.split(";");


        //add all in add1 to allAvaTimes
        for(int i = 0; i < add1.length; i++){
            if (add1[i] != null && !(add1[i].equalsIgnoreCase("Nan")) && !(add1[i].equalsIgnoreCase("")) ){
                allAvaTimes.add(add1[i]);
            }
        }

        //add all in add2 to allAvaTimes
        for(int i = 0; i < add2.length; i++){
            if (add2[i] != null && !(add2[i].equalsIgnoreCase("Nan")) && !(add2[i].equalsIgnoreCase(""))){
                allAvaTimes.add(add2[i]);
            }
        }

        //add all in add to allAvaTimes
        for(int i = 0; i < add.length; i++){
            if (add[i] != null && !(add[i].equalsIgnoreCase("Nan")) && !(add[i].equalsIgnoreCase(""))){
                allAvaTimes.add(add[i]);
            }
        }


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

    public void setReadingMentor(boolean readingMentor) {
        isReadingMentor = readingMentor;
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

    public void setReturning(boolean returning) {
        isReturning = returning;
    }

    public boolean isPhotoVideoConsentTrue() {
        return isPhotoVideoConsentTrue;
    }

    public void setPhotoVideoConsentTrue(boolean photoVideoConsentTrue) {
        isPhotoVideoConsentTrue = photoVideoConsentTrue;
    }


     @Override
     public String toString() {
         return "Student [name=" + firstName + " " + lastName +"  "+  "Preferred Name=" + preferredName + "Age=" +age + "  "+ "Phone Number=" + phoneNumber +  "  "+ "Email=" +email +"  "+ "Pronouns=" +  pronouns + "  " + "Position=" + position()
                 + "]";
     }

    public boolean isValid() {
        return (age >= 15 && isAvailable());
    }

    public boolean isAvailable() {
        return (!(availabilityClayton.equalsIgnoreCase("NaN") && availabilityOnline.equalsIgnoreCase("NaN") && availabilityInPerson.equalsIgnoreCase("NaN")));
    }

    public int getNumOfAvailabilities() {
        int total = 0;
        for (int i = 0; i < availabilityOnline.length(); i++) {
            char thisChar = availabilityOnline.charAt(i);
            if (thisChar == ';') {
                total++;
            }
        }

        for (int i = 0; i < availabilityClayton.length(); i++) {
            char thisChar = availabilityClayton.charAt(i);
            if (thisChar == ';') {
                total++;
            }
        }

        for (int i = 0; i < availabilityInPerson.length(); i++) {
            char thisChar = availabilityInPerson.charAt(i);
            if (thisChar == ';') {
                total++;
            }
        }

        return total;

    }

    public boolean hasInPerson() {
        for (int i = 0; i < availabilityInPerson.length(); i++) {
            char thisChar = availabilityInPerson.charAt(i);
            if (thisChar == ';') {
                return true;
            }
        }
        return false;
    }

    public String position(){
        if(isReadingMentor){
            return "Reading Mentor";
        }
        return "Math Mentor";
    }



}