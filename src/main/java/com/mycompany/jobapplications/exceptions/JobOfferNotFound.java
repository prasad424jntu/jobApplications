package com.mycompany.jobapplications.exceptions;

public class JobOfferNotFound extends JobApplicationBaseAbstractException{

	public JobOfferNotFound(String msg) {
		setError(msg);
		setMessage(msg);
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
