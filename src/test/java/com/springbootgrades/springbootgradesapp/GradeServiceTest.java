package com.springbootgrades.springbootgradesapp;

import java.util.List;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
        // 1. Mock the data needed to carry out the unit test
        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(
            new Grade("Harry", "Potions", "C-"),
            new Grade("Hermoine", "Arithmancy", "A+")
            ));

        List<Grade> result = gradeService.getGrades();
            
        // 2. Call the method that you want to test
        // 3. Compare the value we were expecting vs the actual value we got
        assertEquals("Harry", result.get(0).getName());
        assertEquals("Arithmancy", result.get(1).getSubject());
    }

    @Test
    public void getGradeByIndexTest() {
        // 1. Mock data needed
        Grade grade = new Grade("Harry", "Potions", "C-");

        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        // 2. Call method that you want to test - getGradeIndex() function
        int valid = gradeService.getGradeIndex(grade.getId());
        int notFound = gradeService.getGradeIndex("123");

        // 3. Check if method is bahaving correctly
        assertEquals(0, valid);
        assertEquals(Constants.NOT_FOUND, notFound);
    }

    @Test
    public void getGradeByIdTest() {
        // 1. Mock data needed
        Grade grade = new Grade("Harry", "Potions", "C-");

        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        // 2. Call method you want to test
        String id = grade.getId();
        Grade result = gradeService.getGradeById(id);

        // 3. Check if method is bahving correctly
        assertEquals(grade, result);
    }

    @Test
    public void addGradeTest() {
        Grade grade = new Grade("Harry", "Potions", "C-");

        when(gradeRepository.getGrades()).thenReturn(Arrays.asList(grade));
        when(gradeRepository.getGrade(0)).thenReturn(grade);

        Grade newGrade = new Grade("Hermoine", "Arithmancy", "A+");
        gradeService.submitGrade(newGrade);

        // Check that addGrade() method gets called at least 1 time
        verify(gradeRepository, times(1)).addGrade(newGrade);
    }
}