package ohtu;

import java.util.ArrayList;
import java.lang.StringBuilder;

public class Submission {
    private int week;
    private int hours;
    private ArrayList exercises;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    @Override
    public String toString() {
        return "viikko "+week+ ": tehtyjä tehtäviä yhteensä: "+exercises.size()
                + ", aikaa kului " +hours+ " tuntia, tehdyt tehtävät: " +
                exercises.toString();
    }
    
    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }
    
    public void setExercises(ArrayList list) {
        this.exercises = list;
    }

    public ArrayList getExercises() {
        return exercises;
    }
    
    public int numExercises() {
        return exercises.size();
    }
    
}