package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import utils.APIClient;
import utils.scenarioContext;
import io.restassured.response.Response;
import io.cucumber.datatable.DataTable;

import java.util.Map;
import utils.configReader;

import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;




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

    @Given("user set path parameter {string} as {string}")
    public void set_path_param(String key, String value) {
        ScenarioContext.setRequest(
            ScenarioContext.getRequest().pathParam(key, value)
        );
    }

     @Given("I authenticate using basic auth with username {string} and password {string}")
    public void set_username_password(String username, String password){
        ScenarioContext.getRequest().auth().preemptive().basic(configReader.getProperty(username), configReader.getProperty(password));
       
    }
    @Given("I authenticate using digest auth with username {string} and password {string}")
    public void set_digest_username_password(String username, String password){
        ScenarioContext.getRequest().auth().digest(configReader.getProperty(username), configReader.getProperty(password));
       
    }
    @Given("I set bearer token {string}")
    public void set_bearer_token(String token){
        ScenarioContext.getRequest().header("Authorization", "Bearer " + configReader.getProperty("bearer.token"));
       
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

    @Then("response should match json schema {string}")
    public void response_should_match_json_schema(String schemaPath) {
        ScenarioContext.getResponse()
        .then()
        .assertThat()
        .body(matchesJsonSchemaInClasspath(schemaPath));
}

@Then("response headers should be:")
public void response_headers_should_be(DataTable table) {

    Map<String, String> expectedHeaders = table.asMap(String.class, String.class);

    expectedHeaders.forEach((header, expectedValue) -> {
        String actualValue = ScenarioContext.getResponse().getHeader(header);

        assertThat(
            "Header mismatch for: " + header,
            actualValue,
            containsString(expectedValue)
        );
    });
}

@Then("response json fields should be:")
public void response_headers_from_body_should_be(DataTable table) {

    Map<String, String> expectedHeaders = table.asMap(String.class, String.class);

    expectedHeaders.forEach((headerName, expectedValue) -> {

        String actualValue = ScenarioContext
                .getResponse()
                .jsonPath()
                .getString(headerName);

        assertThat(
            "Header mismatch for: " + headerName,
            actualValue,
            equalTo(expectedValue)
        );
    });
}

@Then("response json field {string} should not be empty")
public void response_json_field_should_not_be_empty(String jsonPath) {

    String value = ScenarioContext
            .getResponse()
            .jsonPath()
            .getString(jsonPath);

    assertThat("JSON field is missing: " + jsonPath, value, notNullValue());
    assertThat("JSON field is empty: " + jsonPath, value.trim().length(), greaterThan(0));


}

@Then("response json field {string} should match regex {string}")
public void response_json_field_should_match_regex(String jsonPath, String regex) {

    String value = ScenarioContext
            .getResponse()
            .jsonPath()
            .getString(jsonPath);

    assertThat("JSON field missing: " + jsonPath, value, notNullValue());
    assertThat(
        "Regex mismatch for jsonPath: " + jsonPath,
        value,
        matchesPattern(regex)
    );
}






}
