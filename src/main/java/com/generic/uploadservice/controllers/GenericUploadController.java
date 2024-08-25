package com.generic.uploadservice.controllers;

import java.io.IOException;

import org.apache.poi.EmptyFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.generic.uploadservice.exceptions.InvalidFileTypeException;
import com.generic.uploadservice.exceptions.InvalidUploadTypeException;
import com.generic.uploadservice.services.UploadService;
/**
 *upload csv file and process the records based on uploadType
 *
 */
@RestController
@RequestMapping("/upload")
public class GenericUploadController {
	Logger logger = LoggerFactory.getLogger(GenericUploadController.class);
	@Autowired
	UploadService uploadService;

	/**
	 * Processes the uploaded csv file based on uploadtype
	 * @param file
	 * @param uploadType
	 * @return
	 * @throws InvalidFileTypeException when file type other than csv format are uploaded
	 * @throws IOException
	 * @throws EmptyFileException when the uploaded file doesn't contain records
	 * @throws InvalidUploadTypeException when the uploadtype doesn't present in the application
	 */
	@PostMapping
	public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("uploadType") String uploadType)
			throws InvalidFileTypeException, IOException, EmptyFileException, InvalidUploadTypeException {
		logger.info("GenericUploadController::uploadFile");
		if (file.getContentType().equals("text/csv")) {
			 uploadService.uploadData(file.getInputStream(), uploadType);
		} else {
			logger.error("GenericUploadController:: invalid file Type - {}", file.getContentType());
			throw new InvalidFileTypeException("invalid file type");
		}
			return ResponseEntity.ok().build();
	}

}
