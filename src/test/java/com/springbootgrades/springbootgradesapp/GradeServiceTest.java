package com.springbootgrades.springbootgradesapp;

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

    @InjectMocks
    private GradeService gradeService;
}