package globalUtilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class TestBase{
	public RequestSpecification REQUEST;
	public static Properties prop;
	public TestBase() {
			
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resource/config.properties");
			prop.load(ip);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		RestAssured.baseURI = prop.getProperty("serverName");
		REQUEST = RestAssured.given().contentType("application/json");
	}

}
