package com.booking.pages;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

	WebDriver driver;
	public static Boolean isInList;
    public static String windowHandle;
	@FindBy(xpath = ".//*[contains(text(),'Popular')]/..//a[contains(@href,'/hotel/me/')]") private List<WebElement> popular; //table

	public ResultPage(WebDriver driver) {
		this.driver = driver;
	
	}

	public ResultPage checkPopularHotelsFor(String q) { //after search we can get two different webPages...
		isInList = false;
		   //System.out.println("checkPopulrHotelFor method stars");
			
			for(WebElement item: popular){
			
				if(item.getText().contains(q)){
					isInList = true;
					System.out.println(item.getText()+ " is In the list of popular Hotels");
					System.out.println("");
				    break;
				}
				
			}
		return this;
	}

	public HotelPage goToPopularHotel(String hotelName) throws InterruptedException {
		windowHandle=driver.getWindowHandle();
		System.out.println(windowHandle+"This is your active window in goToPopularHotel method");
		for(WebElement item: popular){
			if(item.getText().contains(hotelName)){
					
				    item.click();
				    break;
				}
		
		
		}
		Thread.sleep(2000);
		return PageFactory.initElements(driver, HotelPage.class);
		
	}

}
