package com.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.booking.data.Data;
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
	
	public void saveListToExel(List<WebElement> elements,String saveDirectory){
		
		//Creating new workbook
		 XSSFWorkbook workbook = new XSSFWorkbook();
		//creating a blank sheet
		 XSSFSheet sheet =workbook.createSheet("Towns");

		 int rowNum=0;
		 for(WebElement item: elements){
			 Row r=sheet.createRow(rowNum++);
		      int cellIndex = 0;
			 r.createCell(cellIndex++).setCellValue(item.getText());
		 }
			 
			 try {
				FileOutputStream out = new FileOutputStream(saveDirectory);
				workbook.write(out);
				out.close();
				//System.out.println("savedDraftToFile method passed");
			} catch (IOException e) {
				e.printStackTrace();
			}
	

		
	}
	public int countExcelRows(String filepath){
		int number = 0;
		try {
            FileInputStream file = new FileInputStream(new File(Data.townsPath));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            
            
       
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    number++;
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
		
		return number;
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
