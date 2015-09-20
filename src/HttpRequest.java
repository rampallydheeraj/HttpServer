
public class HttpRequest {

	//first line contains 3 parts 
	// 1 request type 
	// 2 filename
	//3 http version
	
	String fileName;
	//we have to create a constructor that accepts a string 
	public HttpRequest(String request){
		//now we have the request of which only the first line is important for us
		String lines[] = request.split("\n");// now we got all the lines of request separately
		
		//this line basically splits the first line using space and then selects the 2nd item from the array
		// which is our file name
		fileName = lines[0].split(" ")[1];
		//fileName = lines[1];
	}
}
