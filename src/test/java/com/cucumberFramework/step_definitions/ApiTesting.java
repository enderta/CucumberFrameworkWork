package com.cucumberFramework.step_definitions;

import com.cucumberFramework.utilities.Driver;
import io.cucumber.java.it.Ma;
import io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;

public class ApiTesting {
    @Test
    public void test() {
       //baseURI restasured
        baseURI="https://api.spacexdata.com/v5/launches/latest";
        Response response = given().accept("application/json").
                when().get();
        JsonPath jsonPath = response.jsonPath();
        Map<String,Object> map = jsonPath.getMap("$");
System.out.println(map.get("crew").toString());



    }
    @Test
    public void test2() {
       Driver.get().get("https://www.google.com");
        System.out.println(Driver.get().getTitle());
        Driver.closeDriver();



    }
}
