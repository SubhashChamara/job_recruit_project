package com.recruiter.recruite.model;

import com.recruiter.recruite.constants.Gender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class NicDetails {
    private LocalDate dob;
    private int age;
    private Gender gender;

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "NicDetails{" +
                "dob=" + dob.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}