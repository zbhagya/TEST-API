package stepDefinition;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.Utils;

public class stepDefinition extends Utils {

	private static final String USER_ID = "9b5f49ab-eea9-45f4-9d66-bcf56a531b85";
	private static final String USERNAME = "TOOLSQA-Test";
	private static final String PASSWORD = "Test@@123";
	private static final String BASE_URL = "https://bookstore.toolsqa.com";

	private static String token;
	private static Response response;
	private static String jsonString;
	private static String bookId;
	Logger Log = Logger.getLogger(stepDefinition.class.getName());


	@Given("I am an authorized user")
	public void i_am_an_authorized_user() {
		// Write code here that turns the phrase above into concrete actions

		RestAssured.baseURI =BASE_URL ;
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		response = request.body("{ \"userName\":\"" + USERNAME + "\", \"password\":\"" + PASSWORD + "\"}")
				.post("/Account/v1/GenerateToken");
		Log.info("I am an authorized user and logging into th Book store Applicatioin");
		String jsonString = response.asString();
		token = JsonPath.from(jsonString).get("token");
		Log.info("Received token ");
	}

	@Given("A list of books are available")
	public void a_list_of_books_are_available() {
		// Write code here that turns the phrase above into concrete actions

		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		response = request.get("/BookStore/v1/Books");

		jsonString = response.asString();
		List<Map<String, String>> books = JsonPath.from(jsonString).get("books");
		Assert.assertTrue(books.size() > 0);

		bookId = books.get(0).get("isbn");
	}

	@When("I add a book to my reading list")
	public void i_add_a_book_to_my_reading_list() {
		// Write code here that turns the phrase above into concrete actions

		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();
		request.header("Authorization", "Bearer " + token)
		.header("Content-Type", "application/json");

		response = request.body("{ \"userId\": \"" + USER_ID + "\", " +
				"\"collectionOfIsbns\": [ { \"isbn\": \" " + bookId + "\" } ]}")
				.post("/BookStore/v1/Books");
		
	}

	@Then("the book is added")
	public void the_book_is_added() {
		// Write code here that turns the phrase above into concrete actions

		List<Map<String, String>> booksOfUser = JsonPath.from(jsonString).get("books");
		Assert.assertEquals(8, booksOfUser.size());
		Log.info("Book added successfully in my list");
		Assert.assertEquals(401, response.getStatusCode());
		
	}

	@When("I remove a book from my reading list")
	public void i_remove_a_book_from_my_reading_list() {
		// Write code here that turns the phrase above into concrete actions

		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();

		request.header("Authorization", "Bearer " + token)
		.header("Content-Type", "application/json");

		response = request.body("{ \"isbn\": \"" + bookId + "\", \"userId\": \"" + USER_ID + "\"}")
				.delete("/BookStore/v1/Book");
	}

	@Then("the book is removed")
	public void the_book_is_removed() {
		// Write code here that turns the phrase above into concrete actions
	
		RestAssured.baseURI = BASE_URL;
		RequestSpecification request = RestAssured.given();

		request.header("Authorization", "Bearer " + token)
		.header("Content-Type", "application/json");

		response = request.get("/Account/v1/User/" + USER_ID);
		int getStatusCode=response.getStatusCode();		
		Log.info(getStatusCode);
		jsonString = response.asString();
		List<Map<String, String>> booksOfUser = JsonPath.from(jsonString).get("books");
		Assert.assertEquals(null, booksOfUser);
	}

}
