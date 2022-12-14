package com.springbootgrades.springbootgradesapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GradeController {
    
    @GetMapping("/grades")
    // Returns the grades html (will find the view from the templates folder)
    public String getGrades() {
        return "grades";
    }
}
