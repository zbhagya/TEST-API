package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	public static FileInputStream fis;
	public static RequestSpecification req;
	
	public  RequestSpecification customerRequestSpecification() throws IOException
	{
		
		if(req==null)
		{
		PrintStream log =new PrintStream(new FileOutputStream("customer_logs.txt"));
		 req=new RequestSpecBuilder().setBaseUri(getGlobalValue("url")).addQueryParam("X-Oc-Merchant-Id", "123")
				 .addQueryParam("Content-Type", "application/json").addQueryParam("X-Oc-Session", "761d561ebddeaf3be0565328c9345cdb")
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
		 return req;
		}
		return req;
		
	}
	public  RequestSpecification requestSpecification() throws IOException
	{
		
		if(req==null)
		{
		PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
		 req=new RequestSpecBuilder().setBaseUri(getGlobalValue("BASE_URL")).addQueryParam("key", "qaclick123")
				 .addFilter(RequestLoggingFilter.logRequestTo(log))
				 .addFilter(ResponseLoggingFilter.logResponseTo(log))
		.setContentType(ContentType.JSON).build();
		 return req;
		}
		return req;
		
	}
	
	public static String getGlobalValue(String key) throws IOException
	{
		Properties prop =new Properties();
		 fis =new FileInputStream("C:\\Users\\Raja_Bhagya\\eclipse-workspace\\BDD_Framework\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	
	public  String getJsonPath(Response response,String key)
	{
		  String resp=response.asString();
		JsonPath   js = new JsonPath(resp);
		return js.get(key).toString();
	}


}
