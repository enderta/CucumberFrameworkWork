package com.cucumberFramework.step_definitions;

import io.cucumber.java.en.*;
import io.cucumber.java.it.Ma;
import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
//import restassured baseURI

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class SpaceX {


    Response response;
    @Given("I have a valid access to the SpaceX API")
    public void i_have_a_valid_access_to_the_space_x_api() {
       RestAssured.baseURI="https://api.spacexdata.com/v3";

    }
    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
      //  System.out.println(baseURI+endpoint);
        response = given().accept("application/json").
                when().get(endpoint);
        //response.prettyPeek();


    }
    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(Integer int1) {

        response.then().statusCode(int1);
    }
    @Then("the response body should contain the following information:")
    public void the_response_body_should_contain_the_following_information(Map<String,Object> dataTable) {

        JsonPath jsonPath = response.jsonPath();
        Map<String,Object> map = jsonPath.getMap("");
        List<String> keys = new ArrayList<>(dataTable.keySet());
        List<String> values = new ArrayList<>();
        for (int i = 1; i < keys.size(); i++) {
            values.add(map.get(keys.get(i)).toString());
        }

       List<String> ls=new ArrayList<>();
        for (int i = 1; i < keys.size(); i++) {
            ls.add(map.get(keys.get(i)).toString());
        }
        System.out.println(ls);
        System.out.println(values);
        assert ls.equals(values);
    }

    @When("I send a GET request to {string} with query parameter {string} as {string}")
    public void i_send_a_get_request_to_with_query_parameter_as(String string, String string2, String string3) {
        response=given().accept("application/json").queryParams(string2,string3).when().get(string);
    }
    @Then("the response body should contain at least one launch entry")
    public void the_response_body_should_contain_at_least_one_launch_entry() {
        response.prettyPeek();
       Assert.assertTrue(response.jsonPath().getList("").size()>0);
    }
    @Then("each launch entry should have the following information:")
    public void each_launch_entry_should_have_the_following_information(Map<String,Object> dataTable) {
       JsonPath jsonPath = response.jsonPath();
         List<Map<String,Object>> list = jsonPath.getList("$");
        System.out.println(list.get(0));
        List<String> keys = new ArrayList<>(dataTable.keySet());
        System.out.println(keys);
        List<String> values = new ArrayList<>();
        for (int i = 1; i < keys.size(); i++) {
            values.add(list.get(0).get(keys.get(i)).toString());
        }

        List<String> ls=new ArrayList<>();
        for (int i = 1; i < keys.size(); i++) {
            ls.add(list.get(0).get(keys.get(i)).toString());
        }
        System.out.println(ls);
        System.out.println(values);
        assert ls.equals(values);
    }

}
