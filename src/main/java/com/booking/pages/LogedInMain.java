package com.booking.pages;

import org.openqa.selenium.WebDriver;

import com.booking.data.Data;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogedInMain {

	WebDriver driver;
	public static Boolean isUserLogedIn;
	//
	@FindBy(xpath=".//*[@id='current_account']/a/span[2]" ) private WebElement userFirstName;
	@FindBy(xpath=".//*[@id='current_account']/a/span[3]") private WebElement userLastName;
	//
	@FindBy(xpath=".//*[@id='header_dsf_link']/a") private WebElement desinationFinder;
	@FindBy(xpath=".//*[@id='header_dsf_link']//dd[contains(@class,'dsf_banner_awareness_index_cta')]") private WebElement newDestination;
	
	
public LogedInMain(WebDriver driver) {
      this.driver=driver;
      }

public LogedInMain areTheCredsCorrect() {
	isUserLogedIn = false;
	
	if(Data.currentUserFirstName.equals(userFirstName.getText()) &&
	Data.currentUserLaseName.equals(userLastName.getText())){
		isUserLogedIn = true;
	}
	System.out.println("statment that user is logged in is :"+isUserLogedIn);
	
	return this;
}

public Destination goToDestinationTips() throws InterruptedException {
	desinationFinder.click();
	Thread.sleep(500);
	newDestination.click();
	return PageFactory.initElements(driver, Destination.class);
}	
	
	
}
