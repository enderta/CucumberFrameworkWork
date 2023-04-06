package com.cucumberFramework.step_definitions;

import com.cucumberFramework.pages.TravelPage;
import io.cucumber.java.en.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class PhpTravel {
TravelPage travelPage = new TravelPage();

WebDriver driver = com.cucumberFramework.utilities.Driver.get();

private Workbook workbook;
private Sheet sheet;
private Row row;
private Cell cell;
private int currentRow;
String filePath = "/home/ender/IdeaProjects/EU9-BookIT/src/test/java/com/cucumberFramework/step_definitions/data.xlsx";
File file = new File(filePath);
FileInputStream inputStream = new FileInputStream(file);

public PhpTravel() throws FileNotFoundException {
}

@Given("I am on the home page")
public void i_am_on_the_home_page() {
	driver.get("https://phptravels.net");
}

@When("I enter {string} and {string} in the {string} row")
public void i_enter_and_in_the_row(String email, String pass, String ind) throws IOException {
	workbook = new XSSFWorkbook(inputStream);
	sheet = workbook.getSheetAt(0);
	int rowIndex = Integer.parseInt(ind);
	row = sheet.getRow(rowIndex);
	cell = row.getCell(0);
	email = cell.toString();
	System.out.println("username = " + email);
	cell = row.getCell(1);
	pass = cell.toString().substring(0, 6);
	System.out.println("password = " + pass);
	travelPage.login(email, pass);
	currentRow = rowIndex;
	System.out.println("currentRow = " + currentRow);


}

@Given("I am on the Login page")
public void i_am_on_the_login_page() {
	travelPage.account.click();
	travelPage.customerLogin.click();
}

@When("I click the Login button")
public void i_click_the_login_button() {
	utilities.BrowserUtils.waitFor(2);
}

@Then("I should be redirected to the Dashboard page")
public void i_should_be_redirected_to_the_dashboard_page() throws IOException {
	String actualUrl = driver.getCurrentUrl();
	if (actualUrl.contains("account")) {
		setCellValue("pass");

		FileOutputStream outputStream = new FileOutputStream("/home/ender/IdeaProjects/EU9-BookIT/src/test/java/com/cucumberFramework/step_definitions/data.xlsx");
		workbook.write(outputStream);
		outputStream.close();
		workbook.close();
		Assert.assertTrue(true);
	} else {
		setCellValue("fail");
		FileOutputStream outputStream = new FileOutputStream("/home/ender/IdeaProjects/EU9-BookIT/src/test/java/com/cucumberFramework/step_definitions/data.xlsx");
		workbook.write(outputStream);
		outputStream.close();
		workbook.close();
		Assert.fail();
	}
}

public void setCellValue(String value) {
	row = sheet.getRow(currentRow);
	cell = row.createCell(2);
	cell.setCellValue(value);

}

}