package com.cucumberFramework.step_definitions;

import com.cucumberFramework.utilities.Driver;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.util.List;

public class Lab {
    @Given("I am on the registration page of lab-automation-practice website")
    public void i_am_on_the_registration_page_of_lab_automation_practice_website() {
        Driver.get().get("https://www.saucedemo.com/");
    }
    @When("I enter the following details to fill the registration form:")
    public void i_enter_the_following_details_to_fill_the_registration_form(List<List<String>> dataTable) {
        System.out.println(dataTable);
        Driver.get().findElement(By.id("user-name")).sendKeys(dataTable.get(1).get(0));
        Driver.get().findElement(By.id("password")).sendKeys(dataTable.get(1).get(1));



    }
    @When("click on the register button on the registration page")
    public void click_on_the_register_button_on_the_registration_page() {
        Driver.get().findElement(By.id("login-button")).click();
    }
    @Then("I should be taken to the registration confirmation page")
    public void i_should_be_taken_to_the_registration_confirmation_page() {
        String currentUrl = Driver.get().getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("inventory"));
    }

}
