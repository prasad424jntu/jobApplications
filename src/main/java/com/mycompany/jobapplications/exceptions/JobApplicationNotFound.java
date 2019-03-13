package com.mycompany.jobapplications.exceptions;

public class JobApplicationNotFound extends JobApplicationBaseAbstractException{

	public JobApplicationNotFound(String msg) {
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
