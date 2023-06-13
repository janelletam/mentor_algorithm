package com.company;

import java.util.*;
import java.io.*;

public class Mentor {
    String firstName;
    String lastName;

    String email;

    String phoneNumber;
    public Mentor(String firstName, String lastName, String email, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mentor mentor = (Mentor) o;
        return Objects.equals(firstName, mentor.firstName) && Objects.equals(lastName, mentor.lastName) && Objects.equals(email, mentor.email) && Objects.equals(phoneNumber, mentor.phoneNumber);
    }

    @Override
    public String toString() {
        return "Mentor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public static String printMentorOutputFieldOrder() {
        return "First Name" + ',' + "Preferred Name" + ',' + "Last Name" + ',' +
                "Age" + ',' + "Pronouns" + ',' + "Email" + ',' + "Phone Number" + ',' + "School" + ',' + "Years Attended" + ',' + "Major (if applicable)" + ',' +
                "Emergency Contact" + ',' +
                "Returning Mentor" + ',' + "Photo Video Consent" + ',' + "Additional Notes About Mentor" + ',' +
                "Reading Mentor" + ',' + "Math Mentor" + ',' + "Online Availability" + ',' + "In Person School Availability" + ',' +
                "In Person Community Centre Availability" + ',' + "Clayton Availability";
    }
}