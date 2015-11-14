package com.example.researchbeast.myspectrum.models;

public class NewEventModel {
    public String date;

    public String time;

    public String notes;

    public String rating;

    public NewEventModel(String date, String time, String rating, String notes) {
        this.date = date;
        this.time = time;
        this.notes = notes;
        this.rating = rating;
    }
}
