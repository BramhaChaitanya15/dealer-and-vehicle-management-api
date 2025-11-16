package com.dealer.vehicle.management.api.payloads;

public class ApiResponse {

	private String message;
	private boolean success;
	
	public ApiResponse(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}
	
	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	//getters and setters
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	@Override
	public String toString() {
		return "ApiResponse [message=" + message + ", success=" + success + "]";
	}
}

