package practice;

import java.io.File;

import org.codehaus.jackson.map.ObjectMapper;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class APITests {
	
	@Test
	public void queryTest() {
		RestAssured.given()
		.contentType(ContentType.JSON)
		.queryParam("queryKey", "queryValue")
		.when()
		.get("url")
		.then()
		.log().all();
		//second msg
	}
	
	@Test
	public void htmlValidation() {
		Response response=RestAssured.given()
			.get("url");
		String data=response.htmlPath().get("xpath");
		Assert.assertEquals(data, "expData");
		byte[] bytes = response.getBody().asByteArray();
		File file=new File(bytes.toString());
		
		response.xmlPath().get("xpath");
		
		response.jsonPath().get("jsonPath");
	}
	
	@Test
	public void convertToJSON() {
		RestAssured.given()
		.body("{\"name\":\"karthik\", \"company\":\"OLA\"}\"")
		.post("url");
		
		
	}
	
	@Test
	public void specBuilder() {
		//Before annotation
		AuthenticationScheme authSchema = RestAssured.oauth2("token");
		RequestSpecBuilder reqBuilder=new RequestSpecBuilder();
		//used to build request preconditions
		reqBuilder.setBaseUri("uri");
		reqBuilder.setContentType(ContentType.JSON);
		reqBuilder.setAuth(authSchema);
		//convert specs into request specification
		RequestSpecification reqestSpec=reqBuilder.build();
		
	//Test annotation
		RestAssured.given()
		//call the request specs
		.spec(reqestSpec)
		//POJO, JSON obj, HashMap, File, String
		.body(new Object(), ObjectMapperType.JACKSON_2)
		.when()
		.get("endpoint")
		.then()
		.log().all();
	}
	
	@Test
	public void uploadFile() {
		RestAssured.given()
		.multiPart(new File("pathOf File"))
	
		.when()
		.post()
		.then()
		.log().all();
	}
	
	@Parameters(value = {"browser","plaform","browserVersion"})// use where launch instance code written
	@Test
	public void validateResponse(String browserName,String platform, String browserVersion) {
		ValidatableResponse validate = RestAssured.given()
		.get("url")
		.then();
//		validate.header("content-type", "application/json")//single header value
//		validate.headers(expectedHeaders)// map of header value or used to validate multiple headers
//		validate.cookie(cookieName)
//		validate.statusCode(expectedStatusCode)
//		validate.statusLine(expectedStatusLine)
//		validate.time(matcher)
//		validate.body(arguments, responseAwareMatcher)
		
		
		
		
		
		
		
	}

}
