package api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class ApiHelper {

	public static Response registerFormField(String uniqueEmail, String register_endpoint) {

		return given().contentType("multipart/form-data").multiPart("name", "test1").multiPart("email", uniqueEmail)
				.multiPart("password", "123456").multiPart("title", "Mrs").multiPart("birth_date", "13")
				.multiPart("birth_month", "10").multiPart("birth_year", "1994").multiPart("firstname", "Test")
				.multiPart("lastname", "TestLastname").multiPart("company", "TestCompany")
				.multiPart("address1", "TestAddress").multiPart("address2", "").multiPart("country", "Netherlands")
				.multiPart("zipcode", "testZipcodeAA11").multiPart("state", "Holland").multiPart("city", "nord")
				.multiPart("mobile_number", "0612345678").when().post(register_endpoint).then().extract().response();
	}

}
