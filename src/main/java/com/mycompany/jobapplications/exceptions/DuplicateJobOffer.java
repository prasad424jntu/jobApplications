package com.mycompany.jobapplications.exceptions;

public class DuplicateJobOffer extends JobApplicationBaseAbstractException{
	
	public DuplicateJobOffer(String status) {
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
