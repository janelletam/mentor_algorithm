package com.company;

public class LastTermMentor {
    private String firstName;
    private String lastName;
    private String podName;

    public LastTermMentor(String firstName, String lastName, String podName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.podName = podName;
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

    public String getPodName() {
        return podName;
    }

    public void setPodName(String podName) {
        this.podName = podName;
    }

    @Override
    public String toString() {
        return "ReturningMentor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", podName='" + podName + '\'' +
                '}';
    }
}
