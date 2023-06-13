package com.company;

public class LastTermMentor extends Mentor{
    String lastTermPod;
    public LastTermMentor(String firstName, String lastName, String email, String phoneNum, String podName) {
        super(firstName, lastName, email,phoneNum);
        lastTermPod = podName;
    }

    public String getPodName() {
        return lastTermPod;
    }

    public void setPodName(String podName) {
        this.lastTermPod = podName;
    }


}
