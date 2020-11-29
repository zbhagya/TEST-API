package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Customer_Shopping_Details;
import pojo.Location;

public class Testdata {
	
	public AddPlace addPlacePayLoad()
	{
		AddPlace p =new AddPlace();
		p.setAccuracy(50);
		p.setAddress("29, side layout, cohen 09");
		p.setLanguage("French-IN");
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("https://rahulshettyacademy.com");
		p.setName("Frontline house");
		List<String> myList =new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");

		p.setTypes(myList);
		Location l =new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		return p;
	}
	
	/*
	 * public String deletePlacePayload(String placeId) { return
	 * "{\r\n    \"place_id\":\""+placeId+"\"\r\n}"; }
	 */
	
	public String getPlacePayload(String placeId) {
		
		return "{\r\n    \"place_id\":\""+placeId+"\"\r\n}";
		
		
	}
	Customer_Shopping_Details c=new Customer_Shopping_Details();

	public String getSessionID(String session_id) {
		
		
		return "{\r\n     \"session\": \""+session_id+ "\"\r\n" + 
				"    }\r\n" + 
				"}";
		
		
	}
}

	
