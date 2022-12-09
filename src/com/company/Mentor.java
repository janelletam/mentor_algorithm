package com.company;

public class Mentor {
    private String firstName;
    private String preferredName;
    private String lastName;

    private String age;
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

    //Todo: Convert to boolean
    private String isReadingMentor;

    private String availabilityOnline;
    private String availabilityInPerson;
    private String availabilityClayton;

    //Todo: Convert to boolean
    private String isReturning;
    private String isPhotoVideoConsentTrue;

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
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.pronouns = pronouns;
        this.school = school;
        this.yearsAttended = yearsAttended;
        this.major = major;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactEmail = emergencyContactEmail;
        this.emergencyContactNumber = emergencyContactNumber;
        this.isReadingMentor = isReadingMentor;
        this.availabilityOnline = availabilityOnline;
        this.availabilityInPerson = availabilityInPerson;
        this.availabilityClayton = availabilityClayton;
        this.isReturning = isReturning;
        this.isPhotoVideoConsentTrue = isPhotoVideoConsentTrue;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
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

    public String getIsReadingMentor() {
        return isReadingMentor;
    }

    public void setIsReadingMentor(String isReadingMentor) {
        this.isReadingMentor = isReadingMentor;
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

    public String getIsReturning() {
        return isReturning;
    }

    public void setIsReturning(String isReturning) {
        this.isReturning = isReturning;
    }

    public String getIsPhotoVideoConsentTrue() {
        return isPhotoVideoConsentTrue;
    }

    public void setIsPhotoVideoConsentTrue(String isPhotoVideoConsentTrue) {
        this.isPhotoVideoConsentTrue = isPhotoVideoConsentTrue;
    }

    /*
    @Override
    public String toString() {
        return "Book [name=" + name + ", price=" + price + ", author=" + author
                + "]";
    }
     */
}
