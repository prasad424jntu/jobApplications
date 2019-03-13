package com.mycompany.jobapplications.utils;

import com.mycompany.jobapplications.exceptions.ApplicationStatusNotFound;

public class Utils {
	
	public static ApplicationStatus getStatus(String appStatus) throws ApplicationStatusNotFound{
		switch (appStatus.toUpperCase()) {
		case "REJECTED":
			return ApplicationStatus.REJECTED;
		case "INVITED":
			return ApplicationStatus.INVITED;
		case "HIRED":
			return ApplicationStatus.HIRED;
		case "APPLIED":
			return ApplicationStatus.APPLIED;

		default:
			throw new ApplicationStatusNotFound("");
		}
		
	}
	
	public static void sendNotification(String status, String jobTitle, String candidateEmail) throws ApplicationStatusNotFound {
		ApplicationStatus appStatus = getStatus(status);
		switch (appStatus) {
		case REJECTED:
			System.out.println("We regret to inform you that your Application status for the position of " + jobTitle + " by your registered candidate mail ID "
					 + candidateEmail + " is changed to " + appStatus);
		case INVITED:
			System.out.println("We are Happy to inform you that your Application status for the position of " + jobTitle + " by your registered candidate mail ID "
					 + candidateEmail + " is changed to " + appStatus);
		case HIRED:
			System.out.println("Congratulations.We are Happy to inform you that your Application status for the position of  " + jobTitle + " by your registered candidate mail ID "
					 + candidateEmail + " is changed to " + appStatus+ " .We share you onboarding formalities soon." );
		case APPLIED:
			System.out.println("Thank you. We received Your application for the position of " + jobTitle + " by your registered candidate mail ID "
					 + candidateEmail + " .We carefully review your application and will get back to you.");

		
		}
	}

}
