package com.company;

public class Pod {
    private String time;
    private String podName;

    private boolean isReading;
    private boolean isMath;
    private boolean isIP;
    private boolean isOnline;
    private boolean isClayton;

    int numOfMentors;
    public Pod(String podName, String time, boolean isReading, boolean isMath,
               boolean isIP, boolean isOnline, boolean isClayton) {
        this.podName = podName;
        this.time = time;
        this.isReading = isReading;
        this.isMath = isMath;
        this.isIP = isIP;
        this.isOnline = isOnline;
        this.isClayton = isClayton;

        numOfMentors = 0;
    }

    public Pod(String podName){
        this.podName = podName;
    }

    @Override
    public String toString() {
        return "Pod: " + podName + " | " + time;
    }

    /* ###################################################################
        Getters and setters
        ################################################################### */
    public String getPodName() {
        return podName;
    }

    public void setPodName(String podName) {
        this.podName = podName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isReading() {
        return isReading;
    }

    public void setReading(boolean reading) {
        isReading = reading;
    }

    public boolean isMath() {
        return isMath;
    }

    public void setMath(boolean math) {
        isMath = math;
    }

    public boolean isIP() {
        return isIP;
    }

    public void setIP(boolean IP) {
        isIP = IP;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    public boolean isClayton() {
        return isClayton;
    }

    public void setClayton(boolean clayton) {
        isClayton = clayton;
    }
}
