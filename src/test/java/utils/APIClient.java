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

            return switch(method.toUpperCase()){
                case "GET"-> request.get(endpoint);
                case "POST"-> request.post(endpoint);
                case "PUT"-> request.put(endpoint);
                case "DELETE"-> request.delete(endpoint);
                case "PATCH"-> request.patch(endpoint);
                default -> throw new IllegalArgumentException("Invalid HTTP method: " + method);
            };


        }



    
}
