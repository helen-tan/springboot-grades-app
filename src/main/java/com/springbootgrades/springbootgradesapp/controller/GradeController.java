package com.springbootgrades.springbootgradesapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springbootgrades.springbootgradesapp.Constants;
import com.springbootgrades.springbootgradesapp.Grade;
import com.springbootgrades.springbootgradesapp.repository.GradeRepository;
import com.springbootgrades.springbootgradesapp.service.GradeService;

import jakarta.validation.Valid;

@Controller
public class GradeController {
    // Controller to get data from Repository - Create an instance of the Repository
    GradeRepository gradeRepository = new GradeRepository();
    
    // Returns the grades html (will find the view from the templates folder)
    // Model is directly accessed from the handler method's parameters
    @GetMapping("/grades")
    public String getGrades(Model model) {
        // Create Grade object (POJO object) + add to list
        // studentGrades.add(new Grade("Harry", "Potions", "C-"));
        // studentGrades.add(new Grade("Hermoine", "Arithmancy", "A+"));
        // studentGrades.add(new Grade("Neville", "Charms", "A-"));
       
        // Store data in model
        model.addAttribute("grades", gradeRepository.getGrades());
        return "grades";
    }

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        Grade grade;
        int index = getGradeIndex(id);

        if (index == Constants.NOT_FOUND) {
            grade = new Grade();
        } else {
            // grade = studentGrades.get(index);
            grade = gradeRepository.getGrade(index);
        }

        model.addAttribute("grade", grade);
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(@Valid Grade grade, BindingResult result) {
        // @Valid will use the logic inside ScoreValidator to check if valid
        // Print out boolean to check if user inputs have errors
        System.out.println("Has errors?" + result.hasErrors());

        // If there are errors, keep user inside the form
        if (result.hasErrors()) return "form";

        int index = getGradeIndex(grade.getId());
        // Add new grade only if it doesn't aldy exist
        if(index == Constants.NOT_FOUND) {
            //studentGrades.add(grade);
            gradeRepository.addGrade(grade); // Create
        } else {
            //studentGrades.set(index, grade);
            gradeRepository.updateGrade(grade, index); // Update
        }
        // Redirect user if form submission is successful
        return "redirect:/grades";
    }

    public Integer getGradeIndex(String id) {
        List<Grade> studentGrades = gradeRepository.getGrades();

        for (int i = 0; i < studentGrades.size(); i++) {
            if (studentGrades.get(i).getId().equals(id)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }
}
