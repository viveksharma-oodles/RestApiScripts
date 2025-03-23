package RestfulBooker;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.JsonUtil;

	public class PatchApiTest extends baseTest {


		   @Test(priority = 2, dependsOnMethods = "RestfulBooker.PostApiTest.testCreateObject")
		   public void testUpdateObject() {
		       String objectId = PostApiTest.getObjectId();
		       Assert.assertNotNull(objectId, "Object ID is required for PATCH request");


		       String patchPayload = JsonUtil.readJsonFile("src/main/resources/patch_payload.json");




		       // Parse JSON to extract updated 'name'
		       JSONObject jsonObject = new JSONObject(patchPayload);
		       String updatedName = jsonObject.getString("name");


		       Response response = request
		               .body(patchPayload)
		               .when()
		               .patch("/objects/" + objectId)
		               .then()
		               .statusCode(200)
		               .extract().response();
		       System.out.println("Patch API response is: "+response.prettyPrint().toString());


		       Assert.assertEquals(response.jsonPath().getString("name"), updatedName, "Updated name should match the input JSON");
		       Assert.assertNotNull(response.jsonPath().getString("updatedAt"), "Updated timestamp should be present");
		   }
		}

