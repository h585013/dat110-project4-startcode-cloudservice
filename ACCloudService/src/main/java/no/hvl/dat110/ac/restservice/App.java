package no.hvl.dat110.ac.restservice;

import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;
import static spark.Spark.post;
import static spark.Spark.delete;

import com.google.gson.Gson;

/**
 * Hello world!
 *
 */
public class App {
	
	static AccessLog accesslog = null;
	static AccessCode accesscode = null;
	
	public static void main(String[] args) {

		if (args.length > 0) {
			port(Integer.parseInt(args[0]));
		} else {
			port(8080);
		}

		// objects for data stored in the service
		
		accesslog = new AccessLog();
		accesscode  = new AccessCode();
		
		after((req, res) -> {
  		  res.type("application/json");
  		});
		
		// for basic testing purposes
		get("/accessdevice/hello", (req, res) -> {
			
		 	Gson gson = new Gson();
		 	
		 	return gson.toJson("IoT Access Control Device");
		});
		
		// TODO: implement the routes required for the access control service
		// as per the HTTP/REST operations describined in the project description
		
		// må ha noe for GET/accessdevice/log/-returnerer alle access log entries
		get("/accessdevice/log",(req, res) -> { return  accesslog.toJson();

			
		});
		//GET/accessdevice/log/{id} -returnerer en JSON representasjon av entry med id=id
		get("/accessdevice/log/:id", (req,res) -> { return accesslog.get(Integer.parseInt(req.params(":id")));
		
		});

		//PUT/accessdevice/code skal oppaterer access koden
		put("/accessdevice/code", (req,res) -> { 
			Gson gson = new Gson();
			
int[] newCode=gson.fromJson(req.body(), int[].class);
	accesscode.setAccesscode(newCode);		
		});
		//GET/accessdevice/code skal returnere den nåværede access koden(skal kunne hentes av ardoinoen)
		
		//DELETE/accessdevice/log/ skal slette alle entries i access log og returnere en JSON-representasjon av en tom access log i response
    }
    
}
