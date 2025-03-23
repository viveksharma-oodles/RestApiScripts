package RestfulBooker;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class baseTest {
	protected RequestSpecification request;


	   @BeforeClass
	   public void setup() {
	       RestAssured.baseURI = "https://api.restful-api.dev";
	       request = RestAssured.given()
	               .header("Content-Type", "application/json")
	               .header("Accept", "application/json");
	   }
}

