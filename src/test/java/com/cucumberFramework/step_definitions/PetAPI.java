package com.cucumberFramework.step_definitions;

import com.google.gson.Gson;
import io.cucumber.java.en.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

import java.util.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static io.restassured.http.Headers.headers;

public class PetAPI {
    @Given("the Petstore API is up and running")
    public void the_petstore_api_is_up_and_running() {
       baseURI="https://petstore.swagger.io/v2";
        ValidatableResponse apiKey = given().accept("application/json").
                headers("api_key", "special-key").
                when().get("/pet/10").
                then().statusCode(200);
        System.out.println(apiKey.extract().response().asString());

    }
    @Given("I have the following pet data:")
    public void i_have_the_following_pet_data(List<Map<String,Object>> dataTable) {

       String name = dataTable.get(0).get("name").toString();
        String status = dataTable.get(0).get("status").toString();
       String category1 = dataTable.get(0).get("category").toString();
        //Integer statusCode = Integer.parseInt(dataTable.get(0).get("statusCode").toString());
        Map<String,Object> bdy= dataTable.get(0);
        //serialize the body to json by using gson
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", 0);

        Map<String, Object> category = new HashMap<>();
        category.put("id", 0);
        category.put("name", category1);
        requestBody.put("category", category);

        requestBody.put("name", name);

        List<String> photoUrls = new ArrayList<>();
        photoUrls.add("string");
        requestBody.put("photoUrls", photoUrls);

        List<Map<String, Object>> tags = new ArrayList<>();
        Map<String, Object> tag = new HashMap<>();
        tag.put("id", 0);
        tag.put("name", "string");
        tags.add(tag);
        requestBody.put("tags", tags);

        requestBody.put("status", status);

        Response post = given().accept("application/json").
                headers("Content-Type", "application/json").
                body(requestBody).
                when().post("/pet");
        post.prettyPeek();

        JsonPath jsonPath = post.jsonPath();
        Map<String, Object> map = jsonPath.getMap("$");
        Assert.assertEquals(name, map.get("name"));
        Assert.assertEquals(status, map.get("status"));
        Assert.assertEquals(category1, ((Map<?, ?>) map.get("category")).get("name"));



    }
    @When("I send a POST request to {string} with the provided pet data")
    public void i_send_a_post_request_to_with_the_provided_pet_data(String string) {
        System.out.println("string = " + string);
    }
    @Then("the response should contain the created pet details")
    public void the_response_should_contain_the_created_pet_details() {
        System.out.println("the response should contain the created pet details");

    }

}
