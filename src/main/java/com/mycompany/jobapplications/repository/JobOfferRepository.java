package com.mycompany.jobapplications.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.stereotype.Component;

import com.mycompany.jobapplications.bean.JobApplication;
import com.mycompany.jobapplications.bean.JobOffer;
import com.mycompany.jobapplications.exceptions.ApplicationStatusNotFound;
import com.mycompany.jobapplications.exceptions.DuplicateJobOffer;
import com.mycompany.jobapplications.exceptions.JobApplicationNotFound;
import com.mycompany.jobapplications.exceptions.JobOfferNotFound;
import com.mycompany.jobapplications.utils.Utils;

@Component
public class JobOfferRepository {
	
	Map<JobOffer,List<JobApplication>> jobOffers = new HashMap<>();
	
	static Set<JobOffer> rawJobOffers = new HashSet<>();

	public Map<JobOffer, List<JobApplication>> getJobOffers() {
		return jobOffers;
	}

	public void setJobOffers(Map<JobOffer, List<JobApplication>> jobOffers) {
		this.jobOffers = jobOffers;
	}	
	
	public Set<JobOffer> getRawJobOffers() {
		return rawJobOffers;
	}

	public void setRawJobOffers(Set<JobOffer> rawJobOffers) {
		this.rawJobOffers = rawJobOffers;
	}

	public void addJobOffer() {
		
	}
	
	public void addJobApplication() {
		
	}
	

	public JobOffer createJobOffer(JobOffer jobOffer) throws DuplicateJobOffer {
		if(!this.getRawJobOffers().contains(jobOffer)) {
			this.getJobOffers().put(jobOffer, new ArrayList<JobApplication>());
			this.getRawJobOffers().add(jobOffer);
			System.out.println("rawJobOffers Length : " + this.getRawJobOffers().size());
			return jobOffer;
		}
		throw new DuplicateJobOffer("Duplicate Job Offer Found.");
		
	}
	
	public JobOffer getJobOffer(String jobTitle) throws JobOfferNotFound{
		Set<JobOffer> jobOfferSet = this.getRawJobOffers();
		for (JobOffer jobOffer : jobOfferSet) {
			System.out.println("jobOffer Name to get the rawJobOffer: " + jobOffer.getJobTitle());
			if(jobOffer.getJobTitle().equalsIgnoreCase(jobTitle))
				return jobOffer;
		}
		throw new JobOfferNotFound("");
	}
	
	public List<JobApplication> getJobApplications(String jobTitle) throws JobOfferNotFound{
		JobOffer jobOffer=this.getJobOffer(jobTitle);
		return this.getJobOffers().get(jobOffer);
	}
	
	public List<JobApplication> getJobApplications(JobOffer jobOffer) throws JobOfferNotFound{
		return this.getJobOffers().get(jobOffer);
	}

	public JobApplication getJobApplication(String jobTitle, String candidateEmail) throws JobOfferNotFound,JobApplicationNotFound {
		List<JobApplication> jobApplications= this.getJobApplications(jobTitle);
		for (JobApplication jobApplication : jobApplications) {
			if(jobApplication.getCandidateEmail().equalsIgnoreCase(candidateEmail))
				return jobApplication;
		}
		throw new JobApplicationNotFound("");
	}

	public JobApplication updateJobApplication(String jobTitle,String candidateEmail, String appStatus) throws JobApplicationNotFound, JobOfferNotFound, ApplicationStatusNotFound {
		List<JobApplication> jobApplications= this.getJobApplications(jobTitle);
		for (JobApplication jobApplication : jobApplications) {
			if(jobApplication.getCandidateEmail().equalsIgnoreCase(candidateEmail)) {
				jobApplication.setAppStatus(Utils.getStatus(appStatus));
				/**
				 * Application status is updated and notification is sent to Candidate.
				 */
				Utils.sendNotification(appStatus, jobTitle, candidateEmail);
				return jobApplication;
			}
		}
		throw new JobApplicationNotFound("");
		
	}

	public void saveJobApplication(@Valid JobApplication jobApplication) throws JobOfferNotFound {
		for (JobOffer jobOffer : this.getRawJobOffers()) {
			if(jobOffer.getJobTitle().equalsIgnoreCase(jobApplication.getRelatedOffer())) {
				this.getJobApplications(jobOffer).add(jobApplication);
			}
			
		} 
		
	}
	
	
}
