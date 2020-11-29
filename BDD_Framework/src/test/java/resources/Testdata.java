package resources;

import io.restassured.response.Response;

public class Testdata {
	
	
	private static String token;
	public static String getToken() {
		return token;
	}
	public static void setToken(String token) {
		Testdata.token = token;
	}
	public static Response getResponse() {
		return response;
	}
	public static void setResponse(Response response) {
		Testdata.response = response;
	}
	public static String getJsonString() {
		return jsonString;
	}
	public static void setJsonString(String jsonString) {
		Testdata.jsonString = jsonString;
	}
	public static String getBookId() {
		return bookId;
	}
	public static void setBookId(String bookId) {
		Testdata.bookId = bookId;
	}
	private static Response response;
	private static String jsonString;
	private static String bookId;	
}

	
