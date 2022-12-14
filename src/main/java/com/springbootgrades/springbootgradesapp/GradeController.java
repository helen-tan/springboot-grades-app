package com.springbootgrades.springbootgradesapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GradeController {
    // List of grade objects (only populated when the controller is first initialized)
    List<Grade> studentGrades = Arrays.asList(
        new Grade("Harry", "Potions", "C-"),
        new Grade("Hermoine", "Arithmancy", "A+"),
        new Grade("Neville", "Charms", "A-")
    );
    
    // Returns the grades html (will find the view from the templates folder)
    // Model is directly accessed from the handler method's parameters
    @GetMapping("/grades")
    public String getGrades(Model model) {
        // Create Grade object (POJO object) + add to list
        // studentGrades.add(new Grade("Harry", "Potions", "C-"));
        // studentGrades.add(new Grade("Hermoine", "Arithmancy", "A+"));
        // studentGrades.add(new Grade("Neville", "Charms", "A-"));
       
        // Store data in model
        model.addAttribute("grades", studentGrades);
        return "grades";
    }
}
