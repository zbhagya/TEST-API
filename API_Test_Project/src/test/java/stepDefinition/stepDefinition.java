package stepDefinition;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import java.io.IOException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.Testdata;
import resources.Utils;
import resources.APIResources;

public class stepDefinition extends Utils {

	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	Testdata data = new Testdata();
	static String place_id;

	@Given("Add Place Payload")
	public void add_place_payload() throws IOException {
		// Write code here that turns the phrase above into concrete actions
		res = given().spec(requestSpecification()).body(data.addPlacePayLoad());
	}
	@Given("Get Place Payload")
	public void get_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		res=given().spec(requestSpecification()).body(data.getPlacePayload(place_id));
	
	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		// Write code here that turns the phrase above into concrete actions

		// constructor will be called with value of resource which you pass
		APIResources resourceAPI = APIResources.valueOf(resource);
		System.out.println(resourceAPI.getResource());

		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		if (method.equalsIgnoreCase("POST"))
			response = res.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = res.when().get(resourceAPI.getResource());
		else if(method.equalsIgnoreCase("PUT"))
			response=res.when().put(resourceAPI.getResource());
	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		// Write code here that turns the phrase above into concrete actions		
		assertEquals(response.getStatusCode(), 200);
}
	

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String Expectedvalue) {
		// Write code here that turns the phrase above into concrete actions
		assertEquals(getJsonPath(response, keyValue), Expectedvalue);
	}
	


}
