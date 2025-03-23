package RestfulBooker;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.JsonUtil;


public class PostApiTest extends baseTest {


   private static String objectId;
   private static String objectName;


   @Test(priority = 1)
   public void testCreateObject() {
       String postPayload = JsonUtil.readJsonFile("src/main/resources/post_payload.json");


       // Parse JSON to extract 'name'
       JSONObject jsonObject = new JSONObject(postPayload);
       objectName = jsonObject.getString("name");


       Response response = request
               .body(postPayload)
               .when()
               .post("/objects")
               .then()
               .statusCode(200)
               .extract().response();
       System.out.println("POST API response is: "+response.prettyPrint().toString());






       objectId = response.jsonPath().getString("id");  // Extract the ID for PATCH test
       Assert.assertNotNull(objectId, "ID should not be null");
       Assert.assertEquals(response.jsonPath().getString("name"), objectName, "Name should match the input JSON");
   }


   public static String getObjectId() {
       return objectId;
   }




   public static String getObjectName() {
       return objectName;
   }
}

