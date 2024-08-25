package com.generic.uploadservice.exceptions;

/**
 * Exception to throw if an invalid Upload type other than configured entities
 * is used
 */
public class InvalidUploadTypeException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidUploadTypeException() {
		super();
	}

	public InvalidUploadTypeException(String message) {
		super(message);
	}
}
