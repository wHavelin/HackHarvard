package com.example.researchbeast.myspectrum.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewEventModel implements Comparable<NewEventModel> {
    public String date;
    public String time;
    public String duration;
    public String notes;

    public String rating;

    public NewEventModel(String date, String time, String duration, String rating, String notes) {
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.notes = notes;
        this.rating = rating;
    }

    public int compareTo(NewEventModel otherModel){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
            Date thisDate = sdf.parse(date.replaceAll("\\s+",""));
            Date otherDate = sdf.parse(otherModel.date.replaceAll("\\s+",""));
            return thisDate.compareTo(otherDate);
        }
        catch (Exception e){
            rating = e.getMessage();
            return 0;
        }
    }
}
