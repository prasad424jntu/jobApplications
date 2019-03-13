package com.mycompany.jobapplications.bean;

import java.util.Date;

import com.mycompany.jobapplications.exceptions.JobOfferNotFound;

public class JobOffer {
	
	String jobTitle;
	//For Simplicity using date as String
	String date;
	int noOfApplications;
	
	
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getNoOfApplications() {
		return noOfApplications;
	}
	public void setNoOfApplications(int noOfApplications) {
		this.noOfApplications = noOfApplications;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.getJobTitle().equals(((JobOffer)obj).getJobTitle());
	}
	
	@Override
	public int hashCode() {
		return this.getJobTitle().hashCode();
	}
	
	@Override
	public String toString() {
		
		return "Job Title :"+this.getJobTitle()+" ,Job Application Date: "+this.getDate()+" , No OF Applications allowed "+this.getNoOfApplications();
	}
}
