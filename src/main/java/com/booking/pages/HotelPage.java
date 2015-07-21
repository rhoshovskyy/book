package com.booking.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HotelPage {

	WebDriver driver;
	@FindBy (xpath=".//span[contains(@class,'hp_usp_filter_api_text')][contains(text(),'WiFi')]") private List<WebElement> freeWiFi;
	@FindBy (xpath=".//span[contains(@class,'hp_usp_filter_api_text')][contains(text(),'parking')]") private List<WebElement> freeParking;
	
	public static Boolean wiFi;
	public static Boolean parking;
	
	public HotelPage(WebDriver driver) {
		this.driver=driver;
		
	}
	public HotelPage checkForWiFi() throws InterruptedException {
		
		
	//	System.out.println(driver.getWindowHandles().size()+" windows are opened");
		
		if(driver.getWindowHandles().size()>1){
			System.out.println("there are tow windows opened");
			driver.close();
			System.out.println("we just closed one of them");
			System.out.println("");
			for(String winHandle : driver.getWindowHandles()){
				driver.switchTo().window(winHandle);
			}
		}
		
		System.out.println(driver.getWindowHandle()+"is your active window");
		
		Thread.sleep(1000);
		wiFi = freeWiFi.size()>0;
		System.out.println("do we have free Wifi?: "+ wiFi);
		return this;
	}
	
public HotelPage checkForParking() throws InterruptedException {
	
	if(driver.getWindowHandles().size()>1){
		System.out.println("there are tow windows opened");
		driver.close();
		System.out.println("we just closed one of them");
		System.out.println("");
		for(String winHandle : driver.getWindowHandles()){
			driver.switchTo().window(winHandle);
		}
	}
	
		Thread.sleep(1000);
		parking = freeParking.size()>0;
		System.out.println("do we have free parking?: "+ parking);
		return this;
	}
	
}
