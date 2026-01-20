package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import utils.APIClient;
import utils.scenarioContext;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.is;
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

    @When("user sends a {string} request to {string} endpoint")
    public void user_send_the_request(String method, String endpoint){
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
