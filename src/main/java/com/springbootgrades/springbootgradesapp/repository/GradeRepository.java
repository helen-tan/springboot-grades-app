package com.springbootgrades.springbootgradesapp.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbootgrades.springbootgradesapp.Grade;

// Repository class is the Data Access Layer
// Only Repo class can perform CRUD operations on the Data store!

// @Component/@Repository converts the GradeRepository class into a component and creates an object out of it (a bean)
// Stores the bean in the Spring Container
@Repository
public class GradeRepository {
     // List of grade objects (only populated when the controller is first initialized)
     private List<Grade> studentGrades = new ArrayList<>();
    
     // GET/READ grade
     public Grade getGrade(int index) {
        return studentGrades.get(index);
     }

     // CREATE grade
     public void addGrade(Grade grade) {
        studentGrades.add(grade);
     }

     // UPDATE grade
     public void updateGrade(Grade grade, int index) {
        studentGrades.set(index, grade);
     }

    // GET/READ grades
    public List<Grade> getGrades() {
        return studentGrades;
    }  
}
