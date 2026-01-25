package hooks;

import io.cucumber.java.Before;
import io.cucumber.java.After;

import io.restassured.RestAssured;

import java.io.InputStream;
import java.util.Properties;
import utils.configReader;


public class Hooks {

@Before
public void setUp() {
    
    Properties props=new Properties();
    try{ 
            RestAssured.baseURI=configReader.getProperty("baseUrl");
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration: " + e.getMessage());
        }

         RestAssured.baseURI=configReader.getProperty("baseUrl");

    }
}
