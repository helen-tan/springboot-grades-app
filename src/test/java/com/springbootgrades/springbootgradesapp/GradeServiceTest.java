package com.springbootgrades.springbootgradesapp;

import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.springbootgrades.springbootgradesapp.repository.GradeRepository;
import com.springbootgrades.springbootgradesapp.service.GradeService;

@RunWith(MockitoJUnitRunner.class)
public class GradeServiceTest {

    // Create Mock for mimicking GradeRepository
    // Later used to inject into the GradeRepository variable in GradeService
    @Mock
    private GradeRepository gradeRepository;

    // @InjectMocks creates an object out of the class you want to test
    // (GradeService)
    // also inject the GradeRepository mock into the GradeService object
    @InjectMocks
    private GradeService gradeService;

    // Test if getGrades can recieve the grades from the repo
    @Test
    public void getGradesFromRepoTest() {
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(
            new Grade("Harry", "Potions", "C-"),
            new Grade("Hermoine", "Arithmancy", "A+")
            ));

        List<Grade> result = gradeService.getGrades();

        // Compare the value we were expecting vs the actual value we got
        assertEquals("Harry", result.get(0).getName());
        assertEquals("Arithmancy", result.get(1).getSubject());
    }
}