package com.generic.uploadservice.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.EmptyFileException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import jakarta.validation.Validator;

/**
 * processes the data in uploaded csv file and throws validation errors if any,
 * else saves the records in databases
 */
@Service
public class UploadService {
	private static final String STATE = "STATE";
	private static final String BRAND = "BRAND";
	private static final String RATING = "RATING";
	private static final String CUSTOMERTYPE = "CUSTOMERTYPE";
	private static final String CREDITSTATUS = "CREDITSTATUS";
	private static final String GSTNTYPE = "GSTNTYPE";
	@Autowired
	StateRepository stateRepository;
	@Autowired
	BrandRepository brandRepository;
	@Autowired
	Validator validator;
	@Autowired
	RatingRepository ratingRepository;
	@Autowired
	CreditStatusRepository creditStatusRepository;
	@Autowired
	GstnTypeRepository gstnTypeRepository;
	@Autowired
	CustomerTypeRepository customerTypeRepository;

	/**
	 * reads the contents of csv file and processes then based on uploadType
	 * @param in
	 * @param uploadType
	 * @throws IOException
	 * @throws EmptyFileException if contents of csv file is empty
	 * @throws InvalidUploadTypeException when other than configured entity types are used
	 */
	public void uploadData(InputStream in, String uploadType)
			throws IOException, EmptyFileException, InvalidUploadTypeException {
		CSVParser csvParser = CSVParser.parse(new BufferedReader(new InputStreamReader(in)),
				CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter(','));
		List<String> headers = csvParser.getHeaderNames();
		List<CSVRecord> csvRecords = csvParser.getRecords();
		if (csvRecords.isEmpty()) {
			throw new EmptyFileException();
		} else {
			switch (uploadType.toUpperCase()) {
			case STATE:
				processStateData(csvRecords, headers);
				break;
			case BRAND:
				processBrandData(csvRecords, headers);
				break;
			case RATING:
				processRatingData(csvRecords, headers);
				break;
			case CREDITSTATUS:
				processCreditStatusData(csvRecords, headers);
				break;
			case GSTNTYPE:
				processGstnTypeData(csvRecords, headers);
				break;
			case CUSTOMERTYPE:
				processCustomertypeData(csvRecords, headers);
				break;
			default:
				throw new InvalidUploadTypeException("Invalid upload type");
			}
		}
	}

	/**
	 * process the csvRecords of GSTNType entity
	 * @param csvRecords
	 * @param headers
	 */
	public void processGstnTypeData(List<CSVRecord> csvRecords, List<String> headers) {
		List<Gstntype> gstnTypes = csvRecords.stream().map(gstnType -> convertCSVRecordToGstntype(gstnType, headers))
				.collect(Collectors.toList());
		gstnTypeRepository.saveAll(gstnTypes);
	}

	/**
	 * process the csvRecords of CustomerType entity
	 * @param csvRecords
	 * @param headers
	 */
	public void processCustomertypeData(List<CSVRecord> csvRecords, List<String> headers) {
		List<Customertype> customertypes = csvRecords.stream()
				.map(customerType -> convertCSVRecordToCustomerType(customerType, headers))
				.collect(Collectors.toList());
		customerTypeRepository.saveAll(customertypes);
	}

	/**
	 * process the csvRecords of State entity
	 * @param csvRecords
	 * @param headers
	 */
	public void processStateData(List<CSVRecord> csvRecords, List<String> headers) {
		List<State> states = csvRecords.stream().map(state -> convertCSVRecordToState(state, headers))
				.collect(Collectors.toList());
		stateRepository.saveAll(states);
	}

	/**
	 * process the csvRecords of Brand entity
	 * @param csvRecords
	 * @param headers
	 */
	public void processBrandData(List<CSVRecord> csvRecords, List<String> headers) {
		List<Brand> brands = csvRecords.stream().map(brand -> convertCSVRecordToBrand(brand, headers))
				.collect(Collectors.toList());
		brandRepository.saveAll(brands);
	}

	/**
	 * process the csvRecords of Rating entity
	 * @param csvRecords
	 * @param headers
	 */
	public void processRatingData(List<CSVRecord> csvRecords, List<String> headers) {
		List<Rating> ratings = csvRecords.stream().map(rating -> convertCSVRecordToRating(rating, headers))
				.collect(Collectors.toList());
		ratingRepository.saveAll(ratings);
	}

	/**
	 * process the csvRecords of CreditStatus entity
	 * @param csvRecords
	 * @param headers
	 */
	public void processCreditStatusData(List<CSVRecord> csvRecords, List<String> headers) {
		List<Creditstatus> creditStatuses = csvRecords.stream()
				.map(rating -> convertCSVRecordToCreditStatus(rating, headers)).collect(Collectors.toList());
		creditStatusRepository.saveAll(creditStatuses);
	}

	/**
	 * converts the CsvRecord to GSTNType entity
	 * @param csvRecord
	 * @param headers
	 * @return
	 */
	public Gstntype convertCSVRecordToGstntype(CSVRecord csvRecord, List<String> headers) {
		Gstntype gstntype = new Gstntype();
		gstntype.setGsttypedesc(csvRecord.get(headers.get(0)));
		validateData(gstntype);
		return gstntype;
	}

	/**
	 * converts the CsvRecord to CustomerType entity
	 * @param csvRecord
	 * @param headers
	 * @return
	 */
	public Customertype convertCSVRecordToCustomerType(CSVRecord csvRecord, List<String> headers) {
		Customertype customertype = new Customertype();
		customertype.setBizcontacttypedesc(csvRecord.get(headers.get(0)));
		validateData(customertype);
		return customertype;
	}

	/**
	 * converts the CsvRecord to CreditStatus entity
	 * @param csvRecord
	 * @param headers
	 * @return
	 */
	public Creditstatus convertCSVRecordToCreditStatus(CSVRecord csvRecord, List<String> headers) {
		Creditstatus creditStatus = new Creditstatus();
		creditStatus.setCreditstatus(csvRecord.get(headers.get(0)));
		validateData(creditStatus);
		return creditStatus;
	}

	/**
	 * converts the CsvRecord to Brand entity
	 * @param csvRecord
	 * @param headers
	 * @return
	 */
	public Brand convertCSVRecordToBrand(CSVRecord csvRecord, List<String> headers) {
		Brand brand = new Brand();
		brand.setBrandcode((csvRecord.get(0)));
		brand.setBrandname(csvRecord.get(1));
		validateData(brand);
		return brand;
	}

	/**
	 * converts the CsvRecord to Rating entity
	 * @param csvRecord
	 * @param headers
	 * @return
	 */
	public Rating convertCSVRecordToRating(CSVRecord csvRecord, List<String> headers) {
		Rating rating = new Rating();
		rating.setRating(csvRecord.get(headers.get(0)));
		validateData(rating);
		return rating;
	}

	/**
	 * converts the CsvRecord to State entity
	 * @param csvRecord
	 * @param headers
	 * @return
	 */
	public State convertCSVRecordToState(CSVRecord csvRecord, List<String> headers) {
		State state = new State();
		state.setStatecode(Integer.parseInt(csvRecord.get(headers.get(0))));
		state.setStatename(csvRecord.get(headers.get(1)));
		validateData(state);
		return state;
	}

	/**
	 * validates the fields of entity and throws exception if any validations fail
	 * @param data
	 */
	private void validateData(Object data) {
		Set<ConstraintViolation<Object>> validationErrors = validator.validate(data);
		StringBuffer errorMessage = new StringBuffer();
		if (!validationErrors.isEmpty()) {
			for (ConstraintViolation<Object> c : validationErrors) {
				errorMessage.append(c.getPropertyPath().toString() + "-" + c.getMessage());
				errorMessage.append(";");
			}
			throw new ValidationException(errorMessage.toString());
		}
	}
}
