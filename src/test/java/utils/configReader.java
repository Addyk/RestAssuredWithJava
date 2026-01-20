package utils;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.management.RuntimeErrorException;

public class configReader {

        private static Properties properties = new Properties();


        //This static block loads config.properties file in memory. Whenever we call getProperty we don't need to read the file again.
        static{
            try(InputStream input=
                configReader.class.getClassLoader().getResourceAsStream("config.properties")){
                    properties.load(input);
                }catch(Exception e){
                    throw new RuntimeException("Failed to load config properties",e);
                }
            
        }

        public static String getBaseUrl() {
            return properties.getProperty("baseUrl");
        }

        public static String getProperty(String key){
            return properties.getProperty(key);
        }


}
