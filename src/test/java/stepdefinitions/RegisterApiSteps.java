package stepdefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;
import java.net.URI;

import TestDataSets.TestData;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class RegisterApiSteps {
	String register_endpoint;
	Response response;
	String body;
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
		response = registerFormField(uniqueEmail);
	
		System.out.println("create body request and Response body: ");
		response.prettyPrint();

		assertNotNull(response, "Response is null — API call may have failed!");
		assertTrue(response.statusCode() > 0, "Status code is invalid!");

	}

	public Response registerFormField(String uniqueEmail) {
		
		return given().contentType("multipart/form-data").multiPart("name", "test1").multiPart("email", uniqueEmail)
				.multiPart("password", "123456").multiPart("title", "Mrs").multiPart("birth_date", "13")
				.multiPart("birth_month", "10").multiPart("birth_year", "1994").multiPart("firstname", "Test")
				.multiPart("lastname", "TestLastname").multiPart("company", "TestCompany")
				.multiPart("address1", "TestAddress").multiPart("address2", "").multiPart("country", "Netherlands")
				.multiPart("zipcode", "testZipcodeAA11").multiPart("state", "Holland").multiPart("city", "nord")
				.multiPart("mobile_number", "0612345678").when().post(register_endpoint).then().extract().response();
	}

	@Then("the response status code should be 200")
	public void the_response_status_code_should_be_200() {
		response_code = response.getStatusCode();
		System.out.println("Actual status code: " + response_code);
		assertEquals(response_code, 200, "Status code is not as expected!");

	}

	@And("the response should contain success")
	public void the_response_should_contain_success() {

		response_message = response.asString();
		System.out.println("response_message: "+response_message);
		assertTrue(response_message.contains("User created!"), "Expected success message not found in response!");
	}

}
