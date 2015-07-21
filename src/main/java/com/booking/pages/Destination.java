package com.booking.pages;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class Destination {

	
	WebDriver driver;
	@FindBy(xpath = ".//input[contains(@id,'search')]") private WebElement searchField;
	@FindBy(xpath = ".//*[@id='ac_destinations']/div/div[2]") private WebElement searchSecondOption;
	
	@FindBy(xpath = ".//*[@id='dsf_button']") private WebElement submitButton;

	public Destination(WebDriver driver) {
		this.driver=driver;
	}

	public Destination setSerchFor(String q) throws InterruptedException {
		
		searchField.clear();
		searchField.sendKeys(q);
		Thread.sleep(500);
		searchSecondOption.click();
		return this;
		
	}

	public DestinationSearchResults search() throws InterruptedException {
		submitButton.click();
		Thread.sleep(2000);
		return PageFactory.initElements(driver, DestinationSearchResults.class);
	}
}
