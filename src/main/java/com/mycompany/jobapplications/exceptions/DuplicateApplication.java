package com.mycompany.jobapplications.exceptions;

public class DuplicateApplication extends JobApplicationBaseAbstractException{
	
	public DuplicateApplication(String status) {
		setError(status);
		setMessage(status);
	}

	@Override
	public String getError() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}
	

}
