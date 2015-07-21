package com.booking;


import java.awt.AWTException;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.booking.pages.*;


public class TestClass extends com.tools.Framework{
	
WebDriver driver;

	
	@BeforeMethod
	public void start() throws InterruptedException
	{
		driver=startDriver();
		driver.get("http://www.booking.com");
		Thread.sleep(2000);
		setDefault();
	}
	
//	@Test (priority = 1)
//		public void firstTest() throws InterruptedException, AWTException, IOException  {
//		 PageFactory.initElements(driver, MainPage.class)
//		 .logInAs("User1")
//		 .areTheCredsCorrect();
//		
//		Assert.assertTrue(LogedInMain.isUserLogedIn, "Credentials from file and the ones on WebSite do not match");
//	}	
//	
//
//	@Test(priority =2)
//	public void secondTest() throws InterruptedException, AWTException{
//		
//		PageFactory.initElements(driver, MainPage.class)
//		.setSearchLine("Monteneg")
//		.setCheckInDate("22","March")
//		.setCheckOutDate("30","March")
//        .search()
//	    .checkPopularHotelsFor("Hotel Montenegro");
//		
//		Assert.assertTrue(ResultPage.isInList, "Searched hotel was not found in the list");
//}
//	@Test(priority = 3)
//	public void thirdTest() throws InterruptedException, AWTException{
//		
//		PageFactory.initElements(driver, MainPage.class)
//		.setSearchLine("Monteneg")
//		.setCheckInDate("22","March")
//		.setCheckOutDate("30","March")
//        .search()
//        .goToPopularHotel("Hotel Montenegro")
//        .checkForWiFi() 
//        .checkForParking(); 
//		
//		Assert.assertTrue(HotelPage.wiFi && HotelPage.parking, "There is no Wifi or Parking");
//	}
//	@Test(priority = 4)
//	public void forthTest() throws InterruptedException, AWTException, IOException{
//		PageFactory.initElements(driver, MainPage.class)
//		.logInAs("User1")
//		.goToDestinationTips()
//		.setSerchFor("Lviv")
//		.search()
//		.checkForResults();
//		
//		Assert.assertTrue(DestinationSearchResults.result, "There were no search results");
//	}
	@Test(priority = 5)
	public void fifthTest() throws InterruptedException, IOException {
		PageFactory.initElements(driver, MainPage.class)
		.saveVisibleTowns()
		.countTownsInFile();
		
		Assert.assertTrue(MainPage.visibleTowns==MainPage.numberOfTownsInFile, "Number of visible destination is not the same as in file");
		
	}
	
       @AfterMethod            
       public void drQuit(){
    	   driver.quit();   
       }
       
	   @AfterClass
     	public void tearDown(){
		
	   }
}
