package tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import globalUtilities.TestBase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import response.GeoCodeGoogle;

public class TestCode extends TestBase{

	@Test(enabled = true)
	public void validateTheResponseCode() throws JsonProcessingException, IOException {
		RestAssured.baseURI = "https://maps.googleapis.com";
		String endpoint = "/maps/api/geocode/json";
		System.out.println(endpoint);
		String key = prop.getProperty("key");
		System.out.println(key);

		String newEndP= endpoint+key;
		System.out.println(newEndP);
		Response resp = REQUEST.queryParam("key",prop.getProperty("key")).queryParam("address",prop.getProperty("address")).get(endpoint);
		System.out.println(resp.asString());
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode newJSon = mapper.readTree(resp.asString());
		JsonNode oldJson = mapper.readTree(GeoCodeGoogle.goeCodeResponse());
		
		System.out.println(newJSon.equals(oldJson));
		
		if (resp.statusCode() == 200) {
			System.out.println("Validate the response code");
			assertTrue(true);
		} else {
			assertFalse(false);
		}

	}

	@Test(enabled=false)
	public void validateAddressComponents(){
		
		RestAssured.baseURI = "https://maps.googleapis.com";
		String endpoint = "/maps/api/geocode/json?address=1600+Amphitheatre+Parkway+Mountain+View+CA&key=AIzaSyAmeeaLsMHfaVwrkJZeiSrYo4SpFObMyVM";
		System.out.println(endpoint);
		String key = "AIzaSyAmeeaLsMHfaVwrkJZeiSrYo4SpFObMyV";

		Response resp = RestAssured.given().when().get(endpoint);
		String res = resp.asString();
		JsonPath jp = new JsonPath(res);
/*
		SoftAssertions softly = new SoftAssertions();
		softly.assertThat(jp.get("results[0].address_components[0].long_name").toString().))
*/
		if(jp.get("results[0].address_components[0].long_name") !=null){
			System.out.println("pass");
			assertTrue(true);
		}
		if(jp.get("results[0].address_components[0].short_name") !=null){
			System.out.println("pass");
			assertTrue(true);
		}
		
		if(jp.get("results[0].formatted_address").equals("1600 Amphitheatre Pkwy, Mountain View, CA 94043, USA")){
			System.out.println("pass");
			assertTrue(true);
		}else{
			System.out.println("FAIL");
			assertFalse(false);
		}
		
		
		/*Result result = resp.as(Result.class);
		
		if(result.getGeometry().getLocation().getLat() !=null){
			assertTrue(true);
			System.out.println("Passs");
			}else{
			assertFalse(false);
		}
		if(result.getGeometry().getLocation().getLng() !=null){
			assertTrue(true);
			System.out.println("Passs");
			}else{
			assertFalse(false);
		}*/
	}
	@Test(enabled=false)
	public void verifyPlaceID(){
		RestAssured.baseURI = "https://maps.googleapis.com";
		String endpoint = "/maps/api/geocode/json?address=1600+Amphitheatre+Parkway+Mountain+View+CA&key=AIzaSyAmeeaLsMHfaVwrkJZeiSrYo4SpFObMyVM";
		System.out.println(endpoint);
		String key = "AIzaSyAmeeaLsMHfaVwrkJZeiSrYo4SpFObMyV";

		Response resp = RestAssured.given().when().get(endpoint);
		String res = resp.asString();
		JsonPath jp = new JsonPath(res);
		
		if(jp.get("results[0].place_id").equals("ChIJ2eUgeAK6j4ARbn5u_wAGqWA")){
			System.out.println("pass");
			assertTrue(true);
		}else{
			System.out.println("FAIL");
			assertFalse(false);
		}
		
	}

}
