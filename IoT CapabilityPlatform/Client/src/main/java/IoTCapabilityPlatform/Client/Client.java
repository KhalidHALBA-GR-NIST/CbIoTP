package IoTCapabilityPlatform.Client;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;
/**
 * Hello world!
 *
 */
public class Client extends AbstractVerticle {
	  // Convenience method so you can run it in your IDE
	  public static void main(String[] args) {
	    Runner.runExample(Client.class);
	  }
    @Override
    public void start() throws Exception {
      WebClient client = WebClient.create(vertx);
      client.get(8080, "localhost", "/").send(ar -> {
        if (ar.succeeded()) {
          HttpResponse<Buffer> response = ar.result();
          JsonArray myArray = ar.result().body().toJsonArray();
//          ArrayList<String> listdata = new ArrayList<String>();     
//   
//		JsonArray jArray = (JsonArray)myObject; 
//          if (jArray != null) { 
//             for (int i=0;i<((Buffer) jArray).length();i++){ 
//              listdata.add(jArray.getString(i));
//              
//              
//              System.out.println("okokok"+listdata.get(i)+"\n");
//              
//             } 
//          } 
//          

//		ArrayList<String> list = new ArrayList<String>();     
//
//		if (jArray != null) { 
//		   int len = jArray.size();
//		   for (int i=0;i<len;i++){ 
//		    list.add(jArray.getInteger(i).toString());
//		   } 
//		} 
//          
//	
       //   JsonObject a =	myArray.getJsonObject(0);      
//          
//          for (int i = 0; i < myArray.size(); i++) {
//        	  
//        	  
//        	  JsonObject a =	myArray.getJsonObject(0);
//        	  
//            
//              a.f.put(rs.getMetaData().getColumnLabel(i + 1)
//                      .toLowerCase(), rs.getObject(i + 1));
//              jsonArray.add(obj);
//          }
//		
//		
//	       
    //      System.out.println("myObject " + myArray.size() + " name " + myArray.getList(). + " value " + a.getValue((a.fieldNames()).toString())); 
          System.out.println("Got HTTP response with status " + response.statusCode() + " with data " +
            response.body().toJsonArray());
        } else {
          ar.cause().printStackTrace();
        }
      });
   
    
    
    
    
    }
    
    
    
    
    
    
    
}