import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.json.XML;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.xml.ws.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RestGet {

    public static void testGet()
    {
        given()
                .auth()
                .preemptive()
                .basic("admin","district")
                .pathParam("dataElementID","AtRsfhXEntI")
                .when()
                .get("https://play.dhis2.org/2.29/api/29/dataElements/{dataElementID}")
                .then()
                .assertThat()
                .statusCode(200);

    }

    public static void getRespnse()
    {
        /*List<String> jsonResponse=given()
                .auth()
                .preemptive()
                .basic("admin","district")
                .pathParam("dataElementID","AtRsfhXEntI")
                .when()
                .get("https://play.dhis2.org/2.29/api/29/dataElements/{dataElementID}")
              .contentType(ContentType.JSON).extract().response();
*/

    }

    public static Map<String, Object> map = new HashMap<String, Object>();
    JSONObject a;
    String json;

    @BeforeSuite
    public void initialize(){
        RestAssured.baseURI = "https://play.dhis2.org/2.29/api/29/";
    }

    @AfterSuite
    public void cleanUp(){}

    @BeforeTest
    public void postDataSetup(){
        map.put("dataElementAttribute", true);
        map.put("valueType", "TEXT");
        map.put("name", "Test_attribute");
        RestAssured.basePath = "attributes";
    }

    @Test
    public static void testPost(){

        given()
                .contentType("application/json")
                .auth()
                .preemptive()
                .basic("admin", "district")
                .body(map)
                .when()
                .post()
                .then()
                .statusCode(201)
                .and()
                .assertThat()
                .body("httpStatus", equalTo("Created"));
    }

    @Test
    public static void testDelete(){

        given()
                .contentType("application/json")
                .auth()
                .preemptive()
                .basic("admin", "district")
                .when()
                .delete("/OEswwLjjzVC")
                .then()
                .statusCode(200)
                .and()
                .assertThat()
                .body("httpStatus", equalTo("OK"));

        given()
                 .contentType("application/json")
                .auth()
                .preemptive()
                .basic("admin", "district")
                .when()

                .get(RestAssured.baseURI+RestAssured.basePath+"/OEswwLjjzVC")
                .then()
                .statusCode(404);


    }

/*    public static void main(String args[])
    {

        testGet();
        System.out.println("hello");
    }*/
}
