import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//this is the class which basically handles all the connections which contains the requests 
public class HandleConnection extends Thread 
{
	Socket s;
	// for sending the output to the client
	PrintWriter pw ;
	
	//for getting the input from client
	BufferedReader br;
	//constructor to initialize thread with a Socket
	public HandleConnection(Socket s) throws IOException{
		this.s = s;
		br = new BufferedReader(new InputStreamReader(s.getInputStream()));
		pw = new PrintWriter(s.getOutputStream());
	}

	//thread class contains a method run which is called automatically when we start the Thread
	// in this method we have to read the request and give the response
	@Override
	public void run(){
		// here we get the request string and give this string to HttpRequest class
		try{
			String request ="";
			
			//from br we have to read the request 
			//read until request is not there or br is ready
			while(br.ready() || request.length() == 0){
				request +=(char) br.read();
			}
			System.out.println(request); // for display
			HttpRequest req = new HttpRequest(request);
			
			// now we have to pass the HttpRequest object to HttpResponse class for getting the response
			HttpResponse res = new HttpResponse(req);
			
			pw.write(res.response.toCharArray());
			pw.close();
			br.close();
			s.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
