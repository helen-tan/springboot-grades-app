package com.springbootgrades.springbootgradesapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GradeController {
    
    @GetMapping("/grades")
    // Returns the grades html (will find the view from the templates folder)
    // Model is directly accessed from the handler method's parameters
    public String getGrades(Model model) {
        // Create Grade object (POJO object)
        Grade grade =new Grade("Harry", "Potions", "C-");
        // Store data in model
        model.addAttribute("grade", grade);
        return "grades";
    }
}
