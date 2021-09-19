package com.example.crimetracker;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UserReport {

    private int id;
    private String typeOfCrime;
    private String description;
    private int degreeOfDanger;

    //constructor
    public UserReport(int id, String typeOfCrime, String description, int degreeOfDanger) {
        this.id = id;
        this.typeOfCrime = typeOfCrime;
        this.description = description;
        this.degreeOfDanger = degreeOfDanger;
    }

    @Override
    public String toString() {
        return "UserReportDatabase{" +
                "id=" + id +
                ", typeOfCrime='" + typeOfCrime + '\'' +
                ", description='" + description + '\'' +
                ", degreeOfDanger=" + degreeOfDanger +
                '}';
    }

    //getters and setters

    public int getDegreeOfDanger() {
        return degreeOfDanger;
    }

    public void setDegreeOfDanger(int degreeOfDanger) {
        this.degreeOfDanger = degreeOfDanger;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeOfCrime() {
        return typeOfCrime;
    }

    public void setTypeOfCrime(String typeOfCrime) {
        this.typeOfCrime = typeOfCrime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}