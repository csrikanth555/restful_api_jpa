package com.example.demo;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import junit.framework.Assert;

@SpringBootTest
@AutoConfigureMockMvc
class RestfulWebservicesApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void helloWorld() throws Exception {
		System.out.println("Begin helloWorld method ");
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/hello-world")).andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
		System.out.println(mvcResult.getResponse().getStatus());
		String expected = "Hello World This is first SB !!!!!";
		String actual = mvcResult.getResponse().getContentAsString();
		Assert.assertEquals(expected, actual);
		System.out.println("End helloWorld method ");
	}

	
	public void retrieveAllUsers() throws Exception {
		System.out.println("Begin retrieveAllUsers method ");
		String uri = "/user";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
		System.out.println(mvcResult.getResponse().getStatus());
		String expected = "[{\"userId\":201,\"firstName\":\"Madhav\",\"lastName\":\"Reddy\",\"userType\":\"Gold\",\"startDate\":\"2018-08-20\"},{\"userId\":301,\"firstName\":\"Mallik\",\"lastName\":\"Arjun\",\"userType\":\"Silver\",\"startDate\":\"2018-07-20\"}]";
		System.out.println(expected);
		JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(), false);
		System.out.println("End retrieveAllUsers method ");
	}

	
	public void getUserByIdAPI() throws Exception {
		System.out.println("Begin getUserByIdAPI method :::::");
		MvcResult mvcResult = mockMvc
				.perform(MockMvcRequestBuilders.get("/user/{id}", 201).accept(MediaType.APPLICATION_JSON)).andReturn();
		System.out.println(mvcResult.getResponse().getStatus());
		System.out.println(mvcResult.getResponse().getContentAsString());
		String expected = "{\"userId\":201,\"firstName\":\"Madhav\",\"lastName\":\"Reddy\",\"userType\":\"Gold\",\"startDate\":\"2018-08-20\"}";
		System.out.println(expected);
		JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(), false);
		System.out.println("End getUserByIdAPI method :::::");
	}

}
