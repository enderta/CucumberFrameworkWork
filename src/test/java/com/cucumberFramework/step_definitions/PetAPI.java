package com.cucumberFramework.step_definitions;

import io.cucumber.java.en.*;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
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
    public void i_have_the_following_pet_data(io.cucumber.datatable.DataTable dataTable) {
        // Write code here that turns the phrase above into concrete actions
        // For automatic transformation, change DataTable to one of
        // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
        // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
        // Double, Byte, Short, Long, BigInteger or BigDecimal.
        //
        // For other transformations you can register a DataTableType.
        throw new io.cucumber.java.PendingException();
    }
    @When("I send a POST request to {string} with the provided pet data")
    public void i_send_a_post_request_to_with_the_provided_pet_data(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @Then("the response should contain the created pet details")
    public void the_response_should_contain_the_created_pet_details() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}
