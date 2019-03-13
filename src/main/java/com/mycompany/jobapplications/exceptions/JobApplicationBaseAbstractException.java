package com.mycompany.jobapplications.exceptions;

public abstract class JobApplicationBaseAbstractException extends Exception implements JobApplicationsBaseException {
	
	private String error;
	
	private String message;

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getError() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setError(String error) {
		this.error = error;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
