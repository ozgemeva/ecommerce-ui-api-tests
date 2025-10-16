package api;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class ApiHelper {
	
	public static Response sendPostRequest(String endpoint, String body) {
	        return given()
	                .header("Content-Type", "application/json")
	                .body(body)
	                .when()
	                .post(endpoint)
	                .then()
	                .extract()
	                .response();
	    }

}
