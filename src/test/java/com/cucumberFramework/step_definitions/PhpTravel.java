package com.cucumberFramework.step_definitions;

import com.cucumberFramework.pages.TravelPage;
import com.cucumberFramework.utilities.Driver;
import io.cucumber.java.en.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.*;
import java.time.Duration;

public class PhpTravel {
TravelPage travelPage = new TravelPage();

WebDriver driver = com.cucumberFramework.utilities.Driver.get();

private Workbook workbook;
private Sheet sheet;
private Row row;
private Cell cell;
private int currentRow;
String filePath = "/home/ender/IdeaProjects/CucumberFrameWrok/src/test/java/com/cucumberFramework/step_definitions/data.xlsx";
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

		FileOutputStream outputStream = new FileOutputStream("/home/ender/IdeaProjects/CucumberFrameWrok/src/test/java/com/cucumberFramework/step_definitions/data.xlsx");
		workbook.write(outputStream);
		outputStream.close();
		workbook.close();
		Assert.assertTrue(true);
	} else {
		setCellValue("fail");
		FileOutputStream outputStream = new FileOutputStream("/home/ender/IdeaProjects/CucumberFrameWrok/src/test/java/com/cucumberFramework/step_definitions/data.xlsx");
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
@Given("I am on the hotel search page")
public void i_am_on_the_hotel_search_page() {
	driver.findElement(By.id("hotels-tab")).click();
	//wait
	utilities.BrowserUtils.waitFor(2);
}

@When("I enter {string} as the destination")
public void i_enter_as_the_destination(String destination) {

	driver.findElement(By.id("select2-hotels_city-container")).click();
// Locate the autocomplete element
	WebElement autocomplete = driver.findElement(By.xpath("//input[@class='select2-search__field']"));

// Click on the element to activate the dropdown menu
	autocomplete.click();

// Wait for the dropdown menu to appear
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("select2-results")));

// Locate the search box within the dropdown menu
	WebElement searchBox = driver.findElement(By.className("select2-search__field"));

// Type the text that you want to search for in the search box
	searchBox.sendKeys(destination);
	String des= "'".concat(destination).concat("'");
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[contains(text()," + des + ")]")));

	System.out.println(des);
// Locate the desired search result
	WebElement searchResult = driver.findElement(By.xpath("//li[contains(text()," + des + ")]"));

// Click on the desired search result to select it
	searchResult.click();

	//wait
	utilities.BrowserUtils.waitFor(2);


}


@When("I enter {string} as the check-in date")
public void i_enter_as_the_check_in_date(String date) {
	int i = date.indexOf(" ");
	// find the check-in date field and click it to open the date picker
	WebElement checkinField = driver.findElement(By.name("checkin"));
	checkinField.click();
	// find the month and year switcher and click it until we reach May 2023
	WebElement monthYearSwitcher = driver.findElement(By.xpath("(//th[@class='switch'])[1]"));
	while (!monthYearSwitcher.getText().contains(date.substring(0, i))) {
		driver.findElement(By.cssSelector("th.next")).click();
	}

	// find the day and click it
	WebElement checkinDate = driver.findElement(By.xpath("//td[text()=" + date.substring(i + 1) + "]"));
	checkinDate.click();
	utilities.BrowserUtils.waitFor(2);
	driver.findElement(By.xpath("(//*[@class='label-text'])[1]")).click();
	utilities.BrowserUtils.waitFor(2);


}

@When("I enter {string} as the check-out date")
public void i_enter_as_the_check_out_date(String date) {
	int i = date.indexOf(" ");
	// find the check-in date field and click it to open the date picker
	WebElement checkoutField = driver.findElement(By.id("checkout"));
	checkoutField.click();
	// find the month and year switcher and click it until we reach May 2023
	WebElement monthYearSwitcher = driver.findElement(By.xpath("(//th[@class='switch'])[4]"));
	while (!monthYearSwitcher.getText().contains(date.substring(0, i))) {
		driver.findElement(By.cssSelector("th.next")).click();
	}
String date1= "'".concat(date.substring(i+1)).concat("'");
	// find the day and click it
	WebElement checkout = driver.findElement(By.xpath("(//td[text()="+date1+"])[3]"));
	checkout.click();
	utilities.BrowserUtils.waitFor(2);

}

@When("I click on the search button")
public void i_click_on_the_search_button() {
	driver.findElement(By.id("submit")).click();
	utilities.BrowserUtils.waitFor(2);
}

@Then("I should see a list of available hotels in New York")
public void i_should_see_a_list_of_available_hotels_in_new_york() {
	String actualUrl = driver.getCurrentUrl();
	if (actualUrl.contains("hotels")) {
		Assert.assertTrue(true);
	} else {
		Assert.fail();
	}

}
}