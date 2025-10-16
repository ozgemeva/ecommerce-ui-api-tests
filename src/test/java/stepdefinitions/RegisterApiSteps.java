package stepdefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import TestDataSets.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import api.*;

public class RegisterApiSteps {
	String register_endpoint;
	Response response;
	String body;
	int response_code;
	String response_status;
	
	@Given("the API endpoint is {string}")
	public void setEndpoint(String url) {
		URI uri = URI.create(TestData.BASE_URL_API);
		register_endpoint = uri.resolve(url).toString();
		System.out.println("Register Api endpoint: " + register_endpoint);
	}

	@When("I send a POST request with valid registration data")
	public void i_send_a_post_request_with_valid_user_registration_data() {
		// Read json files

		try {
			body = new String(Files.readAllBytes(Paths.get("src/test/resources/testDataSet/registerUser.json")));
			System.out.println("create body request");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Dynamic email
		body = body.replace("testApi@mail.com", "testApi" + System.currentTimeMillis() + "@mail.com");

		// Send Api request
		try {
			System.out.println("send post request");
			response = ApiHelper.sendPostRequest(register_endpoint, body);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Response body: ");
		response.prettyPrint();

		assertNotNull(response, "Response is null — API call may have failed!");
		assertTrue(response.statusCode() > 0, "Status code is invalid!");

	}

	@Then("the response status code should be 200")
	public void the_response_status_code_should_be_200() {
		response_code = response.getStatusCode();
		System.out.println("Actual status code: " + response_code);
		assertEquals(response_code, 200, "Status code is not as expected!");

	}
	
	@And("the response should contain success")
	 public void the_response_should_contain_success() {
		
		response_status = response.jsonPath().getString("status");
		assertTrue(response_status.contains("user created"), "Expected success message not found in response!");
	}


}
