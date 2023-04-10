package com.cucumberFramework.pages;

import com.cucumberFramework.utilities.Driver;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.*;

public class TravelPage {
public TravelPage() throws FileNotFoundException {
	PageFactory.initElements(Driver.get(), this);
}
@FindBy(id = "ACCOUNT")
public WebElement account;

@FindBy(xpath = "//a[.='Customer Login']")
public WebElement customerLogin;

@FindBy(name = "email")
public WebElement email;

@FindBy(name = "password")
public WebElement password;

@FindBy(xpath = "//button[.='Login']")
public WebElement login;

public void login(String email, String password) {
	this.email.sendKeys(email);
	this.password.sendKeys(password);
	login.click();

}

	private static Workbook workbook;
	private static Sheet sheet;
	private static Row currentRow;


	public static FileInputStream file(String filePath) throws IOException,FileNotFoundException {
		File file = new File(filePath);
		FileInputStream inputStream = new FileInputStream(file);
		return inputStream;

	}

public static String cellValue(String row, String column,String path) throws IOException {
	FileInputStream file = file(path);
	workbook = new XSSFWorkbook(file);
	sheet = workbook.getSheetAt(0);
	int rowIndex = Integer.parseInt(row);
	Row row1 = sheet.getRow(rowIndex);
	int columnIndex = Integer.parseInt(column);
	Cell cell = row1.getCell(columnIndex);
	String value = cell.toString();
	currentRow = row1;
	return value;
}
public static FileOutputStream fileOut(String filePath) throws IOException {
	File file = new File(filePath);
	FileOutputStream fileOut = new FileOutputStream(file);
	return fileOut;
}
public static void setCellValue(String value, String path) throws IOException {

		Cell cell = currentRow.createCell(2);
		cell.setCellValue(value);
		FileOutputStream fileOut = fileOut(path);
		workbook.write(fileOut);
		fileOut.close();
		workbook.close();


}

}