package com.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.booking.pages.*;


public class Framework {
	private WebDriver driver;
	public static String date= getDate();

	public WebDriver startDriver() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;

	}

	private static String getDate() {
		
			DateFormat df = new SimpleDateFormat("yyyy/MM/dd/HH:mm:ss");
			Date today = Calendar.getInstance().getTime();
			String reportDate = df.format(today);
			
			System.out.println(reportDate);
		
		return reportDate;
	}

	public void clean(){
		driver.manage().deleteAllCookies();
		
		
	}

	public String readFileAsString(String filePath) throws IOException {
        StringBuffer fileData = new StringBuffer();
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        char[] buf = new char[1024];
        int numRead=0;
        while((numRead=reader.read(buf)) != -1){
            String readData = String.valueOf(buf, 0, numRead);
            fileData.append(readData);
        }
        reader.close();
        return fileData.toString();
    }
	public void setDefault()
	{
		HotelPage.wiFi=false;
		HotelPage.parking=false;
		DestinationSearchResults.result=false;
		MainPage.visibleTowns=-1;
		MainPage.numberOfTownsInFile=0;
		DestinationSearchResults.result=false;
		LogedInMain.isUserLogedIn=false;
		ResultPage.isInList=false;
	}
	
	
	  
	
}
