package com.project.crm.dto.response;

public class GenericResponse {
	private String message;

	// Constructor
	public GenericResponse(String message) {
		this.message = message;
	}

	// Getters and setters
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}