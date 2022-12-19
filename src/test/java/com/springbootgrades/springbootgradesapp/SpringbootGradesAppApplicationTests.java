package com.springbootgrades.springbootgradesapp;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

	// INTEGRATION TESTS
	// Test if app can hadle GET reqs on empty path ("/")
	@Test
	public void testShowGradeForm() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/?id=123");

		mockMvc.perform(request) // will traverse the application layers
		.andExpect(status().is2xxSuccessful()) // make assertions(verifications) with regards to the model, view and status/response
		.andExpect(view().name("form"))
		.andExpect(model().attributeExists("grade")); 
	}

	// perform mock request on the path /handleSubmit
	// Test will validate the lifecycle of a successful submission
	@Test
	public void testSuccessfulSubmission() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/handleSubmit")
		.param("name", "Harry")
		.param("subject", "Potions")
		.param("score", "C-");

		mockMvc.perform(request)
		.andExpect(status().is3xxRedirection())
		.andExpect(redirectedUrl("/grades"));

	}

}
