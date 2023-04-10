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


}