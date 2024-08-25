package com.generic.uploadservice.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.EmptyFileException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.generic.uploadservice.entities.Brand;
import com.generic.uploadservice.entities.Creditstatus;
import com.generic.uploadservice.entities.Customertype;
import com.generic.uploadservice.entities.Gstntype;
import com.generic.uploadservice.entities.Rating;
import com.generic.uploadservice.entities.State;
import com.generic.uploadservice.exceptions.InvalidUploadTypeException;
import com.generic.uploadservice.repositories.BrandRepository;
import com.generic.uploadservice.repositories.CreditStatusRepository;
import com.generic.uploadservice.repositories.CustomerTypeRepository;
import com.generic.uploadservice.repositories.GstnTypeRepository;
import com.generic.uploadservice.repositories.RatingRepository;
import com.generic.uploadservice.repositories.StateRepository;

import jakarta.validation.ValidationException;
import jakarta.validation.Validator;

@ExtendWith(MockitoExtension.class)
public class UploadServiceTest {
	@Mock
	StateRepository stateRepository;
	@Mock
	BrandRepository brandRepository;
	@Mock
	Validator validator;
	@Mock
	RatingRepository ratingRepository;
	@Mock
	CreditStatusRepository creditStatusRepository;
	@Mock
	GstnTypeRepository gstnTypeRepository;
	@Mock
	CustomerTypeRepository customerTypeRepository;
	@Spy
	@InjectMocks
	UploadService uploadService;

	@Test
	public void testUploadType_WithEmptyFile() throws FileNotFoundException {
		File file = new File("./src/test/resources/emptydata.csv");
		InputStream inputStream = new FileInputStream(file);
		assertThrows(EmptyFileException.class, () -> uploadService.uploadData(inputStream, "dummy"));
	}

	@Test
	public void testUploadType_WithValidFileAndInvalidUploadType() throws IOException {
		File file = new File("./src/test/resources/state_data.csv");
		InputStream inputStream = new FileInputStream(file);
		assertThrows(InvalidUploadTypeException.class, () -> uploadService.uploadData(inputStream, "dummy"));
	}

	@Test
	public void testUploadType_WithInValidStateCSVFile()
			throws IOException, EmptyFileException, InvalidUploadTypeException {
		File file = new File("./src/test/resources/state_data.csv");
		InputStream inputStream = new FileInputStream(file);
		when(validator.validate(any())).thenThrow(new ValidationException());
		assertThrows(ValidationException.class,()-> uploadService.uploadData(inputStream, "STATE"));
		verify(uploadService,times(1)).processStateData(anyList(), anyList());
		verify(uploadService,times(1)).convertCSVRecordToState(any(), any());
	}
	
	@Test
	public void testUploadType_WithInValidRatingCSVFile()
			throws IOException, EmptyFileException, InvalidUploadTypeException {
		File file = new File("./src/test/resources/rating.csv");
		InputStream inputStream = new FileInputStream(file);
		when(validator.validate(any())).thenThrow(new ValidationException());
		assertThrows(ValidationException.class,()-> uploadService.uploadData(inputStream, "RATING"));
		verify(uploadService,times(1)).processRatingData(anyList(), anyList());
		verify(uploadService,times(1)).convertCSVRecordToRating(any(), any());
	}
	
	@Test
	public void testUploadType_WithInValidCustomerTypeCSVFile()
			throws IOException, EmptyFileException, InvalidUploadTypeException {
		File file = new File("./src/test/resources/customertype.csv");
		InputStream inputStream = new FileInputStream(file);
		when(validator.validate(any())).thenThrow(new ValidationException());
		assertThrows(ValidationException.class,()-> uploadService.uploadData(inputStream, "customertype"));
		verify(uploadService,times(1)).processCustomertypeData(anyList(), anyList());
		verify(uploadService,times(1)).convertCSVRecordToCustomerType(any(), any());
	}
	
	@Test
	public void testUploadType_WithInValidCreditStatusCSVFile()
			throws IOException, EmptyFileException, InvalidUploadTypeException {
		File file = new File("./src/test/resources/creditstatus.csv");
		InputStream inputStream = new FileInputStream(file);
		when(validator.validate(any())).thenThrow(new ValidationException());
		assertThrows(ValidationException.class,()-> uploadService.uploadData(inputStream, "creditstatus"));
		verify(uploadService,times(1)).processCreditStatusData(anyList(), anyList());
		verify(uploadService,times(1)).convertCSVRecordToCreditStatus(any(), any());
	}
	
	@Test
	public void testUploadType_WithInValidGSTNTYPECSVFile()
			throws IOException, EmptyFileException, InvalidUploadTypeException {
		File file = new File("./src/test/resources/GSTNTYPE.csv");
		InputStream inputStream = new FileInputStream(file);
		when(validator.validate(any())).thenThrow(new ValidationException());
		assertThrows(ValidationException.class,()-> uploadService.uploadData(inputStream, "GSTNTYPE"));
		verify(uploadService,times(1)).processGstnTypeData(anyList(), anyList());
		verify(uploadService,times(1)).convertCSVRecordToGstntype(any(), any());
	}
	
	@Test
	public void testUploadType_WithInValidBrandCSVFile()
			throws IOException, EmptyFileException, InvalidUploadTypeException {
		File file = new File("./src/test/resources/brand.csv");
		InputStream inputStream = new FileInputStream(file);
		when(validator.validate(any())).thenThrow(new ValidationException());
		assertThrows(ValidationException.class,()-> uploadService.uploadData(inputStream, "BRAND"));
		verify(uploadService,times(1)).processBrandData(anyList(), anyList());
		verify(uploadService,times(1)).convertCSVRecordToBrand(any(), any());
	}
	
	@Test
	public void testUploadType_WithValidStateCSVFile()
			throws IOException, EmptyFileException, InvalidUploadTypeException {
		File file = new File("./src/test/resources/state_data.csv");
		State state = new State();
		state.setStatecode(12);
		state.setStatename("dummy");
		doReturn(state).when(uploadService).convertCSVRecordToState(any(),any());
		InputStream inputStream = new FileInputStream(file);
		uploadService.uploadData(inputStream, "STATE");
		verify(uploadService,times(1)).processStateData(anyList(), anyList());
		verify(stateRepository,times(1)).saveAll(anyList());
	}
	
	@Test
	public void testUploadType_WithValidBrandCSVFile()
			throws IOException, EmptyFileException, InvalidUploadTypeException {
		File file = new File("./src/test/resources/brand.csv");
		Brand brand = new Brand();
		brand.setBrandcode("REL");
		brand.setBrandname("RELIANCE");
		doReturn(brand).when(uploadService).convertCSVRecordToBrand(any(),any());
		InputStream inputStream = new FileInputStream(file);
		uploadService.uploadData(inputStream, "BRAND");
		verify(uploadService,times(1)).processBrandData(anyList(), anyList());
		verify(brandRepository,times(1)).saveAll(anyList());
	}
	
	@Test
	public void testUploadType_WithValidCreditStatusCSVFile()
			throws IOException, EmptyFileException, InvalidUploadTypeException {
		File file = new File("./src/test/resources/creditstatus.csv");
		Creditstatus creditstatus = new Creditstatus();
		doReturn(creditstatus).when(uploadService).convertCSVRecordToCreditStatus(any(),any());
		InputStream inputStream = new FileInputStream(file);
		uploadService.uploadData(inputStream, "creditstatus");
		verify(uploadService,times(1)).processCreditStatusData(anyList(), anyList());
		verify(creditStatusRepository,times(1)).saveAll(anyList());
	}
	
	@Test
	public void testUploadType_WithValidRatingCSVFile()
			throws IOException, EmptyFileException, InvalidUploadTypeException {
		File file = new File("./src/test/resources/rating.csv");
		Rating rating = new Rating();
		doReturn(rating).when(uploadService).convertCSVRecordToRating(any(),any());
		InputStream inputStream = new FileInputStream(file);
		uploadService.uploadData(inputStream, "rating");
		verify(uploadService,times(1)).processRatingData(anyList(), anyList());
		verify(ratingRepository,times(1)).saveAll(anyList());
	}
	
	@Test
	public void testUploadType_WithValidGstnTypeCSVFile()
			throws IOException, EmptyFileException, InvalidUploadTypeException {
		File file = new File("./src/test/resources/gstntype.csv");
		doReturn(new Gstntype()).when(uploadService).convertCSVRecordToGstntype(any(), any());
		InputStream inputStream = new FileInputStream(file);
		uploadService.uploadData(inputStream, "gstntype");
		verify(uploadService,times(1)).processGstnTypeData(anyList(), anyList());
		verify(gstnTypeRepository,times(1)).saveAll(anyList());
	}
	
	@Test
	public void testUploadType_WithValidCustomerTypeCSVFile()
			throws IOException, EmptyFileException, InvalidUploadTypeException {
		File file = new File("./src/test/resources/customertype.csv");
		doReturn(new Customertype()).when(uploadService).convertCSVRecordToCustomerType(any(), any());
		InputStream inputStream = new FileInputStream(file);
		uploadService.uploadData(inputStream, "customertype");
		verify(uploadService,times(1)).processCustomertypeData(anyList(), anyList());
		verify(customerTypeRepository,times(1)).saveAll(anyList());
	}

}
