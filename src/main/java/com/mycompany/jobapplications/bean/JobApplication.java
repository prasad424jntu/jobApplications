package com.mycompany.jobapplications.bean;

import com.mycompany.jobapplications.utils.ApplicationStatus;

public class JobApplication {

	String relatedOffer;
	String candidateEmail;
	String resumeText;
	ApplicationStatus appStatus;
	
	public String getRelatedOffer() {
		return relatedOffer;
	}
	public void setRelatedOffer(String relatedOffer) {
		this.relatedOffer = relatedOffer;
	}
	public String getCandidateEmail() {
		return candidateEmail;
	}
	public void setCandidateEmail(String candidateEmail) {
		this.candidateEmail = candidateEmail;
	}
	public String getResumeText() {
		return resumeText;
	}
	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}
	public ApplicationStatus getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(ApplicationStatus appStatus) {
		this.appStatus = appStatus;
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.getCandidateEmail().equals(((JobApplication)obj).getCandidateEmail());
	}
	
	@Override
	public int hashCode() {
		return this.getCandidateEmail().hashCode();
	}
	
	@Override
	public String toString() {
		return "The Job Title: "+this.getRelatedOffer()+" , Candidate eMail:"+this.getCandidateEmail()+" ,Resume Text:"+this.getResumeText()+" ,Application Status"+this.getAppStatus();
	}
	
	
}
