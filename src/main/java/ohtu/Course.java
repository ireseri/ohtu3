/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.List;

/**
 *
 * @author paivi
 */
public class Course {
    
    private String name;
    private String term;
    private List exercises;
    
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void setTerm(String term) {
        this.term = term;
    }

    public String getTerm() {
        return term;
    }
    
    public void setExercises(List exercises) {
        this.exercises = exercises;
    }

    public List getExercises() {
        return exercises;
    }
    
    public double getMax(int i){   
        return (Double)exercises.get(i);
    }
            
    
}
