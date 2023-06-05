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
   static String petId;
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
        System.out.println("map = " + map);
        Object id = map.get("id");
        System.out.println("id = " + id);
        petId = id.toString();
        System.out.println("petId = " + petId);
        Assert.assertEquals(name, map.get("name"));
        Assert.assertEquals(status, map.get("status"));
        Assert.assertEquals(category1, ((Map<?, ?>) map.get("category")).get("name"));



    }
    @When("I send a POST request to {string} with the provided pet data")
    public void i_send_a_post_request_to_with_the_provided_pet_data(String string) {
        System.out.println("id: "+petId);
    }
    @Then("the response should contain the created pet details")
    public void the_response_should_contain_the_created_pet_details() {
        System.out.println("the response should contain the created pet details");

    }
 Response response1;
 @Given("an existing pet with ID {int}")
 public void an_existing_pet_with_id(Integer int1) {
 // int1=Integer.parseInt(petId);
  response1 = given().headers("Content-Type", "application/json").
          when().get("/pet/" + "9223372036854775807");








 }
 Map<String, Object> map;
 Map<String, Object> requestBody;
 @Given("I have the following updated pet data:")
 public void i_have_the_following_updated_pet_data(List<Map<String,Object>> dataTable) {
    String name = dataTable.get(0).get("name").toString();
    String status = dataTable.get(0).get("status").toString();
  JsonPath jsonPath = response1.jsonPath();
  map = jsonPath.getMap("$");
   requestBody = new HashMap<>();
  requestBody.put("id", map.get("id"));
  Map<String, Object> category = new HashMap<>();
  category.put("id", 0);
  category.put("name", map.get("category"));
  requestBody.put("category", category);

  requestBody.put("name", map.get("name"));

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
  //Assert.assertEquals(name, map.get("name"));

 }
 Response put;
 @When("I send a PUT request to {string} with the updated pet data")
 public void i_send_a_put_request_to_with_the_updated_pet_data(String string) {
  put=given().headers("Content-Type", "application/json").
          body(requestBody).
          when().put("/pet"+"/"+"9223372036854775807");
 }
 @Then("the response should contain the updated pet details")
 public void the_response_should_contain_the_updated_pet_details() {
  put.prettyPeek();
    JsonPath jsonPath = put.jsonPath();
    Map<String, Object> map = jsonPath.getMap("$");
    System.out.println("map = " + map);
    Object id = map.get("id");
    System.out.println("id = " + id);
    petId = id.toString();
    System.out.println("petId = " + petId);
    Assert.assertEquals(requestBody.get("name"), map.get("name"));
    Assert.assertEquals(requestBody.get("status"), map.get("status"));
    Assert.assertEquals(requestBody.get("category"), ((Map<?, ?>) map.get("category")).get("name"));

 }


}
