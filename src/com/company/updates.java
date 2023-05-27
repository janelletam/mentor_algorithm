package com.company;

public class updates {
    int id;
    /*
    id 1 for delete
    id 2 for change pod
     */

    Mentor currM;
    Pod changedPod;

    updates(String id, String firstN, String lastN, String email, String pod){
        this.id = 2;
        currM = new Mentor(firstN,lastN,email,"null");
        changedPod = new Pod(pod);
    }

    updates(String id, String firstN, String lastN, String email){
        this.id = 1;
        currM = new Mentor(firstN, lastN, email, "null");
    }

}
