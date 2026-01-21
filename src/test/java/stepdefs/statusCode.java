package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import utils.APIClient;
import utils.scenarioContext;
import io.restassured.response.Response;
import io.cucumber.datatable.DataTable;


import static org.hamcrest.Matchers.is;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.given;



public class statusCode {
   
    private final scenarioContext ScenarioContext;

    public statusCode(scenarioContext ScenarioContext){
        this.ScenarioContext=ScenarioContext;
    }


    @Given("the API request is initialized")
    public void the_api_request_is_initialised(){
        ScenarioContext.setRequest(RestAssured.given());
    }

    @Given("I set headers:")
    public void set_request_header(DataTable table){
       Map<String, String> headers = table.asMap();
        ScenarioContext.getRequest().headers(headers);
    }
     @Given("I authenticate using basic auth with username {string} and password {string}")
    public void set_username_password(String username, String password){
        ScenarioContext.getRequest().auth().preemptive().basic(username, password);
       
    }

    @When("user sends a {string} request to {string} endpoint")
    public void user_send_the_request(String method, String endpoint){
         ScenarioContext.getRequest().log().all();
        Response response=APIClient.sendRequest(method,
            endpoint,
            ScenarioContext.getRequest()
        );
       
        ScenarioContext.setResponse(response);
    }

    @Then("the response status code should be {int}")
    public void status_code_should_be(int status){
        assertThat(ScenarioContext.getResponse().statusCode(), is(status));
    }



}
