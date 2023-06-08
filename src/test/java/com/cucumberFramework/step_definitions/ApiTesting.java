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



            public static String run(String[] product_names, int[] quantities, int[] prices, float discount, float delivery_fee) {
                /*
                 * Write your code below; return type and arguments should be according to the problem's requirements
                 */


                String output = "";
        for (int i = 0; i < product_names.length; i++) {
            output += product_names[i] + " " + quantities[i] + " " + prices[i] + " " + discount + " " + delivery_fee + " " + (quantities[i] * prices[i] * (1 - discount / 100) + delivery_fee) + "\n";
        }
                return output;
            }






    @Test
public void test2() {
    /*
    * product_names = ["Apples", "Oranges", "Bananas"]
quantities = [5, 3, 10]
prices = [1, 2, 1]
discount = 10
delivery_fee = 5
*
* output = "Apples: 9.50\nOranges: 10.40\nBananas: 14.00"
    * */

    String[] product_names = {"Apples", "Oranges", "Bananas"};
    int[] quantities = {5, 3, 10};
    int[] prices = {1, 2, 1};
    float discount = 10;
    float delivery_fee = 5;
    String output = "";
    //output = "Apples: 9.50\nOranges: 10.40\nBananas: 14.00" should be the output
    for (int i = 0; i < product_names.length; i++) {
        output += product_names[i]+" " +(quantities[i] * prices[i] * (1 - discount / 100) + delivery_fee) + "/n";
    }
    System.out.println(output);

}}
