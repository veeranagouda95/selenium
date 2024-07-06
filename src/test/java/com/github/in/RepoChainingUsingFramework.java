package com.github.in;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.github.genericlibery.Base_Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RepoChainingUsingFramework extends Base_Test {

	String baseurl="https://api.gitHub.com";
	String token="";
	String owner="veeranagouda95";
	String reponame;
	@Test(priority = 1)
	public void createRepo() {
		HashMap b=new HashMap();
		b.put("name","sachin");
		b.put("description",":( bad");
		b.put("private", true);
		
		HttpRequest("post", b, baseurl+"/user/repos", token);
		
		reponame=readReponseJsonValue("name");
		Assert.assertEquals(readReponseJsonValue("name"), b.get("name"));
		Assert.assertEquals(readReponseJsonValue("description"), b.get("description"));
		Assert.assertEquals(readReponseJsonValue("name"), b.get("private"));
		Assert.assertEquals(responseBody.getStatusCode(), 201);
		Assert.assertEquals(responseBody.getHeaders().getValue("Server"), "GitHub.com");
		test.log(Status.PASS, "Resonse verified");
	}
	@Test(priority = 2)
	public void getRepos() {
		HttpRequest("get", null, baseurl+"user/repos/"+owner+"/"+reponame, token);
		
		Assert.assertEquals(responseBody.getStatusCode(), 200);
		Assert.assertEquals(responseBody.jsonPath().get("description"), ":( bad");
		Assert.assertEquals(responseBody.jsonPath().get("private"), true);
		Assert.assertEquals(responseBody.jsonPath().get("name"), reponame);
		
	}
	
	@Test(priority = 3)
	
	public void updateRepos() {
		
		HashMap b=new HashMap();
		b.put("description", "sorry");
		
		Response response = RestAssured.given().body(b)
		.header("Authorization","Bearer "+token)
		.when().patch(baseurl+"/repos/"+owner+"/"+reponame);
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.jsonPath().get("description"), "sorry");
		
	}
		
		@Test(priority = 4)
		public void deleteRepo() {
			RestAssured.given()
			.header("Authorization","Bearer "+token)
			.when().delete(baseurl+"/repos/"+owner+"/"+reponame)
			.then().assertThat().statusCode(204);
		}	
	
}
