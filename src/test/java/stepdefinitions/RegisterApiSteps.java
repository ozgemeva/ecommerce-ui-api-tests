package stepdefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import java.net.URI;

import TestDataSets.TestData;
import api.ApiHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;

public class RegisterApiSteps {
	String register_endpoint;
	Response response;
	int response_code;
	String response_message;
	String uniqueEmail;

	@Given("the API endpoint is {string}")
	public void setEndpoint(String url) {
		URI uri = URI.create(TestData.BASE_URL_API);
		register_endpoint = uri.resolve(url).toString();
		System.out.println("Register Api endpoint: " + register_endpoint);
	}

	@When("I send a POST request with valid registration data")
	public void i_send_a_post_request_with_valid_user_registration_data() {

		uniqueEmail = "testApi" + System.currentTimeMillis() + "@mail.com";
		System.out.println("Dynamic email: " + uniqueEmail);

		response = ApiHelper.registerFormField(uniqueEmail, register_endpoint);

		System.out.println("create body request and Response body: ");
		response.prettyPrint();

		assertNotNull(response, "Response is null — API call may have failed!");
		assertTrue(response.statusCode() > 0, "Status code is invalid!");
	}

	@Then("the response status code should be 201")
	public void the_response_status_code_should_be_201() {
		response_code = response.getStatusCode();
		response_message = response.asString();

		boolean bodyContains201 = response_message.contains("\"responseCode\": 201");
		assertTrue(response_code == 201 || bodyContains201,"Expected 201 but got HTTP " + response_code + " and body: " + response_message);
	}

	@And("the response should contain success")
	public void the_response_should_contain_success() {

		response_message = response.asString();
		assertTrue(response_message.contains("User created!"), "Expected success message not found in response!");
	}

}
