package com.mycompany.jobapplications.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mycompany.jobapplications.bean.JobApplication;
import com.mycompany.jobapplications.bean.JobOffer;
import com.mycompany.jobapplications.exceptions.ApplicationStatusNotFound;
import com.mycompany.jobapplications.exceptions.DuplicateApplication;
import com.mycompany.jobapplications.exceptions.DuplicateJobOffer;
import com.mycompany.jobapplications.exceptions.JobApplicationNotFound;
import com.mycompany.jobapplications.exceptions.JobOfferNotFound;
import com.mycompany.jobapplications.repository.ApplicationRepository;
import com.mycompany.jobapplications.repository.JobOfferRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/api/v1")
public class JobApplicationsMainController {

 @Autowired
  private JobOfferRepository jobOfferRepository;
 
 @Autowired
 private ApplicationRepository applicationRepository;
 
 /**
  * This rest URI end point method is used to create job offers.
  * @param jobOffer
  * @return
  */
  @PostMapping("/jobOffers")
  public ResponseEntity<JobOffer> createJobOffer(@Valid @RequestBody JobOffer jobOffer) {
	  
    try {
		return ResponseEntity.ok().body(jobOfferRepository.createJobOffer(jobOffer));
	} catch (DuplicateJobOffer e) {
		e.printStackTrace();
		return new ResponseEntity("Duplicate Job Offer Found.",HttpStatus.NOT_FOUND);
	}
  }
 
  /**
   * This rest URI end point method is used to get the all job offers created by the User.
   * @return
   */
  @GetMapping("/jobOffers")
  public Set<JobOffer> getAllJobOffers() {
    return jobOfferRepository.getRawJobOffers();
  }
  
  /**
   * This rest URI end point method is used to fetch a specific job application for the given jobTitle(uniqe field)
   * @param jobTitle
   * @return
   */
  @GetMapping("/jobOffers/{jobTitle}")
  public ResponseEntity<JobOffer> getJobOfferByTitle(@PathVariable(value = "jobTitle") String jobTitle){
	  JobOffer jobOffer;
	try {
		jobOffer = (JobOffer)jobOfferRepository.getJobOffer(jobTitle);
	} catch (JobOfferNotFound e) {
		e.printStackTrace();
		return new ResponseEntity("Job Offer Not Found.",HttpStatus.NOT_FOUND);
	}
	  return ResponseEntity.ok().body(jobOffer);
  }
  
  /**
   * This rest URI end point method is used to apply for a specific job offer.Mapping between job offer and job application
   * is done with job Title.
   * @param jobApplication
   * @return
   */
  @PostMapping("/jobApplications")
  public ResponseEntity<JobApplication> createJobApplication(@Valid @RequestBody JobApplication jobApplication) {
	  try {
		  if(jobOfferRepository.getJobOffer(jobApplication.getRelatedOffer()) != null)
			  jobOfferRepository.saveJobApplication(jobApplication);
		  	  applicationRepository.saveJobApplication(jobApplication);
			  return ResponseEntity.ok().body(jobApplication);
	  }catch(JobOfferNotFound jobOfferNotFound) {
		  jobOfferNotFound.printStackTrace();
		  return new ResponseEntity("Job Offer Not Found.",HttpStatus.NOT_FOUND);
	  }catch(DuplicateApplication duplicateApplication) {
		  duplicateApplication.printStackTrace();
		  return new ResponseEntity("Duplicate Job Application.",HttpStatus.NOT_FOUND);
	  }
  }
  
  
  /**
   * This rest URI end point method is used to retrieve all the  job applications for a given job offer.
   * @param jobTitle
   * @return
   * @throws JobOfferNotFound
   */
  @GetMapping("/jobApplications/{jobTitle}")
  public ResponseEntity<List<JobApplication>> getJobApplications(@PathVariable(value = "jobTitle") String jobTitle){
	    try {
			return ResponseEntity.ok().body(jobOfferRepository.getJobApplications(jobTitle));
		} catch (JobOfferNotFound e) {
			e.printStackTrace();
			return new ResponseEntity("Job Offer Not Found.",HttpStatus.NOT_FOUND);
		}
  }
  
  /**
   * This rest URI end point method is used to get the specific candidate application for a given job offer.
   * Assumption here is candidate can apply for only one job offer with a given mail ID.
   * @param jobTitle
   * @param candidateEmail
   * @return
   * @throws JobOfferNotFound
   * @throws JobApplicationNotFound
   */
  @GetMapping("/jobApplications/{jobTitle}/{candidateEmail}")
  public ResponseEntity<JobApplication> getJobApplication(@PathVariable(value = "jobTitle") String jobTitle,@PathVariable(value = "candidateEmail") String candidateEmail) {
	    try {
			return ResponseEntity.ok().body(jobOfferRepository.getJobApplication(jobTitle,candidateEmail));
		} catch (JobOfferNotFound e) {
			e.printStackTrace();
			return new ResponseEntity("Job Offer Not Found.",HttpStatus.NOT_FOUND);
		}catch (JobApplicationNotFound e) {
			e.printStackTrace();
			return new ResponseEntity("Job Application Not Found.",HttpStatus.NOT_FOUND);
		}
  }
  
  
  /**
   * This rest URI end point method is used to update the status of the job application for a specific job offer and specific candidate.
   * @param jobTitle
   * @param candidateEmail
   * @param appStatus
   * @return
   * @throws ApplicationStatusNotFound
   * @throws JobApplicationNotFound
   * @throws JobOfferNotFound
   */
  @GetMapping("/jobApplications/{jobTitle}/{candidateEmail}/{appStatus}")
  public ResponseEntity<JobApplication> getJobApplications(@PathVariable(value = "jobTitle") String jobTitle,@PathVariable(value = "candidateEmail") String candidateEmail, @PathVariable(value = "appStatus") String appStatus) {
	    
	    try {
	    	jobOfferRepository.updateJobApplication(jobTitle,candidateEmail,appStatus);
			return ResponseEntity.ok().body(applicationRepository.updateJobApplication(candidateEmail,appStatus));
		} catch (ApplicationStatusNotFound e) {
			e.printStackTrace();
			return new ResponseEntity("Application Status Not Found.",HttpStatus.NOT_FOUND);
		} catch (JobApplicationNotFound e) {
			e.printStackTrace();
			return new ResponseEntity("Job Application Not Found.",HttpStatus.NOT_FOUND);
		} catch (JobOfferNotFound e) {
			e.printStackTrace();
			return new ResponseEntity("Job Offer Not Found.",HttpStatus.NOT_FOUND);
		}
  }
  
  /**
   * This rest end point URI method is used to fetch all the job applications for all the Job Offers.
   * @return
   * @throws JobOfferNotFound
   */
  @GetMapping("/jobApplications")
  public ResponseEntity<List<JobApplication>> getAllJobApplications() {
	    return ResponseEntity.ok().body(applicationRepository.getJobapplications());
  }
}