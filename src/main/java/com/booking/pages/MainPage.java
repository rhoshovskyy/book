package com.booking.pages;


import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.booking.data.Data;
import com.tools.Framework;

import java.awt.AWTException;



public class MainPage {

	WebDriver driver;
	public static int numberOfTownsInFile;
	public static int visibleTowns;
	//all this locators i would like to get into another class but in what way should i access them and where this class should be
	@FindBy (xpath=".//*[@id='current_account']/a/span") private WebElement logInButton;
	//logIn fields
	@FindBy (xpath="//div[contains(@style,\"visible\")]//div[contains(@class,\"shown\")]//input[@name=\"username\"]") private WebElement emailField;
	@FindBy (xpath=".//div[contains(@class,'user-access-menu-lightbox--user-center')]//div[contains(@class,'form-shown-section')]//*[contains(@class,'user_signup_password') and @name='password']")private WebElement passField;
	@FindBy (xpath = ".//div[contains(@class,'user-access-menu-lightbox--user-center')]//div[contains(@class,'form-shown-section')]//input[@type='submit']") private WebElement signInButton;
	//search block fields
	@FindBy (id="destination") private WebElement searchField;//
	@FindBy (xpath=".//fieldset//select[@name=\"checkin_monthday\"]") private WebElement inDayDD;//
	@FindBy (xpath=".//fieldset//select[@name=\"checkin_year_month\"]") private WebElement inMonthDD;//
	@FindBy (xpath=".//fieldset//select[@name=\"checkout_monthday\"]") private WebElement outDayDD;//
	@FindBy (xpath=".//fieldset//select[@name=\"checkout_year_month\"]") private WebElement outMonthDD;//
	@FindBy (xpath=".//fieldset//button")private WebElement submitButton;//
	//
	@FindBy (xpath=".//*[@id='popularDestinations']//h3/a") private List<WebElement> townList;
		
	public MainPage(WebDriver driver) {
		
		this.driver=driver;
	}

	public LogedInMain logInAs(String user) throws InterruptedException, AWTException, IOException {
		

		String creds[] = getCreds(user);
		
		logInButton.click();
		emailField.sendKeys(creds[0]);
		passField.sendKeys(creds[1]);
		signInButton.click();
		Thread.sleep(4000);

		return PageFactory.initElements(driver, LogedInMain.class);
		
	}
	private String[] getCreds(String searchedUser) throws IOException{
		 
	    Framework a = new Framework();
		String stringFromFile = a.readFileAsString(com.booking.data.Data.usersFilePath);
		//		System.out.println("String From file is :"+stringFromFile);
		
		String[] ary =stringFromFile.split(",");
		int index = -1;
		index = Arrays.asList(ary).indexOf(searchedUser);
		//
        //		System.out.println("converted array lenth is : "+ary.length);
        //		System.out.println("Search for user: "+ name);
		//
		String[] newArr = new String[2];
     	//
		if(index >-1){
		newArr[0]=ary[index+1];
		newArr[1]=ary[index+2];
		
		com.booking.data.Data.currentUserFirstName = ary[index+3];
		com.booking.data.Data.currentUserLaseName = ary[index+4];
		//		System.out.println("Searched user has index of: "+index+" and his log,pass indexes are: "+index+1+","+index+2);
		}
		else{
			System.out.println("no Such name");
		}
	return newArr;
	 
 }
	public MainPage setSearchLine(String q) throws InterruptedException, AWTException {
		searchField.clear();
		searchField.sendKeys(q);
		Thread.sleep(1000);
		driver.findElement(By.xpath(".//ul/li[@class='ui-menu-item'][1]/a")).click(); // first element in search autoDD
		Thread.sleep(1000);
		
		
		
	return this;
		
	}
	public MainPage setCheckInDate(String day, String month) throws InterruptedException {
		Select dayDD = new Select(inDayDD);
		Select monthDD = new Select(inMonthDD);
		List <WebElement> allDayOptions = dayDD.getOptions();
		List <WebElement> allMonthOptions = monthDD.getOptions();
		   
		for(WebElement item: allDayOptions){

			if(item.getText().contains(day)){
				dayDD.selectByVisibleText(item.getText());
				break;
			}
		}
		   for(WebElement it: allMonthOptions){
			   	if(it.getText().contains(month)){
				   monthDD.selectByVisibleText(it.getText());
				   break;
				}
		   }
				Thread.sleep(1000);
				return this;
	}
	public MainPage setCheckOutDate(String day, String month) throws InterruptedException {
		  
	Select dayDD = new Select(outDayDD);
	Select monthDD = new Select(outMonthDD);
	List <WebElement> allDayOptions = dayDD.getOptions();
	List <WebElement> allMonthOptions = monthDD.getOptions();
			   
		   for(WebElement item: allDayOptions){

				if(item.getText().contains(day)){
					dayDD.selectByVisibleText(item.getText());
					break;
				}
		   }
		   for(WebElement it: allMonthOptions){
			   
			   if(it.getText().contains(month)){
				   monthDD.selectByVisibleText(it.getText());
				   break;
				}
		   }
				Thread.sleep(1000);
		   
	return this;
	}
	public ResultPage search() throws InterruptedException {
		submitButton.click();
		Thread.sleep(5000);
	return PageFactory.initElements(driver, ResultPage.class);
	}

	public MainPage saveVisibleTowns() {
		  
		    visibleTowns=townList.size();
	        //Creating new workbook
			 XSSFWorkbook workbook = new XSSFWorkbook();
			//creating a blank sheet
			 XSSFSheet sheet =workbook.createSheet("Towns");

			 int rowNum=0;
			 for(WebElement item: townList){
				 Row r=sheet.createRow(rowNum++);
			      int cellIndex = 0;
				 r.createCell(cellIndex++).setCellValue(item.getText());
			 }
				 
				 try {
					FileOutputStream out = new FileOutputStream(Data.TownsPath);
					workbook.write(out);
					out.close();
					//System.out.println("savedDraftToFile method passed");
				} catch (IOException e) {
					e.printStackTrace();
				}
		
		System.out.println("There are "+ visibleTowns+" visible towns on the page");
		return this;
		
	}

	public MainPage countTownsInFile() throws IOException {
		
		
		
		try {
            FileInputStream file = new FileInputStream(new File(Data.TownsPath));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            
            numberOfTownsInFile=0;
       
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    numberOfTownsInFile++;
//                    switch (cell.getCellType()) {
//                        case Cell.CELL_TYPE_NUMERIC:
//                            System.out.print(cell.getNumericCellValue() + " t");
//                            break;
//                        case Cell.CELL_TYPE_STRING:
//                            System.out.print(cell.getStringCellValue() + " t");
//                            break;
//                    }
                }
            }
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		//System.out.println("number of Towns in file is: "+numberOfTownsInFile);
		System.out.println("There are "+numberOfTownsInFile+ " towns in the file");
		return this;

		
	}
	
}
