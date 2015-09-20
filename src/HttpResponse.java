import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class HttpResponse {

	HttpRequest req;
	// this is the filal response which is generated 
	String response;
	
	String base = "/Users/rdheeraj/Documents/workspace/";
	// root path of the server
	String root = base +"HttpServer/contents";
	
	public HttpResponse(HttpRequest request){
		req = request;
		// now we have to open the file mentioned in request
		File f = new File(root+req.fileName);
		
		try{
			response = "HTTP/1.0 200 \r\n" ; // version of http and 200 for status code.200 mean everything is OK
			response += "Server: Our Java Server/1.0 \r\n"; // identify the server
			response += "Content-Type: text/html \r\n"; //response is in html format
			response += "Connection: close \r\n"; //this line tells the browser that server is closing the connection
			response += "Content-Length:"+f.length() +"\r\n"; // the length of the response file
			response += "\r\n"; // after blank line we have to append file data
			FileInputStream fis = new FileInputStream(f);
			int s;
			while((s =fis.read()) != -1){ //-1 means end of file
				response +=(char)s;
			}
			System.out.println(response);
			fis.close();
		}catch(FileNotFoundException e){// if we do not find the file on the server folder the error would be 404
			response = response.replace("200","404");
			System.out.println(response);
		}catch(Exception e){ // if there is any other error then 500 internal server error
			response = response.replace("200","500");
			System.out.println(response);
		}
		
		
	}
}
