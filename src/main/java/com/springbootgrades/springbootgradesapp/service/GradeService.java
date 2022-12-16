package com.springbootgrades.springbootgradesapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbootgrades.springbootgradesapp.Constants;
import com.springbootgrades.springbootgradesapp.Grade;
import com.springbootgrades.springbootgradesapp.repository.GradeRepository;

// @Component/@Service converts the GradeService class into a component and creates an object out of it (a bean)
// Stores the bean in the Spring Container
@Service 
public class GradeService {
    // Service to get data from Repository 
    // Dependency Injection: Autowire the bean into the gradeRepository variable from the Spring container (Do not create an instance of the Repository)
    @Autowired
    GradeRepository gradeRepository;

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
