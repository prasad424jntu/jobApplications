package com.mycompany.jobapplications.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.mycompany.jobapplications.bean.JobApplication;
import com.mycompany.jobapplications.exceptions.ApplicationStatusNotFound;
import com.mycompany.jobapplications.exceptions.DuplicateApplication;
import com.mycompany.jobapplications.exceptions.JobApplicationNotFound;
import com.mycompany.jobapplications.utils.ApplicationStatus;
import com.mycompany.jobapplications.utils.Utils;

@Component
public class ApplicationRepository {
	
	List<JobApplication> jobapplications = new ArrayList<>();

	public List<JobApplication> getJobapplications() {
		return jobapplications;
	}

	public void setJobapplications(List<JobApplication> jobapplications) {
		this.jobapplications = jobapplications;
	}
	
	public JobApplication saveJobApplication(JobApplication jobapplication) throws DuplicateApplication {
		if(this.getJobapplications().contains(jobapplication)) {
			throw new DuplicateApplication("Duplicate Exception Added.");
		}else {
			this.getJobapplications().add(jobapplication);
		}
		return jobapplication;
	}

	public JobApplication updateJobApplication(String candidateEmail, String appStatus) throws ApplicationStatusNotFound,JobApplicationNotFound{
		for (JobApplication jobApplication : getJobapplications()) {
			if(jobApplication.getCandidateEmail().equals(candidateEmail)) {
				jobApplication.setAppStatus(Utils.getStatus(appStatus));
				return jobApplication;
			}
		}
		throw new JobApplicationNotFound("");
	}
}