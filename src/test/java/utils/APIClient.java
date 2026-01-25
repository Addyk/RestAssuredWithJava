package utils;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;;

public class APIClient {

    public static Response sendRequest(
        String method,
        String endpoint,
        RequestSpecification request){

            if(request==null){
                throw new IllegalStateException("Request is not initalized");
            }

            return switch (method.toUpperCase()) {
            case "GET" -> request.when().get(endpoint);
            case "DELETE" -> request.when().delete(endpoint);
            case "POST" -> request.when().post(endpoint);
            case "PUT" -> request.when().put(endpoint);
            case "PATCH" -> request.when().patch(endpoint);
            default -> throw new IllegalArgumentException("Invalid method: " + method);
        };


        }



    
}
