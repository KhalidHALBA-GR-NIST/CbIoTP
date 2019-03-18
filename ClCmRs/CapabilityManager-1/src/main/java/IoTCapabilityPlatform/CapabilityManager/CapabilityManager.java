package IoTCapabilityPlatform.CapabilityManager;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.sql.SQLConnection;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import org.json.JSONArray;
//import org.json.JSONObject;
//
//import com.fasterxml.jackson.databind.util.JSONPObject;
/**
 * Hello world!
 *
 */
public class CapabilityManager extends AbstractVerticle {
	
	
	  private static final String SQL_CREATE_CAPABILITY_TABLE = "create table if not exists Capabilities (Id integer identity primary key, Name varchar(255) unique, Location varchar(255) unique)";
	  private static final String SQL_GET_CAPABILITY = "select Id, Content from Pages where Location = ?"; // <1>
	  private static final String SQL_CREATE_CAPABILITY = "insert into Pages values (NULL, ?, ?)";
	  private static final String SQL_SAVE_CAPABILITY = "update Pages set Content = ? where Id = ?";
	  private static final String SQL_ALL_CAPABILITY = "select Location from Pages";
	  private static final String SQL_DELETE_CAPABILITY = "delete from Pages where Id = ?";
	
	
	
	  // tag::db-and-logger[]
	  private JDBCClient dbClient;

	  private static final Logger LOGGER = LoggerFactory.getLogger(CapabilityManager.class);
	  // end::db-and-logger[]

	  // tag::prepareDatabase[]
	  private Future<Void> prepareDatabase() {
	    Future<Void> future = Future.future();

	    dbClient = JDBCClient.createShared(vertx, new JsonObject()  // <1>
	      .put("url", "jdbc:hsqldb:file:db/")   // <2>
	      .put("driver_class", "org.hsqldb.jdbcDriver")   // <3>
	      .put("max_pool_size", 30));   // <4>

	    dbClient.getConnection(ar -> {    // <5>
	      if (ar.failed()) {
	        LOGGER.error("Could not open a database connection", ar.cause());
	        future.fail(ar.cause());    // <6>
	      } else {
	        SQLConnection connection = ar.result();   // <7>
	        connection.execute(SQL_CREATE_CAPABILITY_TABLE, create -> {
	          connection.close();   // <8>
	          if (create.failed()) {
	            LOGGER.error("Database preparation error", create.cause());
	            future.fail(create.cause());
	          } else {
	            future.complete();  // <9>
	          }
	        });
	      }
	    });

	    return future;
	  }
	
	
	
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	
	
//	
//	    private final String url = "jdbc:postgresql://localhost/knh6";
//	    private final String user = "postgres";
//	    private final String password = "root1234";
//	    Statement stmt = null;
//	    JsonObject capability = new JsonObject();
//
//    @Override
//    public void start() throws Exception {
//
//        Connection conn = null;
//        try {
//            conn = DriverManager.getConnection(url, user, password);
//        //    System.out.println("Connected to the PostgreSQL server successfully.");
//            
//            stmt = conn.createStatement();
//            ResultSet resultSet = stmt.executeQuery( "SELECT * FROM caplist;" );
// 
//
//            JSONArray array= new JSONArray();
//            
//            JSONArray jsonArray = new JSONArray();
//            
//            JSONObject jsonobject = new JSONObject();
//        //    System.out.println("Y: jsonArray2" +jsonArray2);
//           
//            while (resultSet.next()) {
//            	
//            	//System.out.println("row out " +resultSet.getRow());
//            	JSONObject jsonobject1 = new JSONObject();
//                for (int i = 0; i < resultSet.getMetaData().getColumnCount() ; i++) {
//                	
//                	//System.out.println("row in " +resultSet.getRow());
//                	
//                    //	System.out.println("elements" + resultSet.getObject(i + 1));
//                  // 	System.out.println(" column " +i);
//                	jsonobject1.put(resultSet.getMetaData().getColumnLabel(i + 1),resultSet.getObject(i + 1));
//                	
//                	
//                	// System.out.println("resultSet.getMetaData().getColumnLabel("+i + 1+")" +resultSet.getMetaData().getColumnLabel(i + 1)+" resultSet.getObject("+i + 1+")"+resultSet.getObject(i + 1));
//                	
//                    if(i==2) {
//                 //   System.out.println("jsonobject" +jsonobject);
//                    	array.put(jsonobject1);
//                    
//                    System.out.println("array" +array);
//                 //   System.out.println("A: jsonArray2" +jsonArray2);
//                    }
//                //    System.out.println("B: jsonArray2" +jsonArray2);
//                }
//             //   System.out.println("C: jsonArray2" +jsonArray2);
//            }
//            
//            
//            
//            
//            System.out.println("array 2" +array);
//                vertx.createHttpServer().requestHandler(req -> {
//                    req.response().end(array.toString());	
//                  }).listen(8080, listenResult -> {
//                    if (listenResult.failed()) {
//                      listenResult.cause().printStackTrace();
//                    } else {
//                    }
//                  }); 
//        resultSet.close();
//             stmt.close();
//             conn.close();
//             
//             
//        } catch (SQLException e) {
//           // System.out.println(e.getMessage());
//        }
//    }  
//    public static void main( String[] args )
//    {
//    	Runner.runExample(CapabilityManager.class);		
//    }
}