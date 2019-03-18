package  khalid.app1;
import com.jayway.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * These tests checks our REST API.
 */
public class MyRestIT {

  @BeforeClass
  public static void configureRestAssured() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = Integer.getInteger("http.port", 8080);
  }

  @AfterClass
  public static void unconfigureRestAssured() {
    RestAssured.reset();
  }

  @Test
  public void checkThatWeCanRetrieveIndividualProduct() {
    // Get the list of bottles, ensure it's a success and extract the first id.

    final int id = get("/api/capabilities").then()
        .assertThat()
        .statusCode(200)
        .extract()
        .jsonPath().getInt("find { it.name=='temperature' }.id");

    // Now get the individual resource and check the content
    get("/api/capabilities/" + id).then()
        .assertThat()
        .statusCode(200)
        .body("name", equalTo("temperature"))
        .body("location", equalTo("192.168.1.240:3001"))
        .body("id", equalTo(id));
  }

  @Test
  public void checkWeCanAddAndDeleteAProduct() {
    // Create a new bottle and retrieve the result (as a Capability instance).
    Capability capability = given()
        .body("{\"name\":\"lamp brightness\", \"location\":\"192.168.1.137:8675\"}").request().post("/api/capabilities").thenReturn().as(Capability.class);
    assertThat(capability.getName()).isEqualToIgnoringCase("lamp brightness");
    assertThat(capability.getLocation()).isEqualToIgnoringCase("192.168.1.137:8675");
    assertThat(capability.getId()).isNotZero();

    // Check that it has created an individual resource, and check the content.
    get("/api/capabilities/" + capability.getId()).then()
        .assertThat()
        .statusCode(200)
        .body("name", equalTo("lamp brightness"))
        .body("location", equalTo("192.168.1.137:8675"))
        .body("id", equalTo(capability.getId()));

    // Delete the bottle
    delete("/api/capabilities/" + capability.getId()).then().assertThat().statusCode(204);

    // Check that the resrouce is not available anymore
    get("/api/capabilities/" + capability.getId()).then()
        .assertThat()
        .statusCode(404);
  }
}
