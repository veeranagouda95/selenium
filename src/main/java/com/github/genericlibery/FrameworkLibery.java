package com.github.genericlibery;

import java.util.HashMap;

import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class FrameworkLibery {

	public Response responseBody;

	public ExtentSparkReporter sparkReporter;
	public ExtentReports reports;
	public ExtentTest test;
	
	
	
	/*method for sending http Request
	 * 
	 * Returns http Response
	 * Author:veeranagouda
	 * Reviewed By:anurag,vishnu
	 */
	
	public Response HttpRequest(String httpMethod,HashMap body,String URI,String authorization) {
		
		if (httpMethod.equalsIgnoreCase("post")) {
			responseBody=RestAssured.given().body(body).header("Authorization","Bearer "+ authorization)
			.when().post(URI);
			
		}
		else if (httpMethod.equalsIgnoreCase("Get")) 
		{
			responseBody=RestAssured.given().header("Authorization","Bearer "+authorization)
			.when().get(URI);	
		}
		else if (httpMethod.equalsIgnoreCase("put")) {
			responseBody=RestAssured.given().body(body).header("Authorization","Bearer "+authorization)
					.when().put(URI);
		}
		else if (httpMethod.equalsIgnoreCase("patch")) {
			responseBody=RestAssured.given().body(body).header("Authorization","Bearer "+authorization)
					.when().patch(URI);
			
		}
		else if (httpMethod.equalsIgnoreCase("delete")) {
			responseBody=RestAssured.given().body(body).header("Authorization","Bearer "+authorization)
					.when().delete(URI);	
		}
		else {
			Reporter.log("Invalid Request", true);
		}
		return responseBody;
	}
		public String readReponseJsonValue(String key) {
			String value=responseBody.jsonPath().get(key);
			return value;
		}
	
}
