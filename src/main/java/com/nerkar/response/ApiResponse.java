package com.nerkar.response;

public class ApiResponse {
	
	private String message;
	
	private String status;
	
	public ApiResponse() {
		// TODO Auto-generated constructor stub
	}

	public ApiResponse(String message, String status) {
		super();
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
