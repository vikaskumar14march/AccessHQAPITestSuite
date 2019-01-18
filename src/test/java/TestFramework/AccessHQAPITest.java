package TestFramework;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AccessHQAPITest {
	

@Test
public void getStationAPITest()
{
		//BaseURL or Host
		RestAssured.baseURI= "https://www.transportnsw.info";
		
		Response resp = given().
		       param("TfNSWSF","true").
		       param("language","en").
		       param("name_sf","Wynyard Station").
		       param("outputFormat","rapidJSON").
		       param("type_sf","any").
		       param("version","10.2.2.48").
		       when().
		       get("/web/XML_STOPFINDER_REQUEST").
		       then().assertThat().statusCode(200).and().contentType(ContentType.JSON)
		       .and().
		       body("locations[0].name",equalTo("Wynyard Station, Sydney")).
		       extract().response();;
		       
		       JsonPath js= ReusableMethods.rawToJson(resp);
			   
			   int count=js.get("locations[0].size()");
			  
			   System.out.println(count);
			   System.out.println("Size of Modes : " + js.get("locations[0].modes.size()"));
			   int modesOfTransport = js.get("locations[0].modes.size()");
			   
		       if(modesOfTransport == 0)
		          Assert.fail("Other Modes of transport are not available");
		       
		      
	
}

}
