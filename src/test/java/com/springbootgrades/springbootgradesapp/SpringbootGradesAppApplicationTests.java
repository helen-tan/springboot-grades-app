package com.springbootgrades.springbootgradesapp;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.springbootgrades.springbootgradesapp.controller.GradeController;

@SpringBootTest
@AutoConfigureMockMvc // Will register the MockMvc bean inside the Spring Container, which means it then can be injected
class SpringbootGradesAppApplicationTests {

	// Autowire/dependency inject GradeController bean
	@Autowired
	private GradeController controller; // don't really need to do this

	@Autowired
	private MockMvc mockMvc;

	// Load Spring Context/ Spring Container. contextLoads is a lifesycle method
	@Test
	void contextLoads() {
		// verify that controller is not null (was the bean actually injected into the variable?)
		assertNotNull(controller); 
		// verify that MockMvc bean is not null
		assertNotNull(mockMvc);
	}

}
