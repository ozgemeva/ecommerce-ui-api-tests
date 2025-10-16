package api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import io.restassured.specification.RequestSpecification;

public class ApiHelper {

	public static Response registerFormField(String uniqueEmail, String register_endpoint) {
		try {
			RequestSpecification request = given().contentType("multipart/form-data").multiPart("name", "test1")
					.multiPart("email", uniqueEmail).multiPart("password", "123456").multiPart("title", "Mrs")
					.multiPart("birth_date", "13").multiPart("birth_month", "10").multiPart("birth_year", "1994")
					.multiPart("firstname", "Test").multiPart("lastname", "TestLastname")
					.multiPart("company", "TestCompany").multiPart("address1", "TestAddress").multiPart("address2", "")
					.multiPart("country", "Netherlands").multiPart("zipcode", "testZipcodeAA11")
					.multiPart("state", "Holland").multiPart("city", "nord").multiPart("mobile_number", "0612345678");

			System.out.println("Sending POST to: " + register_endpoint);
			System.out.println("Generated email: " + uniqueEmail);

			Response response = request.when().post(register_endpoint);
			return response;
		} catch (Exception e) {
			System.err.println("API request failed: " + e.getMessage());
			e.printStackTrace();
			return null;
		}

	}
}
