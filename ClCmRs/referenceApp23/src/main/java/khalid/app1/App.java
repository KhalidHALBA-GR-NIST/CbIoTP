package khalid.app1;

import java.util.LinkedHashMap;
import java.util.Map;

import io.vertx.core.AbstractVerticle;
//import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
//import io.vertx.core.Handler;
//import io.vertx.core.eventbus.Message;
//import io.vertx.core.http.HttpClient;
//import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
//import io.vertx.servicediscovery.ServiceDiscovery;
//import io.vertx.servicediscovery.ServiceDiscoveryOptions;
//import io.vertx.servicediscovery.ServiceReference;
//import io.vertx.servicediscovery.types.HttpEndpoint;
//import io.vertx.serviceproxy.ServiceException;
//
//





//import io.vertx.core.*;
//import io.vertx.core.json.JsonObject;
//import io.vertx.servicediscovery.ServiceDiscovery;
//import io.vertx.servicediscovery.types.HttpEndpoint;
//
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import io.vertx.ext.web.client.HttpResponse;
//import io.vertx.ext.web.client.WebClient;
//import io.vertx.ext.web.codec.BodyCodec;
//
//
//
//import io.vertx.servicediscovery.Record;
//import io.vertx.servicediscovery.spi.ServiceImporter;
//import io.vertx.servicediscovery.spi.ServicePublisher;
//import io.vertx.servicediscovery.spi.ServiceType;
//import io.vertx.servicediscovery.types.*;
//
//import io.vertx.servicediscovery.ServiceDiscovery;
//import io.vertx.servicediscovery.Record;
//import io.vertx.servicediscovery.ServiceReference;
//import io.vertx.servicediscovery.types.MessageSource;



/**
 * Hello world!
 *
 */
public class App extends AbstractVerticle 
{
	
	
	@Override
	  public void start(Future<Void> fut) {
		
		

		
		
		
		
		
		
		

		
		createSomeData();
		// Create a router object.
		 Router router = Router.router(vertx);

		 
		 // Bind "/" to our hello message - so we are still compatible.
		 
		 // router dispatches/routes requests to route "/" to handler 
		 
//		 router.route("/alpha").handler(routingContext -> {
//			 
//		   HttpServerResponse response = routingContext.response();
//		   response.putHeader("content-type", "text/html").end("<h1><button type=\"button\">Click Me!</button><table width=\"100%\" border=\"1\" bordercolor=\"#cccccc\"cellpadding=\"5\" cellspacing=\"5\"><tr><th colspan=\"6\">My Hello Plan for the Week</th></tr><tr><td></td><th>Monday</th><th>Tuesday</th><th>Wednesday</th><th>Thursday</th><th>Friday</th></tr><tr><td>Activity</td><td>Meeting at ABC-123 Ltd</td><td>Business Lunch at 1pm</td><td>Project due by 5pm</td><td>Web Conference in London</td><td>Early Finish (4pm)</td>\n" + 
//		   		"</tr></table></h1>\n" + 
//		   		"");
//		   
//		   // router.route("/caplist").handler(routingContext -> { 
//		  // HttpServerResponse response = routingContext.response();
//		  // response.putHeader("content-type", "text/html").end("<h1>html or json or table</h1>");}   )
//		   
//		   
//		 
//		 });

		
//		 router.route("/beta").handler(routingContext -> {
//			 
//			   HttpServerResponse response = routingContext.response();
//			   response.putHeader("content-type", "text/html").end("<h1><button type=\"button\">Click her!</button><table width=\"100%\" border=\"1\" bordercolor=\"#cccccc\"cellpadding=\"5\" cellspacing=\"5\"><tr><th colspan=\"6\">My Hello Plan for the Week</th></tr><tr><td></td><th>Monday</th><th>Tuesday</th><th>Wednesday</th><th>Thursday</th><th>Friday</th></tr><tr><td>Activity</td><td>Meeting at ABC-123 Ltd</td><td>Business Lunch at 1pm</td><td>Project due by 5pm</td><td>Web Conference in London</td><td>Early Finish (4pm)</td>\n" + 
//			   		"</tr></table></h1>\n" + 
//			   		"");
//			   
//			   // router.route("/caplist").handler(routingContext -> { 
//			  // HttpServerResponse response = routingContext.response();
//			  // response.putHeader("content-type", "text/html").end("<h1>html or json or table</h1>");}   )
//			   
//
//			 });
//
//			

		 
		 
		 // Serve static resources from the /assets directory
	
		 
		 
		 
		 	 router.route("/assets/*").handler(StaticHandler.create("assets"));
		
		 router.get("/api/capabilities").handler(this::getAll);
		 
	
		 
			//This line instructs the router to handle the GET requests on “/api/capabilities” by calling the getAll method. 
		 // We could have inlined the handler code, but for clarity reasons we have created getall method: 
		 
		    router.get("/api/capabilities/:id").handler(this::getOne);
		    router.put("/api/capabilities/:id").handler(this::updateOne);
		 
		  router.route("/api/capabilities*").handler(BodyHandler.create());
		  router.post("/api/capabilities").handler(this::addOne);
		  router.delete("/api/capabilities/:id").handler(this::deleteOne);
		  
		  
		  router.route("/*").handler(StaticHandler.create());
	
		 
		 
	    vertx
	        .createHttpServer()
	        .requestHandler(router::accept)
	        .listen(
	        		 // Retrieve the port from the configuration,
	                // default to 8080.
	        		
	        config().getInteger("http.port", 8080),
	        result -> {
	          if (result.succeeded()) {
	            fut.complete();

	          } else {
	            fut.fail(result.cause());
	          }
	        });
	}
	
	
	
	private void getAll(RoutingContext routingContext) {
		  routingContext.response()
		      .putHeader("content-type", "application/json; charset=utf-8")
		      .end(Json.encodePrettily(CapMap.values()));
		}
	
	private void addOne(RoutingContext routingContext) {
		  final Capability cap = Json.decodeValue(routingContext.getBodyAsString(),
				  Capability.class);
		  CapMap.put(cap.getId(), cap);
		  routingContext.response()
		      .setStatusCode(201)
		      .putHeader("content-type", "application/json; charset=utf-8")
		      .end(Json.encodePrettily(cap));
		}
	
	
	private void deleteOne(RoutingContext routingContext) {
		  String id = routingContext.request().getParam("id");
		  if (id == null) {
		    routingContext.response().setStatusCode(400).end();
		  } else {
		    Integer idAsInteger = Integer.valueOf(id);
		    CapMap.remove(idAsInteger);
		  }
		  routingContext.response().setStatusCode(204).end();
		}
	
	
	
	
	  private void getOne(RoutingContext routingContext) {
		    final String id = routingContext.request().getParam("id");
		    if (id == null) {
		      routingContext.response().setStatusCode(400).end();
		    } else {
		      final Integer idAsInteger = Integer.valueOf(id);
		      Capability capability = CapMap.get(idAsInteger);
		      if (capability == null) {
		        routingContext.response().setStatusCode(404).end();
		      } else {
		        routingContext.response()
		            .putHeader("content-type", "application/json; charset=utf-8")
		            .end(Json.encodePrettily(capability));
		      }
		    }
		  }

		  private void updateOne(RoutingContext routingContext) {
		    final String id = routingContext.request().getParam("id");
		    JsonObject json = routingContext.getBodyAsJson();
		    if (id == null || json == null) {
		      routingContext.response().setStatusCode(400).end();
		    } else {
		      final Integer idAsInteger = Integer.valueOf(id);
		      Capability capability = CapMap.get(idAsInteger);
		      if (capability == null) {
		        routingContext.response().setStatusCode(404).end();
		      } else {
		        capability.setName(json.getString("name"));
		        capability.setLocation(json.getString("location"));
		        routingContext.response()
		            .putHeader("content-type", "application/json; charset=utf-8")
		            .end(Json.encodePrettily(capability));
		      }
		    }
		  }
	
	
	
	

	
	
	
	
	
	
	
	private Map<Integer, Capability> CapMap = new LinkedHashMap<>();
	// Create some product
	private void createSomeData() {
		Capability temp = new Capability("temperature", "192.168.1.240:3001");
		CapMap.put(temp.getId(), temp);
	  Capability airq = new Capability("dust", "192.168.1.240:3002");
	  CapMap.put(airq.getId(), airq);
	}
	
}
