package com.generic.uploadservice.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.poi.EmptyFileException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.generic.uploadservice.exceptions.InvalidUploadTypeException;
import com.generic.uploadservice.services.UploadService;

@SpringBootTest
@AutoConfigureMockMvc
public class GenericUploadControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private WebApplicationContext context;
	@MockBean
	UploadService uploadService;

	@Test
	public void testUploadFile_WithInvalidFileType() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE,
				"Hello, World!".getBytes());
		mockMvc.perform(multipart("/upload").file(file).param("uploadType", "sample"))
				.andExpect(status().isUnprocessableEntity());
	}

	@Test
	public void testUploadFile_WithEmptyFile() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		String content = "Id,Title,Description,Published\n" + "1,Spring Boot Tut#1,Tut#1 Description,FALSE";
		byte[] fileContent = content.getBytes(StandardCharsets.UTF_8);
		doThrow(EmptyFileException.class).when(uploadService).uploadData(any(), any());
		MockMultipartFile filePart = new MockMultipartFile("file", "orig.csv", "text/csv", fileContent);
		mockMvc.perform(multipart("/upload").file(filePart).param("uploadType", "sample"))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testtUploadFile_WithValidData_IOException() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		String content = "Id,Title,Description,Published\n" + "1,Spring Boot Tut#1,Tut#1 Description,FALSE";
		byte[] fileContent = content.getBytes(StandardCharsets.UTF_8);
		doThrow(IOException.class).when(uploadService).uploadData(any(), any());
		MockMultipartFile filePart = new MockMultipartFile("file", "orig.csv", "text/csv", fileContent);
		mockMvc.perform(multipart("/upload").file(filePart).param("uploadType", "sample")).andExpect(status().isInternalServerError());
	}
	

	@Test
	public void testtUploadFile_WithFileTypeAndContent() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		String content = "Id,Title,Description,Published\n" + "1,Spring Boot Tut#1,Tut#1 Description,FALSE";
		byte[] fileContent = content.getBytes(StandardCharsets.UTF_8);
		MockMultipartFile filePart = new MockMultipartFile("file", "orig.csv", "text/csv", fileContent);
		mockMvc.perform(multipart("/upload").file(filePart).param("uploadType", "sample")).andExpect(status().isOk());
	}

	@Test
	public void testUploadFile_WithInvalidUploadType() throws Exception {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		String content = "Id,Title,Description,Published\n" + "1,Spring Boot Tut#1,Tut#1 Description,FALSE";
		byte[] fileContent = content.getBytes(StandardCharsets.UTF_8);
		doThrow(InvalidUploadTypeException.class).when(uploadService).uploadData(any(), any());
		MockMultipartFile filePart = new MockMultipartFile("file", "orig.csv", "text/csv", fileContent);
		mockMvc.perform(multipart("/upload").file(filePart).param("uploadType", "sample"))
				.andExpect(status().isUnprocessableEntity());
	}
}
