package com.company;

import java.util.Objects;

public class ParentMentor {
    private String firstName;
    private String lastName;

    public ParentMentor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public ParentMentor() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParentMentor mentor = (ParentMentor) o;
        return Objects.equals(firstName, mentor.firstName) && Objects.equals(lastName, mentor.lastName);
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
}
