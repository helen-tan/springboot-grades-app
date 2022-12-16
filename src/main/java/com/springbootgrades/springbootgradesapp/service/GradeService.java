package com.springbootgrades.springbootgradesapp.service;

import java.util.List;

import com.springbootgrades.springbootgradesapp.Constants;
import com.springbootgrades.springbootgradesapp.Grade;
import com.springbootgrades.springbootgradesapp.repository.GradeRepository;

public class GradeService {
    // Service to get data from Repository - Create an instance of the Repository
    GradeRepository gradeRepository = new GradeRepository();

     // GET/READ grade
     public Grade getGrade(int index) {
        return gradeRepository.getGrade(index);
     }

     // CREATE grade
     public void addGrade(Grade grade) {
        gradeRepository.addGrade(grade);
     }

     // UPDATE grade
     public void updateGrade(Grade grade, int index) {
        gradeRepository.updateGrade(grade, index);
     }

    // GET/READ grades
    public List<Grade> getGrades() {
        return gradeRepository.getGrades();
    }  
    
    // GET grade index
    public int getGradeIndex(String id) {
        List<Grade> studentGrades = getGrades();

        for (int i = 0; i < studentGrades.size(); i++) {
            if (studentGrades.get(i).getId().equals(id)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }

    // Get grade by id
    public Grade getGradeById(String id) {
        Grade grade;
        int index = getGradeIndex(id);

        if (index == Constants.NOT_FOUND) {
            grade = new Grade();
        } else {
            // grade = studentGrades.get(index);
            grade = getGrade(index);
        }
        return grade;
    }

    // Submit grade
    public void submitGrade(Grade grade) {
        int index = getGradeIndex(grade.getId());
        // Add new grade only if it doesn't aldy exist
        if(index == Constants.NOT_FOUND) {
            addGrade(grade); 
        } else {
            //studentGrades.set(index, grade);
            updateGrade(grade, index); 
        }
    }
}
