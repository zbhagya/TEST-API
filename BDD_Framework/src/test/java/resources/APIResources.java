package resources;

public enum APIResources {

	//enum is special class in java which has collection of constants or  methods
	AddPBook("/maps/api/place/add/json"),
	getBook("/maps/api/place/get/json"),
	deleteBook("/maps/api/place/delete/json");
	
	
	private String resource;
	
	APIResources(String resource)
	{
		this.resource=resource;
	}
	
	public String getResource()
	{
		return resource;
	}
	
}
