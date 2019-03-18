package khalid.app1;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

/**
 * Hello world!
 *
 */
public class App extends AbstractVerticle 
{
	
	@Override
	  public void start(Future<Void> fut) {
	    vertx
	        .createHttpServer()
	        .requestHandler(r -> {
	          r.response().end("<h1>Hello from my first " +
	              "Vert.x 3 application</h1>");
	        })
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
