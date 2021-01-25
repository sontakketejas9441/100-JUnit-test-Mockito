package com.ts.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ts.binding.Book;
import com.ts.service.BookServiceImpl;

@WebMvcTest(value = SampleController.class)
public class SampleControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	BookServiceImpl bookService;
	
	@Test
	public void test_Welcome() throws Exception {
		
		when(bookService.welcomeMsg()).thenReturn("Hello From MockBean object");
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/home");
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}

	@Test
	public void test_saveBook_01() throws Exception {
		
		Book b = new Book(101,"Demo Book", 460.00);
		
		ObjectMapper mapper = new ObjectMapper();
		String bookObjJson = mapper.writeValueAsString(b);
		
		when(bookService.saveBook(b)).thenReturn(true);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveBookDtls")
																															.contentType(MediaType.APPLICATION_JSON)
																															.content(bookObjJson);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(201, status);
	}
	
	@Test
	public void test_saveBook_02() throws Exception {
		
		Book b = new Book(101,"Demo Book", 460.00);
		
		ObjectMapper mapper = new ObjectMapper();
		String bookObjJson = mapper.writeValueAsString(b);
		
		when(bookService.saveBook(b)).thenReturn(false);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveBookDtls")
																															.contentType(MediaType.APPLICATION_JSON)
																															.content(bookObjJson);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(400, status);
	}
}
