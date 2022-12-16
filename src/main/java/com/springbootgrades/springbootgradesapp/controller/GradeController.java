package com.springbootgrades.springbootgradesapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springbootgrades.springbootgradesapp.Grade;
import com.springbootgrades.springbootgradesapp.service.GradeService;

import jakarta.validation.Valid;

@Controller
public class GradeController {
    // Controller to communicate w Service
    // Dependency Injection: Autowire the bean into the gradeService variable from the Spring container (Do not create an instance of the Service)
    @Autowired
    GradeService gradeService;
    
    // Returns the grades html (will find the view from the templates folder)
    // Model is directly accessed from the handler method's parameters
    @GetMapping("/grades")
    public String getGrades(Model model) {
        // Create Grade object (POJO object) + add to list
        // studentGrades.add(new Grade("Harry", "Potions", "C-"));
        // studentGrades.add(new Grade("Hermoine", "Arithmancy", "A+"));
        // studentGrades.add(new Grade("Neville", "Charms", "A-"));
       
        // Store data in model
        model.addAttribute("grades", gradeService.getGrades());
        return "grades";
    }

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        
        model.addAttribute("grade", gradeService.getGradeById(id));
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(@Valid Grade grade, BindingResult result) {
        // @Valid will use the logic inside ScoreValidator to check if valid
        // Print out boolean to check if user inputs have errors
        System.out.println("Has errors?" + result.hasErrors());

        // If there are errors, keep user inside the form
        if (result.hasErrors()) return "form";

        // Submit the user input grade to the Service
        gradeService.submitGrade(grade);

        // Redirect user if form submission is successful
        return "redirect:/grades";
    }

}
