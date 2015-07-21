package com.booking.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DestinationSearchResults {

	WebDriver driver;
	public static boolean result;
	
	@FindBy (xpath=".//div[contains(@class,'dsf-interests-title')][@data-component][not(contains(@class,'hidden'))]")private List<WebElement> resultBaner;//
	
	public DestinationSearchResults(WebDriver driver) {
	this.driver=driver;
	}

	public DestinationSearchResults checkForResults() {
		
		result=resultBaner.size()>0;
		//System.out.println("is result banner present? : "resultBaner.size()>0);
		
		
		return this;
		
	}

	public DestinationSearchResults search() {
		// TODO Auto-generated method stub
		return null;
	}

	}

