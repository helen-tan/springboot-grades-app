package com.springbootgrades.springbootgradesapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GradeController {
    // List of grade objects (only populated when the controller is first initialized)
    List<Grade> studentGrades = new ArrayList<>();
    
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

    @GetMapping("/")
    public String getForm(Model model, @RequestParam(required = false) String id) {
        Grade grade;

        if (getGradeIndex(id) == Constants.NOT_FOUND) {
            grade = new Grade();
        } else {
            grade = studentGrades.get(getGradeIndex(id));
        }

        model.addAttribute("grade", grade);
        return "form";
    }

    @PostMapping("/handleSubmit")
    public String submitForm(Grade grade) {
        //System.out.println(grade);
        int index = getGradeIndex(grade.getId());
        // Add new grade only if it doesn't aldy exist
        if(index == Constants.NOT_FOUND) {
            studentGrades.add(grade);
        } else {
            studentGrades.set(index, grade);
        }
        return "redirect:/grades";
    }

    public Integer getGradeIndex(String id) {
        for (int i = 0; i < studentGrades.size(); i++) {
            if (studentGrades.get(i).getId().equals(id)) {
                return i;
            }
        }
        return Constants.NOT_FOUND;
    }
}
