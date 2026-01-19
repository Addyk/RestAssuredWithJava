package stepdefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;

import io.restassured.response.Response;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static io.restassured.RestAssured.given;



public class statusCode {
    private Response response;

    @When("I send {string} request to {string}")
    public void i_send_request_to(String request, String endpoint) {
           switch (request.toUpperCase()) {

            case "GET":
                response = given()
                        .when()
                        .get(endpoint);
                break;

            case "POST":
                response = given()
                        .body("{}")   // placeholder body
                        .when()
                        .post(endpoint);
                break;

            case "PUT":
                response = given()
                        .body("{}")
                        .when()
                        .put(endpoint);
                break;

            case "PATCH":
                response = given()
                        .body("{}")
                        .when()
                        .patch(endpoint);
                break;

            case "DELETE":
                response = given()
                        .when()
                        .delete(endpoint);
                break;

            default:
                throw new IllegalArgumentException(
                        "Unsupported HTTP method: " + request);
        }
    }
        
        // Additional request types (POST, PUT, DELETE) can be handled here
    

    @Then("status code should be {int}")
    public void status_code_should_be(Integer statusCode) {
        assertThat(response.getStatusCode(), is(statusCode));
        System.out.println("Status = " + response.statusCode());
        System.out.println("Body = " + response.asString());
    }

}
