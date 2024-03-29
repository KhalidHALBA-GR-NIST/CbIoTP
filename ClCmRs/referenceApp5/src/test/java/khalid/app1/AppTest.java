package khalid.app1;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.unit.Async;
import io.vertx.ext.unit.TestContext;
import io.vertx.ext.unit.junit.VertxUnitRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.net.ServerSocket;

@RunWith(VertxUnitRunner.class)
public class AppTest {

  private Vertx vertx;
  private Integer port;
  @Before
  public void setUp(TestContext context)  throws IOException {
    vertx = Vertx.vertx();
    
    ServerSocket socket = new ServerSocket(0);
    port = socket.getLocalPort();
    socket.close();
 //   port = 8081;
    DeploymentOptions options = new DeploymentOptions()
            .setConfig(new JsonObject().put("http.port", port)
            );
    vertx.deployVerticle(App.class.getName(), options, context.asyncAssertSuccess());
    
    
  }

  @After
  public void tearDown(TestContext context) {
    vertx.close(context.asyncAssertSuccess());
  }

  
//  
//  
//  @Test
//  public void testMyApplication(TestContext context) {
//    final Async async = context.async();
//
//    vertx.createHttpClient().getNow(port, "localhost", "/alpha",
//     response -> {
//      response.handler(body -> {
//        context.assertTrue(body.toString().contains("Hello"));
//        async.complete();
//      });
//    });
//  }
//  
  @Test
  public void checkThatWeCanAdd(TestContext context) {
    Async async = context.async();
    final String json = Json.encodePrettily(new Capability("lamp brightness", "192.168.1.137:8675"));
    final String length = Integer.toString(json.length());
    vertx.createHttpClient().post(port, "localhost", "/api/capabilities")
        .putHeader("content-type", "application/json")
        .putHeader("content-length", length)
        .handler(response -> {
          context.assertEquals(response.statusCode(), 201);
          context.assertTrue(response.headers().get("content-type").contains("application/json"));
          response.bodyHandler(body -> {
            final Capability capability = Json.decodeValue(body.toString(), Capability.class);
            context.assertEquals(capability.getName(), "lamp brightness");
            context.assertEquals(capability.getLocation(), "192.168.1.137:8675");
            context.assertNotNull(capability.getId());
            async.complete();
          });
        })
        .write(json)
        .end();
  }
  

  @Test
  public void checkThatTheIndexPageIsServed(TestContext context) {
    Async async = context.async();
    vertx.createHttpClient().getNow(port, "localhost", "/assets/index.html", response -> {
      context.assertEquals(response.statusCode(), 200);
      context.assertEquals(response.headers().get("content-type"), "text/html;charset=UTF-8");
      response.bodyHandler(body -> {
        context.assertTrue(body.toString().contains("<title>IoT Capability Platform</title>"));
        async.complete();
      });
    });
  }




}