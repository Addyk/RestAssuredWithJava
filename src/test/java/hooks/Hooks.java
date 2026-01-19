package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;

import io.restassured.RestAssured;

import java.io.InputStream;
import java.util.Properties;


public class Hooks {

@Before
public void setUp() {
    
    Properties props=new Properties();
    try(InputStream input=
        Hooks.class.getClassLoader().getResourceAsStream("config.properties")){ 
            if(input==null){
                throw new RuntimeException("Could not find config.properties file");
            }
            props.load(input);
            RestAssured.baseURI=props.getProperty("baseUrl");
            System.out.println("BASE URI = " + RestAssured.baseURI);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration: " + e.getMessage());
        }

    }
}
