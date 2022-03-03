package com.RentACar.core.results;

public class Result {
	
	private boolean success;
	private String message;
	// read only olsa yeter apide değişmesin constructorda set edilir
	
	public Result(boolean success) {
		this.success = success;
	}
	
	public Result(boolean success, String message) {
		this(success);
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMessage() {
		return message;
	}
	
}
