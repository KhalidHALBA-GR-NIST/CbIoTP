package khalid.app1;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

/**
 * Hello world!
 *
 */
public class App extends AbstractVerticle 
{
	
	@Override
	  public void start(Future<Void> fut) {
		
		
		// Create a router object.
		 Router router = Router.router(vertx);

		 // Bind "/" to our hello message - so we are still compatible.
		 
		 // router dispatches/routes requests to route "/" to handler 
		 
		 router.route("/alpha").handler(routingContext -> {
			 
		   HttpServerResponse response = routingContext.response();
		   response.putHeader("content-type", "text/html").end("<h1><button type=\"button\">Click Me!</button><table width=\"100%\" border=\"1\" bordercolor=\"#cccccc\"cellpadding=\"5\" cellspacing=\"5\"><tr><th colspan=\"6\">My Hello Plan for the Week</th></tr><tr><td></td><th>Monday</th><th>Tuesday</th><th>Wednesday</th><th>Thursday</th><th>Friday</th></tr><tr><td>Activity</td><td>Meeting at ABC-123 Ltd</td><td>Business Lunch at 1pm</td><td>Project due by 5pm</td><td>Web Conference in London</td><td>Early Finish (4pm)</td>\n" + 
		   		"</tr></table></h1>\n" + 
		   		"");
		   
		   // router.route("/caplist").handler(routingContext -> { 
		  // HttpServerResponse response = routingContext.response();
		  // response.putHeader("content-type", "text/html").end("<h1>html or json or table</h1>");}   )
		   
		   
		 
		 });

		
		 router.route("/beta").handler(routingContext -> {
			 
			   HttpServerResponse response = routingContext.response();
			   response.putHeader("content-type", "text/html").end("<h1><button type=\"button\">Click her!</button><table width=\"100%\" border=\"1\" bordercolor=\"#cccccc\"cellpadding=\"5\" cellspacing=\"5\"><tr><th colspan=\"6\">My Hello Plan for the Week</th></tr><tr><td></td><th>Monday</th><th>Tuesday</th><th>Wednesday</th><th>Thursday</th><th>Friday</th></tr><tr><td>Activity</td><td>Meeting at ABC-123 Ltd</td><td>Business Lunch at 1pm</td><td>Project due by 5pm</td><td>Web Conference in London</td><td>Early Finish (4pm)</td>\n" + 
			   		"</tr></table></h1>\n" + 
			   		"");
			   
			   // router.route("/caplist").handler(routingContext -> { 
			  // HttpServerResponse response = routingContext.response();
			  // response.putHeader("content-type", "text/html").end("<h1>html or json or table</h1>");}   )
			   

			 });

			
		
		
		 // Serve static resources from the /assets directory
		 router.route("/assets/*").handler(StaticHandler.create("assets"));
		
		
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
	
	
	
	
	
	
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
