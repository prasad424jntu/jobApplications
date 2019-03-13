package com.mycompany.jobapplications;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mycompany.jobapplications.bean.JobApplication;
import com.mycompany.jobapplications.bean.JobOffer;
import com.mycompany.jobapplications.utils.ApplicationStatus;

import junit.framework.Assert;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = JobapplicationsApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JobapplicationsApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	private String getRootUrl() {
		return "http://localhost:" + port;
	}
	
	@Test
	public void contextLoads() {
	}
	
	
	@Test
	public void testACreateOffer() {
		HttpHeaders headers = new HttpHeaders();
		JobOffer jobOffer = new JobOffer();
		jobOffer.setJobTitle("Senior Developer");
		jobOffer.setDate("01-01-10");
		jobOffer.setNoOfApplications(10);
		HttpEntity<JobOffer> entity = new HttpEntity<JobOffer>(jobOffer, headers);
		ResponseEntity<JobOffer> response = restTemplate.exchange(getRootUrl() + "/api/v1/jobOffers",
		HttpMethod.POST, entity, JobOffer.class);
		JobOffer resultJobOffer =response.getBody();
		System.out.println("Created Job Offer: "+resultJobOffer.toString());
		Assert.assertTrue(response.getBody() instanceof JobOffer);
	}

	
	@Test
	public void testBGetAllOffers() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/v1/jobOffers",
		HttpMethod.GET, entity, String.class);
		System.out.println("Check not empty list Offers: " +response.getBody());
		Assert.assertNotNull(response.getBody());
	}
	
	@Test
	public void testCCreateJobApplication() {
		HttpHeaders headers = new HttpHeaders();
		JobApplication jobApplication = new JobApplication();
		jobApplication.setRelatedOffer("Senior Developer");
		jobApplication.setCandidateEmail("prasad424jntu@gmail.com");
		jobApplication.setResumeText("Nagendra Gandla Has 10 years of experience as Full Stack Developer and DevOps Engineer.");
		jobApplication.setAppStatus(ApplicationStatus.APPLIED);
		HttpEntity<JobApplication> entity = new HttpEntity<JobApplication>(jobApplication, headers);
		ResponseEntity<JobApplication> response = restTemplate.exchange(getRootUrl() + "/api/v1/jobApplications",
		HttpMethod.POST, entity, JobApplication.class);
		JobApplication resultJobApp =response.getBody();
		System.out.println("Created Job Application: "+resultJobApp.toString());
		Assert.assertTrue(response.getBody() instanceof JobApplication);
	}
	
	@Test
	public void testDGetAllJobApplications() {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/v1/jobApplications",
		HttpMethod.GET, entity, String.class);
		System.out.println("Check not empty list Applications: " +response.getBody());
		Assert.assertNotNull(response.getBody());
	}

}
